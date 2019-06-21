package com.daniel.test.selenium_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;


public class Login {

	public WebDriver driver;
	
	@Test
	public void initDriver(){
		driver = new FirefoxDriver();
		driver.get("https://www.51zxw.net/");
		driver.manage().window().maximize();
	}
	
	@Test(dependsOnMethods={"initDriver"})
	public void loginScript() throws InterruptedException{
		driver.findElement(By.linkText("登录")).click();
		Thread.sleep(2000);
		WebElement userInputBox = driver.findElement(By.name("loginStr"));
		userInputBox.isDisplayed();
		WebElement passwordInputBox = driver.findElement(By.name("pwd"));
		passwordInputBox.isDisplayed();
		WebElement loginButton = driver.findElement(By.className("btn"));
		loginButton.isDisplayed();
		String userInputText = "andy_wyzxw";
		userInputBox.sendKeys(userInputText);
		passwordInputBox.sendKeys("wdmhwl1002");
		
		loginButton.click();
		
		Thread.sleep(3000);
		WebElement userInfo = driver.findElement(By.xpath("//span[@id='news_login']/div"));
		String userInfoText = userInfo.getText();
		System.out.println(userInfoText);
		//判断登录后网页显示的用户名是我们登录的那个用户名
		if(userInfoText.indexOf(userInputText)!=-1){
			System.out.println("登陆成功");
		}else{
			System.out.println("登录失败");
		}
	}
	
//	public static void main(String[] args) throws InterruptedException {
//		Login login = new Login();
//		login.initDriver();
//		login.loginScript();
//	}
	

}
