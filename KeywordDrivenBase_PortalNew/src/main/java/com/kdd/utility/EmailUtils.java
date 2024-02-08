package com.kdd.utility;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.SubjectTerm;

import com.kdd.config.PropertiesConfig;

public class EmailUtils {

	public Folder inbox;
	public static final String USERNAME = PropertiesConfig.getProperty("EmailID");
	public static final String PASSWORD = PropertiesConfig.getProperty("Password");

	public EmailUtils() throws Exception {
		Properties props = new Properties();
		props.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.pop3.socketFactory.fallback", "false");
		props.put("mail.pop3.socketFactory.port", "995");
		props.put("mail.pop3.port", "995");
		props.put("mail.pop3.host", "pop.gmail.com");
		props.put("mail.pop3.user", EmailUtils.USERNAME);
		props.put("mail.store.protocol", "pop3");
		props.put("mail.pop3.ssl.protocols", "TLSv1.2");
		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EmailUtils.USERNAME, EmailUtils.PASSWORD);
			}
		};
		Session session = Session.getDefaultInstance(props, auth);

		Store store = session.getStore("pop3");
		store.connect("pop.gmail.com", EmailUtils.USERNAME, EmailUtils.PASSWORD);

		inbox = store.getFolder("INBOX");
		inbox.open(Folder.READ_ONLY);
	}

	/**
	 * Searches for messages with a specific subject
	 * 
	 * @param subject     Subject to search messages for
	 * @param unreadOnly  Indicate whether to only return matched messages that are
	 *                    unread
	 * @param maxToSearch maximum number of messages to search, starting from the
	 *                    latest. For example, enter 100 to search through the last
	 *                    100 messages.
	 */
	public Message[] getMessagesBySubject(String subject, boolean unreadOnly, int maxToSearch) throws Exception {
		Map<String, Integer> indices = getStartAndEndIndices(maxToSearch);

		Message messages[] = inbox.search(new SubjectTerm(subject),
				inbox.getMessages(indices.get("startIndex"), indices.get("endIndex")));

		if (unreadOnly) {
			List<Message> unreadMessages = new ArrayList<Message>();
			for (Message message : messages) {
				if (isMessageUnread(message)) {
					unreadMessages.add(message);
				}
			}
			messages = unreadMessages.toArray(new Message[] {});
		}

		return messages;
	}

	/**
	 * Gets text from the end of a line. In this example, the subject of the email
	 * is 'Authorization Code' And the line to get the text from begins with
	 * 'Authorization code:' Change these items to whatever you need for your email.
	 * This is only an example.
	 */
	public String getAuthorizationCode() throws Exception {
		Message email = getMessagesBySubject("Authorization Code", true, 5)[0];
		BufferedReader reader = new BufferedReader(new InputStreamReader(email.getInputStream()));

		String line;
		String prefix = "Authorization code:";

		while ((line = reader.readLine()) != null) {
			if (line.startsWith(prefix)) {
				return line.substring(line.indexOf(":") + 1);
			}
		}
		return null;
	}

	/**
	 * Gets one line of text In this example, the subject of the email is
	 * 'Authorization Code' And the line preceding the code begins with
	 * 'Authorization code:' Change these items to whatever you need for your email.
	 * This is only an example.
	 */
	public String getVerificationCode() throws Exception {
		Message email = getMessagesBySubject("Authorization Code", true, 5)[0];
		BufferedReader reader = new BufferedReader(new InputStreamReader(email.getInputStream()));

		String line;
		while ((line = reader.readLine()) != null) {
			if (line.startsWith("Authorization code:")) {
				return reader.readLine();
			}
		}
		return null;
	}

	

	public List<String> getUrlsFromMessage(Message message, String linkText) throws Exception {
		String html =message.getContent().toString();
		List<String> allMatches = new ArrayList<String>();
		Matcher matcher = Pattern.compile("(<a [^>]+>)" + linkText + "</a>").matcher(html);
		while (matcher.find()) {
			String aTag = matcher.group(1);
			allMatches.add(aTag.substring(aTag.indexOf("http"), aTag.indexOf("\">")));
		}
		return allMatches;
	}

	private Map<String, Integer> getStartAndEndIndices(int max) throws MessagingException {
		int endIndex = getNumberOfMessages();
		int startIndex = endIndex - max;
		// In event that maxToGet is greater than number of messages that exist
		if (startIndex < 1) {
			startIndex = 1;
		}
		Map<String, Integer> indices = new HashMap<String, Integer>();
		indices.put("startIndex", startIndex);
		indices.put("endIndex", endIndex);

		return indices;
	}

	public int getNumberOfMessages() throws MessagingException {
		return inbox.getMessageCount();
	}

	public Message[] getMessages(int maxToGet) throws MessagingException {
		Map<String, Integer> indices = getStartAndEndIndices(maxToGet);
		return inbox.getMessages(indices.get("startIndex"), indices.get("endIndex"));
	}

	public boolean isTextInMessage(Message message, String text) throws Exception {
		String content = getTextFromMessage(message);
		return content.contains(text);
	}

	public boolean isMessageInFolder(String subject, boolean unreadOnly) throws Exception {
		int messagesFound = getMessagesBySubject(subject, unreadOnly, getNumberOfMessages()).length;
		return messagesFound > 0;
	}

	public boolean isMessageUnread(Message message) throws Exception {
		return !message.isSet(Flags.Flag.SEEN);
	}

	public String getTextFromMessage(Message message) throws MessagingException, IOException {
		if (message.isMimeType("text/plain")) {
			return message.getContent().toString();
		}
		if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			return getTextFromMimeMultipart(mimeMultipart);
		}
		return "";
	}

	public String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
		String result = "";
		for (int i = 0; i < mimeMultipart.getCount(); i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				return result + "\n" + bodyPart.getContent(); 
			}
			result += this.parseBodyPart(bodyPart);
		}
		return result;
	}

	public String parseBodyPart(BodyPart bodyPart) throws MessagingException, IOException {
		if (bodyPart.getContent() instanceof MimeMultipart) {
			return getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
		}

		return "";
	}
}