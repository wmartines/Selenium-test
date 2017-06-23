package br.selenium.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class BuscaCep {

	WebDriver driver;

	/**
	 * Setup do driver feito com Maven
	 * 
	 */
	@BeforeClass
	public void setUp() {
		
		ChromeDriverManager.getInstance().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}

	/**
	 * Metodo responsavel por realizar pesquisa de endereco
	 * 
	 */
	@Test(priority = 1)
	public void invalidSearch() {

		// Tentar realizar a acao dentro do bloco try
		try {

			// Abre pagina para buscar cep
			driver.get("http://www.buscacep.correios.com.br/sistemas/buscacep/");

			// Declara espera de 10 segundos
			WebDriverWait wait = new WebDriverWait(driver, 10);

			// Envia parametro para o campo de busca
			driver.findElement(By.xpath(".//*[@id='Geral']/div/div/span[2]/label/input")).sendKeys("Milton Soares Neiva");

			// Clica no botao pesquisar
			driver.findElement(By.xpath(".//*[@id='Geral']/div/div/div[6]/input")).click();

			// Aguarde no maximo 10 segundos ate o resultado da pesquisa ser exibido		
			wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("html/body/div[1]/div[3]/div[2]/div/div/div[2]/div[2]/div[2]")));

			// Certifique-se de que o resultado e true
			Assert.assertTrue(driver.getPageSource().contains("05833-260"));

			// Captura a excecao
		} catch (Exception e) {

			// Imprime excecao
			System.out.println("Erro buscar por cep: " + e.getCause());
		}

	}
	
	@AfterMethod
	public void afterMethod(ITestResult testResult){
		
		if(testResult.getStatus() == ITestResult.FAILURE){
			
			
		}else{
			
			
		}
	}

	@AfterClass
	public void tearDown() {

		driver.quit();
	}

}
