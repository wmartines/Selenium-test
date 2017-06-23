package br.selenium.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.FirefoxDriverManager;

public class BuscaCep {

	WebDriver driver;

	@BeforeClass
	public void setUp() {

		FirefoxDriverManager.getInstance().setup();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://www.buscacep.correios.com.br/sistemas/buscacep/");

	}

	@Test
	public void firstTest() {

		WebDriverWait wait = new WebDriverWait(driver, 10);

		driver.findElement(By.xpath(".//*[@id='Geral']/div/div/span[2]/label/input")).sendKeys("Milton Soares Neiva");

		driver.findElement(By.xpath(".//*[@id='Geral']/div/div/div[6]/input")).click();

		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
				By.xpath("html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]")));

		Assert.assertTrue(driver.getPageSource().contains("05833-260"));

	}

	@AfterClass
	public void tearDown() {

		driver.quit();
	}

}
