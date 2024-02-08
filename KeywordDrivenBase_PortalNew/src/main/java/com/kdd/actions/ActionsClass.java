package com.kdd.actions;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.mail.Message;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.kdd.config.GlobalVariables;
import com.kdd.config.PropertiesConfig;
import com.kdd.utility.CSVFileUtils;
import com.kdd.utility.DateUtility;
import com.kdd.utility.ElementOperations;
import com.kdd.utility.EmailUtils;

/**
 * @author Bheemarao.M
 *
 */
public class ActionsClass extends ElementOperations implements GlobalVariables {
	private static final Logger Logs = Logger.getLogger(ActionsClass.class.getName());

	/**
	 * Keyword: enterText()
	 * 
	 * This keyword is used to Enters Data in to the Text Box
	 *
	 */
	public void enterText(WebDriver driver, String locatorType, String locatorValue, String data, String runTimeValue)
			throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			element.clear();
			element.sendKeys(data);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'enterText' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: selectValueFromDropdownUsingSendKeys()
	 * 
	 * This keyword is used to Select Value from the DropDown
	 *
	 */

	public void selectValueFromDropdownUsingSendKeys(WebDriver driver, String locatorType, String locatorValue,
			String value, String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			Thread.sleep(2000);
			element.click();
			element.sendKeys(value);
			element.sendKeys(Keys.ENTER);
			// element.sendKeys(Keys.TAB);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'selectValueFromDropdownUsingSendKeys' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: selectValueFromDropdownUsingSendKeysArrow()
	 * 
	 * This keyword is used to Select Value from the DropDown
	 *
	 */

	public void selectValueFromDropdownUsingSendKeysArrow(WebDriver driver, String locatorType, String locatorValue,
			String value, String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			Thread.sleep(2000);
			element.click();
			element.sendKeys(value);
			element.sendKeys(Keys.ARROW_DOWN);
			element.sendKeys(Keys.ARROW_DOWN);
			element.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'selectValueFromDropdownUsingSendKeysArrow' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: verifyCreatedRecordInTheTable()
	 * 
	 * This keyword is used to Verify Created Record In the Table
	 *
	 */

	public void verifyCreatedRecordInTheTable(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			Thread.sleep(2000);
			WebElement element = getElement(driver, locatorType, locatorValue);
			Assert.assertTrue(element.getText().toUpperCase().contains(data.toUpperCase()));
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'verifyCreatedRecordInTheTable' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: verifyNoRecordsInTheTable()
	 * 
	 * This keyword is used to Verify No records Created Record In the Table
	 *
	 */

	public void verifyNoRecordsInTheTable(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			List<WebElement> elements = driver.findElements(getElementBy(locatorType, locatorValue));
			Assert.assertTrue(elements.size() < 1);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'verifyNoRecordsInTheTable' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: clickElement()
	 * 
	 * This keyword is used to Click any web element
	 *
	 */
	public void clickElement(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			if (element.isDisplayed()) {
				element.click();
			}
		} catch (ElementClickInterceptedException e) {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			if (element.isDisplayed()) {
				element.click();
			}
		} catch (StaleElementReferenceException e) {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			if (element.isDisplayed()) {
				element.click();
			}
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'clickElement' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: verifyElementDisplayed()
	 * 
	 * This keyword is used to Verify Element is Displayed
	 *
	 */

	public void verifyElementDisplayed(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			Thread.sleep(5000);
			WebElement element = getElement(driver, locatorType, locatorValue);
			Assert.assertTrue(element.isDisplayed());
		} catch (StaleElementReferenceException e) {
			WebElement element = getElement(driver, locatorType, locatorValue);
			Assert.assertTrue(element.isDisplayed());
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'verifyElementDisplayed' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: verifyElementsDisplayed()
	 * 
	 * This keyword is used to Verify List of Elements is Displayed
	 *
	 */

	public void verifyElementsDisplayed(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			List<WebElement> elements = getElements(driver, locatorType, locatorValue);
			Assert.assertTrue(!elements.isEmpty());
		} catch (StaleElementReferenceException e) {
			List<WebElement> elements = getElements(driver, locatorType, locatorValue);
			Assert.assertTrue(!elements.isEmpty());
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'verifyElementsDisplayed' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: clickElementJavaScript()
	 * 
	 * This keyword is used to Click web element using Java Script
	 *
	 */

	public void clickElementJavaScript(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			executor.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'clickElementJavaScript' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: scrollToElementJavaScript()
	 * 
	 * This keyword is used to Scroll to the Web Element using Java Script
	 *
	 */
	public void scrollToElementJavaScript(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'scrollToElementJavaScript' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: getValueFromTheTable()
	 * 
	 * This keyword is used to Get the Value from the table and Store into Runtime
	 * Config Properties
	 *
	 */

	public void getValueFromTheTable(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			PropertiesConfig.setRunTimeProperty(runTimeValue, element.getText());
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'getValueFromTheTable' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: verifyElementNotDisplayed()
	 * 
	 * This keyword is used to Verify Element is Not Displayed
	 *
	 */
	public void verifyElementNotDisplayed(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			Thread.sleep(2000);
			List<WebElement> elements = driver.findElements(getElementBy(locatorType, locatorValue));
			Assert.assertTrue(elements.size() < 1);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'verifyElementNotDisplayed' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: navigateToURL()
	 * 
	 * This keyword is used to Navigate to Specific URL
	 *
	 */
	public void navigateToURL(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			driver.get(data);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitTime));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(implicitWaitTime));
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'navigateToURL' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: clearTextJavaScript()
	 * 
	 * This keyword is used to Clear text in Text Box using java Script
	 *
	 */

	public void clearTextJavaScript(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value='';", element);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'clearTextJavaScript' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: clearText()
	 * 
	 * This keyword is used to Clear text in Text Box
	 *
	 */
	public void clearText(WebDriver driver, String locatorType, String locatorValue, String data, String runTimeValue)
			throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			Actions actions = new Actions(driver);
			actions.click(element).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE)
					.build().perform();
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'clearText' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: verifyElementSelected()
	 * 
	 * This keyword is used to Verify Check box/Radio button is Selected
	 *
	 */

	public void verifyElementSelected(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			Assert.assertEquals(true, element.isSelected());
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'verifyElementSelected' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: verifyElementEnabled()
	 * 
	 * This keyword is used to Verify List of Elements is Enabled
	 *
	 */
	public void verifyElementEnabled(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			Assert.assertTrue(element.isEnabled());
		} catch (StaleElementReferenceException e) {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			Assert.assertTrue(element.isEnabled());
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'verifyElementEnabled' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: verifyElementDisabled()
	 * 
	 * This keyword is used to Verify List of Elements is Disabled
	 *
	 */
	public void verifyElementDisabled(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			Assert.assertTrue(!element.isEnabled());
		} catch (StaleElementReferenceException e) {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			Assert.assertTrue(!element.isEnabled());
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'verifyElementDisabled' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: verifyElementEnabled()
	 * 
	 * This keyword is used to Verify List of Elements is Enabled
	 *
	 */
	public void verifyElementNotEnabled(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			Assert.assertTrue(!(element.isEnabled()));
		} catch (StaleElementReferenceException e) {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			Assert.assertTrue(element.isEnabled());
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'verifyElementNotEnabled' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: clickElementAndHold()
	 * 
	 * This keyword is used to Click and Hold the web element-Mouse Click
	 *
	 */

	public void clickElementAndHold(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			new Actions(driver).clickAndHold(element).perform();
			Assert.assertTrue(true);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'clickElementAndHold' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: clickElementAndRelease()
	 * 
	 * This keyword is used to Click and Release the web element- Mouse Click
	 *
	 */

	public void clickElementAndRelease(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			new Actions(driver).click(element).perform();
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'clickElementAndRelease' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: rightClickElement()
	 * 
	 * This keyword is used to Right Click the web element- Mouse Click
	 *
	 */

	public void rightClickElement(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			new Actions(driver).contextClick(element).perform();
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'rightClickElement' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: doubleClickElement()
	 * 
	 * This keyword is used to Double Click the web element- Mouse Click
	 *
	 */

	public void doubleClickElement(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			new Actions(driver).doubleClick(element).perform();
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'doubleClickElement' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: mouseHoverElement()
	 * 
	 * This keyword is used to Mouse hover to the web element
	 *
	 */

	public void mouseHoverElement(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			new Actions(driver).moveToElement(element).perform();
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'mouseHoverElement' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: scrollToElement()
	 * 
	 * This keyword is used to Scroll to the web element
	 *
	 */

	public void scrollToElement(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			new Actions(driver).scrollToElement(element).perform();
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'scrollToElement' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: scrollToElement()
	 * 
	 * This keyword is used to Scroll to the web element
	 *
	 */

	public void scrollToWindow(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			// WebElement element = getElement(driver, locatorType, locatorValue);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");

		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'scrollToWindow' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: verifyTextDisplayedWithGivenValue()
	 * 
	 * This keyword is used to Verify text Displayed with given Value
	 *
	 */

	public void verifyTextDisplayedWithGivenValue(WebDriver driver, String locatorType, String locatorValue,
			String data, String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			Assert.assertEquals(element.getText().strip().toUpperCase(), data.toUpperCase());
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'verifyTextDisplayedWithGivenValue' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: verifyAmountDisplayedWithGivenValue()
	 * 
	 * This keyword is used to Verify amount Displayed with given Value
	 *
	 */

	public void verifyAmountDisplayedWithGivenValue(WebDriver driver, String locatorType, String locatorValue,
			String data, String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			Assert.assertEquals(element.getText().replace("$", "").replace(",", "").replace(".00", ""), data);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'verifyAmountDisplayedWithGivenValue' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: verifyDisabledTextDisplayedWithGivenValue()
	 * 
	 * This keyword is used to Verify disabled text Displayed with given Value
	 *
	 */

	public void verifyDisabledTextDisplayedWithGivenValue(WebDriver driver, String locatorType, String locatorValue,
			String data, String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			Assert.assertEquals(element.getAttribute("value").toUpperCase().strip(), data.toUpperCase().strip());
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'verifyDisabledTextDisplayedWithGivenValue' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: verifyDisabledPhoneNumberDisplayedWithGivenValue()
	 * 
	 * This keyword is used to Verify disabled text Displayed with given Value
	 *
	 */

	public void verifyDisabledPhoneNumberDisplayedWithGivenValue(WebDriver driver, String locatorType,
			String locatorValue, String data, String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			Assert.assertEquals(element.getAttribute("value").replaceAll("-", ""), data);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'verifyDisabledPhoneNumberDisplayedWithGivenValue' action "
					+ e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: verifyPhoneNumberDisplayedWithGivenValue()
	 * 
	 * This keyword is used to Verify text Displayed with given Value
	 *
	 */

	public void verifyPhoneNumberDisplayedWithGivenValue(WebDriver driver, String locatorType, String locatorValue,
			String data, String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			Assert.assertEquals(element.getAttribute("value").replaceAll("-", ""), data);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'verifyPhoneNumberDisplayedWithGivenValue' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: verifyDisabledAmountDisplayedWithGivenValue()
	 * 
	 * This keyword is used to Verify disabled text Displayed with given Value
	 *
	 */

	public void verifyDisabledAmountDisplayedWithGivenValue(WebDriver driver, String locatorType, String locatorValue,
			String data, String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			Assert.assertEquals(element.getAttribute("value").replace("$", "").replace(",", "").replace(".00", ""),
					data);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'verifyDisabledAmountDisplayedWithGivenValue' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: verifyDisabledTextNotDisplayedWithGivenValue()
	 * 
	 * This keyword is used to Verify disabled text Displayed with given Value
	 *
	 */

	public void verifyDisabledTextNotDisplayedWithGivenValue(WebDriver driver, String locatorType, String locatorValue,
			String data, String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			Assert.assertNotEquals(element.getAttribute("value"), data);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'verifyDisabledTextNotDisplayedWithGivenValue' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: refreshPage()
	 * 
	 * This keyword is used to Refresh Page
	 *
	 */
	public void refreshPage(WebDriver driver, String locatorType, String locatorValue, String data, String runTimeValue)
			throws Exception {
		try {
			driver.navigate().refresh();
			Thread.sleep(10000);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'refreshPage' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: verifyClassAttributeOfTheElement()
	 * 
	 * This keyword is used to verify any attribute of the element (to check
	 * background and text colors)
	 *
	 */
	public void verifyClassAttributeOfTheElement(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			Assert.assertTrue(element.getAttribute("class").equalsIgnoreCase(data));
		} catch (StaleElementReferenceException e) {
			Thread.sleep(3000);
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			Assert.assertTrue(element.getAttribute("class").equalsIgnoreCase(data));
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'verifyClassAttributeOfTheElement' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: VerifyTableSortedByGivenColumn()
	 * 
	 * This keyword is used to verify table is sorted
	 *
	 */
	public void verifyTableSortedByGivenColumn(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			List<WebElement> elements = getElements(driver, locatorType, locatorValue);
			List<String> valCol = new ArrayList<String>();
			for (WebElement ele : elements) {
				valCol.add(ele.getText().toUpperCase());
			}
			Assert.assertTrue(valCol.stream().sorted().collect(Collectors.toList()).equals(valCol));
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'VerifyTableSortedByGivenColumn' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: VerifySearchWithResultsInTable()
	 * 
	 * This keyword is used to verify search Functionality with Results
	 *
	 */
	public void verifySearchWithResultsInTable(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			int rowCount = getElements(driver, locatorType, locatorValue + "//tr").size();
			int colCount = getElements(driver, locatorType, locatorValue + "//tr[1]//td").size();
			boolean found = false;
			for (int i = 1; i < rowCount; i++) {
				found = false;
				for (int j = 1; j < colCount; j++) {
					String text = getElement(driver, locatorType, locatorValue + "//tr[" + i + "]//td[" + j + "]")
							.getText();
					if (!text.isEmpty()) {
						if (text.contains(data)) {
							found = true;
							break;
						}
					}
				}
			}
			Assert.assertTrue(found);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'VerifySearchWithResultsInTable' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: VerifySearchWithNoResultsInTable()
	 * 
	 * This keyword is used to verify search Functionality with No Results
	 *
	 */
	public void VerifySearchWithNoResultsInTable(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			List<WebElement> elements = driver.findElements(getElementBy(locatorType, locatorValue));
			Assert.assertTrue(elements.size() < 1);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'VerifySearchWithNoResultsInTable' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: selectValueFromDropdownByIndex()
	 * 
	 * This keyword is used select value from the dropdown by Index
	 *
	 */
	public void selectValueFromDropdownByIndex(WebDriver driver, String locatorType, String locatorValue, String indexs,
			String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			Select select = new Select(element);
			for (String index : indexs.split(";;")) {
				select.selectByIndex(Integer.valueOf(index));
				Thread.sleep(1000);
			}
			Assert.assertTrue(!select.getAllSelectedOptions().isEmpty());
			Thread.sleep(3000);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'selectValueFromDropdownByIndex' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: selectValueFromDropdownByValue()
	 * 
	 * This keyword is used select value from the dropdown by value
	 *
	 */
	public void selectValueFromDropdownByValue(WebDriver driver, String locatorType, String locatorValue, String values,
			String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			Select select = new Select(element);
			for (String value : values.split(";;")) {
				select.selectByValue(value);
				Thread.sleep(1000);
			}
			Assert.assertTrue(!select.getAllSelectedOptions().isEmpty());
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'selectValueFromDropdownByValue' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: selectValueFromDropdownByVisibleText()
	 * 
	 * This keyword is used select value from the dropdown by visible text
	 *
	 */
	public void selectValueFromDropdownByVisibleText(WebDriver driver, String locatorType, String locatorValue,
			String texts, String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			Select select = new Select(element);
			for (String text : texts.split(";;")) {
				select.selectByValue(text);
			}
			Assert.assertTrue(!select.getAllSelectedOptions().isEmpty());
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'selectValueFromDropdownByVisibleText' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: switchToFrame()
	 * 
	 * This keyword is used for switch to frame
	 *
	 */
	public void switchToFrame(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			WebElement frame = getElementByFluent(driver, locatorType, locatorValue);
			driver.switchTo().frame(frame);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'switchToFrame' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: selectValueFromDropdownUsingSendKeys()
	 * 
	 * This keyword is used to Select Value from the DropDown
	 *
	 */

	public void selectValueFromDropdownUsingDownArrow(WebDriver driver, String locatorType, String locatorValue,
			String value, String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			Thread.sleep(2000);
			element.click();
			element.sendKeys(Keys.ARROW_DOWN);
			element.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'selectValueFromDropdownUsingDownArrow' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: getText()
	 * 
	 * This keyword is used to Get the Value from the UI and Store into Runtime
	 * Config Properties
	 *
	 */

	public void getText(WebDriver driver, String locatorType, String locatorValue, String data, String runTimeValue)
			throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			PropertiesConfig.setRunTimeProperty(runTimeValue, element.getText());
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'getText' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: getTextUsingVal()
	 * 
	 * This keyword is used to Get the Value from the UI and Store into Runtime
	 * Config Properties
	 *
	 */

	public void getTextUsingValue(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			PropertiesConfig.setRunTimeProperty(runTimeValue, element.getAttribute("value"));
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'getTextUsingValue' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: uploadFile()
	 * 
	 * This keyword is used for uploading the file
	 *
	 */
	public void uploadFile(WebDriver driver, String locatorType, String locatorValue, String data, String runTimeValue)
			throws Exception {
		try {
			WebElement uploadFile = getElementByFluent(driver, locatorType, locatorValue);
			uploadFile.sendKeys(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
					+ File.separator + "resources" + File.separator + "Data" + File.separator + data);
			Thread.sleep(3000);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'uploadFile' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: switchToChildWindow()
	 * 
	 * This keyword is used for switch to child window
	 *
	 */
	public void switchToChildWindow(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			Thread.sleep(3000);
			String parentWindow = driver.getWindowHandle();
			Set<String> allWindows = driver.getWindowHandles();
			for (String window : allWindows) {
				if (!window.equals(parentWindow)) {
					String childWindow = window;
					driver.switchTo().window(childWindow);
					Thread.sleep(2000);
					WebElement element = getElement(driver, locatorType, locatorValue);
					Assert.assertTrue(element.isDisplayed());
					driver.close();
				}
			}
			driver.switchTo().window(parentWindow);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'switchToChildWindow' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: generateDynamicCSV()
	 * 
	 * This keyword is used to generate csv file dynamically
	 *
	 */

	public void generateDynamicCSV(WebDriver driver, String headers, String values, String fileName,
			String runTimeValue) throws Exception {
		try {
			String[] csvHeaders = headers.split(";");
			String[] vals = values.split(";");
			List<String> csvValues = new ArrayList<String>();
			for (String value : vals) {
				switch (value) {
				case "CURRENT_SYSDATE":
					value = DateUtility.getCurrentSystemDate(0);
					break;
				case "CURRENT_SYSDATE+1":
					value = DateUtility.getCurrentSystemDate(1);
					break;
				case "CURRENT_SYSDATE+2":
					value = DateUtility.getCurrentSystemDate(2);
					break;
				case "CURRENT_SYS_STARTTIME":
					value = DateUtility.addMinutesToCurrentTime(5);
					break;
				case "CURRENT_SYS_ENDTIME":
					value = DateUtility.addMinutesToCurrentTime(15);
					break;
				case "RANDOM_EMAIL":
					value = DateUtility.generateRandomEmail(10);
					break;
				case "RANDOM_STRING":
					value = DateUtility.generateRandomString(7);
					break;
				case "RANDOM_PHONE":
					value = DateUtility.generateRandomPhoneNumber();
					break;
				case "SINGLEDIGIT_RANDOM_NUMBER":
					value = DateUtility.generateRandomNumber(9, 1);
					break;
				case "DOUBLEDIGIT_RANDOM_NUMBER":
					value = DateUtility.generateRandomNumber(99, 11);
					break;
				case "THREEDIGIT_RANDOM_NUMBER":
					value = DateUtility.generateRandomNumber(999, 100);
					break;
				}
				csvValues.add(value);
			}
			String csvVal[] = csvValues.toArray(new String[csvValues.size()]);
			List<String[]> data = new ArrayList<String[]>();
			data.add(csvHeaders);
			data.add(csvVal);
			CSVFileUtils.generateDynamicCSV(fileName, data);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'generateDynamicCSV' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: uploadDynamicCSVFile()
	 * 
	 * This keyword is used for uploading the file
	 *
	 */
	public void uploadDynamicCSVFile(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			WebElement uploadFile = getElementByFluent(driver, locatorType, locatorValue);
			((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style','visibility:visible;');",
					uploadFile);
			Thread.sleep(2000);
			uploadFile.sendKeys(System.getProperty("user.dir") + File.separator + data + ".csv");
			Thread.sleep(5000);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'uploadDynamicCSVFile' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: readValueFromTheCSV()
	 * 
	 * This keyword is used for uploading the file
	 *
	 */
	public void readValueFromTheCSV(WebDriver driver, String row, String col, String data, String runTimeValue)
			throws Exception {
		try {
			String langName = CSVFileUtils.readValueFromTheCSV(data, row, col);
			PropertiesConfig.setRunTimeProperty(runTimeValue, langName);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'readValueFromTheCSV' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: verifyEmailReceived()
	 * 
	 * This keyword is used to verify email received
	 * 
	 * @data Subject;Content
	 */

	public void verifyEmailReceived(WebDriver driver, String locatorType, String subject, String content,
			String runTimeValue) throws Exception {
		try {
			Thread.sleep(3000);
			String emailSubject = subject;
			String emailContent = content;
			EmailUtils emailUtil = new EmailUtils();
			boolean found = false;
			Message[] msgs = emailUtil.getMessagesBySubject(emailSubject, true, 50);
			Collections.reverse(Arrays.asList(msgs));
			for (Message msg : msgs) {
				String planText = emailUtil.getTextFromMessage(msg);
				if (planText.contains(emailContent)) {
					found = true;
					break;
				}
			}
			Assert.assertTrue(found, "Email recived with the subject:" + emailSubject + " Content:" + emailContent);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'verifyEmailReceived' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: getLinkFromEmail()
	 * 
	 * This keyword is used to verify email received
	 * 
	 * @data Subject;Content
	 */

	public void getLinkFromEmail(WebDriver driver, String locatorType, String subject, String link, String runTimeValue)
			throws Exception {
		try {
			Thread.sleep(3000);
			String emailSubject = subject;
			String emailLink = link;
			EmailUtils emailUtil = new EmailUtils();
			boolean found = false;
			Message[] msgs = emailUtil.getMessagesBySubject(subject, true, 50);
			Collections.reverse(Arrays.asList(msgs));
			for (Message msg : msgs) {
				List<String> urls = emailUtil.getUrlsFromMessage(msg, link);
				PropertiesConfig.setRunTimeProperty(runTimeValue, urls.get(0));
				found = true;
				break;
			}
			Assert.assertTrue(found, "Got the Link:" + emailLink + " From the Email:" + emailSubject);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'getLinkFromEmail' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: verifyTextInPageSource()
	 * 
	 * This keyword is used to Verify text In page source
	 *
	 */

	public void verifyTextInPageSource(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			if (driver.getPageSource().contains(data)) {
				Assert.assertTrue(true, "Following text is present in the page source:" + data);
			} else {
				if (driver.getPageSource().contains(data)) {
					Assert.assertTrue(true, "Following text is present in the page source:" + data);
				} else {
					Assert.assertTrue(false, "Following text is not present in the page source:" + data);
				}
			}
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'verifyTextInPageSource' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: selectDateFromDatePicker
	 * 
	 * This keyword is used select date from date picker
	 *
	 */

	public void selectDateFromDatePicker(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			String[] dateSplit = data.split("-");
			WebElement ele = driver.findElement(By.xpath("//input[@aria-label='Year']"));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			String val = "arguments[0].value='ENTERVAL';".replace("ENTERVAL",
					Integer.toString(Integer.parseInt(dateSplit[2])));
			jse.executeScript(val, ele);
			WebElement element = getElement(driver, locatorType, locatorValue);
			Select select = new Select(element);
			select.selectByValue(Integer.toString(Integer.parseInt(dateSplit[0]) - 1));
			Thread.sleep(2000);
			String date = DateUtility.getMonth(Integer.parseInt(dateSplit[0])) + " " + Integer.parseInt(dateSplit[1])
					+ "," + " " + Integer.parseInt(dateSplit[2]);
			By dateXpath = By.xpath("//span[@aria-label='DATE']".replace("DATE", date));
			Thread.sleep(2000);
			driver.findElement(dateXpath).click();
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'selectDateFromDatePicker' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	public void selectDateFromCalendar(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			Thread.sleep(2000);
			String[] dateSplit = data.split("-");
			WebElement eleYear = driver.findElement(By.xpath("//select[@title='Select year']"));
			Select selectYear = new Select(eleYear);
			selectYear.selectByValue(Integer.toString(Integer.parseInt(dateSplit[2])));
			Thread.sleep(2000);

			WebElement eleMonth = driver.findElement(By.xpath("//select[@title='Select month']"));
			Select selectMonth = new Select(eleMonth);
			selectMonth.selectByValue(Integer.toString(Integer.parseInt(dateSplit[0])));
			Thread.sleep(2000);

			String date = DateUtility.getMonth(Integer.parseInt(dateSplit[0])) + " " + Integer.parseInt(dateSplit[1])
					+ "," + " " + Integer.parseInt(dateSplit[2]);
			By dateXpath = By.xpath("//div[contains(@aria-label,'DATE')]".replace("DATE", date));
			Thread.sleep(2000);
			driver.findElement(dateXpath).click();
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'selectDateFromDatePicker' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: verifyToastMsg()
	 * 
	 * This keyword is used to verify and capture the notification toast message and
	 *
	 */
	public void verifyToastMsg(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			String op = element.getText();
			if (element.getText().startsWith(data)) {
				Assert.assertTrue(true, "Following text is present in the page source:" + data);
			} else if (element.getText().equalsIgnoreCase(data)) {

				Assert.assertTrue(true, "Following text is present in the page source:" + data);
			} else {
				Assert.assertTrue(false,
						"Following text is not present in the page source:" + data + " but found: " + op);
			}
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'verifyToastMsg' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: getExpanseAmount()
	 * 
	 * This keyword is used to Get the getExpanseAmount from UI and Store into
	 * Runtime Config Properties
	 *
	 */

	public void getExpanseAmount(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			PropertiesConfig.setRunTimeProperty(runTimeValue,
					element.getAttribute("value").replace("$", "").replace(",", "").replace(".00", ""));
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'getExpanseAmount' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: enterclearAndEnterText()
	 * 
	 * This keyword is used to Enters Data in to the Text Box
	 *
	 */
	public void clearAndEnterText(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			Actions actions = new Actions(driver);
			actions.click(element).keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE)
					.build().perform();
			element.sendKeys(data);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'clearAndEnterText' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: getSumOfTwoAmounts()
	 * 
	 * This keyword is used to Get the getSumOfTwoAmounts from UI and Store into
	 * Runtime Config Properties
	 *
	 */

	public void getSumOfTwoAmounts(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			Thread.sleep(2000);
			WebElement element1 = getElementByFluent(driver, locatorType, locatorValue);
			WebElement element2 = getElementByFluent(driver, locatorType, data);
			long firstItem = Long.parseLong(element1.getText().replace("$", "").replace(",", "").replace(".00", ""));
			long secondItem = Long.parseLong(element2.getText().replace("$", "").replace(",", "").replace(".00", ""));
			long sum = firstItem + secondItem;
			PropertiesConfig.setRunTimeProperty(runTimeValue, String.valueOf(sum));
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'getSumOfTwoAmounts' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: getSubtractOfTwoAmounts()
	 * 
	 * This keyword is used to Get the getSubtractOfTwoAmounts from UI and Store
	 * into Runtime Config Properties
	 *
	 */

	public void getSubtractOfTwoAmounts(WebDriver driver, String locatorType, String data1, String data2,
			String runTimeValue) throws Exception {
		try {
			Thread.sleep(2000);
			long sub = Integer.parseInt(data2) - Integer.parseInt(data1);
			PropertiesConfig.setRunTimeProperty(runTimeValue, String.valueOf(sub));
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'getSubtractOfTwoAmounts' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: explicitWait()
	 * 
	 * This keyword is used for define explicit wait
	 *
	 */

	public void explicitWait(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			Thread.sleep(10000);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'explicitWait' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: enterNegativeValue()
	 * 
	 * This keyword is used to Enters negative value in to the Text Box
	 *
	 */
	public void enterNegativeValue(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			WebElement element = getElementByFluent(driver, locatorType, locatorValue);
			element.clear();
			element.sendKeys(data.replaceAll(data, "-" + data));
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'enterNegativeValue' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}

	/**
	 * Keyword: verifyElementColor()
	 * 
	 * This keyword is used to Verify color
	 *
	 */

	public void verifyElementColor(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			String color = getElementByFluent(driver, locatorType, locatorValue).getCssValue("color");
			String hex = Color.fromString(color).asHex();
			Assert.assertTrue(hex.equalsIgnoreCase(data));
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'verifyElementColor' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}
	
	
	/**
	 * Keyword: verifyElementBackgroundColor()
	 * 
	 * This keyword is used to Verify background color
	 *
	 */

	public void verifyElementBackgroundColor(WebDriver driver, String locatorType, String locatorValue, String data,
			String runTimeValue) throws Exception {
		try {
			String color = getElementByFluent(driver, locatorType, locatorValue).getCssValue("background-color");
			String hex = Color.fromString(color).asHex();
			Assert.assertTrue(hex.equalsIgnoreCase(data));
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'verifyElementBackgroundColor' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}
	
	/**
	 * Keyword: getText()
	 * 
	 * This keyword is used to Get the Value from the UI and Store into Runtime
	 * Config Properties
	 *
	 */

	public void getFormattedDate(WebDriver driver, String OF, String TF, String OD, String runTimeValue)
			throws Exception {
		try {
			String date = DateUtility.getFormattedDate(OF, TF, OD);
			PropertiesConfig.setRunTimeProperty(runTimeValue, date);
		} catch (Exception e) {
			Logs.error("Failing whie exeuting 'getText' action " + e.getMessage());
			Assert.assertFalse(false, e.getMessage());
			throw e;
		}
	}
}
