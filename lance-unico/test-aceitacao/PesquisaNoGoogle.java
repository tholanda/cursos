import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class PesquisaNoGoogle {
	
	public static void main(String[] args){
		
		WebDriver driver = new FirefoxDriver();
		
		driver.get("http://www.google.com.br");
		
		WebElement campoDeTexto = driver.findElement(By.name("q"));
		campoDeTexto.sendKeys("triadworks");
		
		campoDeTexto.submit();
		
		//driver.close();
	}

}
