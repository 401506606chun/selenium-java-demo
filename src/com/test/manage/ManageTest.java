package com.test.manage;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ManageTest {
	public WebDriver driver;

	public static void main(String[] args) {
		System.out.println("Test jenkins");
		System.out.println("Start===========");

		ManageTest logins = new ManageTest();
		logins.InitDriver();
		try {
			logins.login();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logins.stop();
		System.out.println("end===========");
		

	}

	public void InitDriver() {
		System.setProperty("webdriver.chrome.driver",
		        "D:/Java/workplace/SeleniumTestDemo/chromedriver.exe");
		// ChromeOptions option= new ChromeOptions();
		// option.addArguments("disable-infobars");
		driver = new ChromeDriver();
		driver.get("http://10.5.19.26:9078/manage/index.html");
		driver.manage().window().maximize();

	}

	public void login() throws Exception {

		// 用户名id及输入内容
		String userid = "userId";
		String username = "zhangxiaochun";
		// 密码
		String passid = "password";
		String userpass = "1qaz@WSX3edc";

		// 获取验证码
		String getvalidid = "btnSendCode";
		// 验证码
		String validid = "verifyCode";
		String validcode = "111111";

		this.elementInputById(userid, username);
		this.elementInputById(passid, userpass);

		this.clickaction(getvalidid);
		Thread.sleep(3000);

		this.elementInputById(validid, validcode);

		// 判断是否发送成功
		String validtext = this.getvalue(getvalidid);
		boolean issucess = validtext.endsWith("秒后可以再次获取!");
		System.out.println(validtext);
		System.out.println(issucess);

	}

	// 定义一个输入方法
	public void elementInputById(String key, String value) {
		WebElement user = driver.findElement(By.id(key));
		user.clear();
		user.sendKeys(value);

	}

	// 定义一个点击事件
	public void clickaction(String btn) {
		WebElement click = driver.findElement(By.id(btn));
		click.click();
	}

	// 获取元素值
	public String getvalue(String element) {
		WebElement get = driver.findElement(By.id(element));
		return get.getText();
	}
	//关闭浏览器
	public void stop(){
		driver.close();
	}
}
