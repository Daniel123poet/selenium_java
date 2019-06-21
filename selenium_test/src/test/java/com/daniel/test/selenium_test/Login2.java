package com.daniel.test.selenium_test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.functors.ForClosure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


/**
 * 代码重构版本
 * */
public class Login2 {

	public WebDriver driver;
	
	public void initDriver(){
		driver = new FirefoxDriver();
		driver.get("https://www.51zxw.net/");
		driver.manage().window().maximize();
	}
	
	public void loginScript(String username, String password) throws InterruptedException{
		this.initDriver();
//		String username = "andy_wyzxw";
//		String password = "wdmhwl1002";
		String loginLinkElement = "frm_login_url";
		String userElement = "loginStr";
		String passwordElement = "pwd";
		String btnElement = "btn";
		String userInfoElement = "//span[@id='news_login']/div";
		
		String ByLink = "linkText";
		String ByName = "name";
		String ByClass = "className";
		String ByXpath = "xpath";
		String ByID = "id";
		
		ProUtil properties = new ProUtil("element.properties");
		String locator = properties.getPro("userElement");
		String locatorType = locator.split(">")[0];
		String locatorValue = locator.split(">")[1];
		
		
		this.element(this.byStr("loginLinkElement")).click();
		Thread.sleep(2000);
		WebElement userInputBox = this.element(this.byStr("userElement"));
		userInputBox.isDisplayed();
		WebElement passwordInputBox = this.element(this.byStr("passwordElement"));
		passwordInputBox.isDisplayed();
		WebElement loginButton = this.element(this.byStr("btnElement"));
		loginButton.isDisplayed();
		
		userInputBox.sendKeys(username);
		passwordInputBox.sendKeys(password);
		loginButton.click();
		
		Thread.sleep(3000);
		WebElement userInfo = this.element(this.byStr("userInfoElement"));
		String userInfoText = userInfo.getText();
		System.out.println(userInfoText);
		//判断登录后网页显示的用户名是我们登录的那个用户名
		if(userInfoText.indexOf(username)!=-1){
			System.out.println("登陆成功");
		}else{
			System.out.println("登录失败");
		}
		
		this.closeDriver();
	}
	
		
	/**
	 * 封装 By
	 * */
	public By byStr(String key){
		ProUtil properties = new ProUtil("element.properties");
		String locator = properties.getPro(key);
		String locatorType = locator.split(">")[0];
		String locatorValue = locator.split(">")[1];
		
		if(locatorType.equals("id")){
			return By.id(locatorValue);
		}else if(locatorType.equals("name")){
			return By.name(locatorValue);
		}else if(locatorType.equals("className")){
			return By.className(locatorValue);
		}else if(locatorType.equals("linkText")){
			return By.linkText(locatorValue);
		}else{
			return By.xpath(locatorValue);
		}
	}
	
	/**
	 * 封装 Element
	 * */
	public WebElement element(By by){
		WebElement ele = driver.findElement(by);
		return ele;
	}
	
	
	/**
	 * 关闭 Driver
	 * @throws InterruptedException 
	 * */
	public void closeDriver() throws InterruptedException{
		Thread.sleep(5000);
		driver.close();
	}
	
	public static void main(String[] args) throws InterruptedException {
		Login2 login = new Login2();
//		login.loginScript("danielqq", "yanggege1002");
		/**
		 * HashMap 
		 * key-value的形式
		 * */
		Map<String, String> user = new HashMap<String, String>();
		user.put("danielqq", "yanggege1002");
		user.put("andy_wyzxw", "wdmhwl1002");
	
		for(Map.Entry<String, String> userInfo : user.entrySet()){
			String username = userInfo.getKey().toString();
			String password = userInfo.getValue().toString();
			System.out.println(username+": "+password);
			login.loginScript(username, password);
		}
		
//		Iterator<Map.Entry<String, String>> iter = user.entrySet().iterator();
//		while(iter.hasNext()){
//			Map.Entry<String, String> userInfo = iter.next();
//			String username = userInfo.getKey().toString();
//			String password = userInfo.getValue().toString();
//			System.out.println(username+": "+password);
//			login.loginScript(username, password);
//		}
		

	}
	

}
