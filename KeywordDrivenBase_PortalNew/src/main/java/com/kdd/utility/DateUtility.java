package com.kdd.utility;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author Bheemarao.M
 *
 */
public class DateUtility {

	public static String getStringDate(String dateFormat) {
		SimpleDateFormat dtf = new SimpleDateFormat(dateFormat);
		Date localDate = new Date();
		return dtf.format(localDate).toString();
	}

	public static String getCurrentSystemDate(long days) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		LocalDateTime now = LocalDateTime.now().plusDays(days);
		return dtf.format(now);
	}

	public static String addMinutesToCurrentTime(int minToAdd) {
		Calendar currentTimeNow = Calendar.getInstance();
		currentTimeNow.add(Calendar.MINUTE, minToAdd);
		SimpleDateFormat formatDate = new SimpleDateFormat("hh:mma");
		return formatDate.format(currentTimeNow.getTime()).toString();

	}

	public static String generateRandomEmail(int size) {
		String email = generateRandomString(size) + "@sstech.in";
		return email;
	}

	public static String generateRandomString(int size) {
		String generatedString = RandomStringUtils.randomAlphabetic(size);
		return generatedString;
	}

	public static long generateRandomNumber() {
		long number = (long) Math.floor(Math.random() * 100000000) + 10000000;
		return number;
	}

	public static String getCurrentDay(long days) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDateTime now = LocalDateTime.now().plusDays(days);
		String[] time= dtf.format(now).split("/");
		return time[1];
	}
	
	public static String getCurrentMonth(long days) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDateTime now = LocalDateTime.now().plusDays(days);
		String[] time= dtf.format(now).split("/");
		return time[0];
	}
	
	public static String getCurrentYear(long days) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDateTime now = LocalDateTime.now().plusDays(days);
		String[] time= dtf.format(now).split("/");
		return time[2];
	}
	
	public static String getMonth(int month){
	    String[] monthNames = {"","January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	    return monthNames[month];
	}
	
	
	public static String generateRandomPhoneNumber() {
		long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
		String text=String.valueOf(number);
		String string = text.replaceFirst(text.substring(0,1),"9");
		return string.toString();
	}
	
	public static String generateRandomNumber(int max, int min) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return String.valueOf(randomNum);
    }
	
	public static String generateRandomFloatNumber(int max, int min) {
		Random rand = new Random();
		DecimalFormat df = new DecimalFormat("#.##");
        return String.valueOf(df.format(rand.nextFloat() * (99 - 11) + 11));
    }
	
	public static String getFormattedDate(String originalFormat,String targetFormat,String originalDate) throws Exception {
		DateFormat OF = new SimpleDateFormat(originalFormat, Locale.ENGLISH);
		DateFormat TF = new SimpleDateFormat(targetFormat);
		Date date = OF.parse(originalDate);
		String formattedDate = TF.format(date);
		return formattedDate;
	}
}
