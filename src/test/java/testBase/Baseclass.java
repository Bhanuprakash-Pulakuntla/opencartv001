package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;//log4j2
import org.apache.logging.log4j.Logger;//log4j2
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class Baseclass 
{
	
	public Properties p;
	public static WebDriver driver;
	public Logger logger;//log4j
	@BeforeClass
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException 
	{
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		
		//remote execution (grid execution)
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) 
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			//os setup for grid execution
			if(os.equalsIgnoreCase("windows")) 
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac")) 
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux")) 
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else 
			{
				System.out.println("No matching os");
				return;
			}
			//browser setup for grid execution
			switch(br.toLowerCase()) 
			{
			case "chrome":capabilities.setBrowserName("chrome");break;
			case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
			case "firefox":capabilities.setBrowserName("firefox");break;
			default :System.out.println("No matching Broswer");return;
			}
		
		driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);	
		}
		
		//local execution 
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) 
		{
			switch(br.toLowerCase()) 
			{
			case "chrome" :driver=new ChromeDriver();break;
			case "edge" :driver=new EdgeDriver();break;
			case "firefox" :driver=new FirefoxDriver();break;
			default :System.out.println("passed the invalid browser");return;
			}
		}
		
		logger=LogManager.getLogger(this.getClass());

		//String url=p.getProperty("appurl");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().deleteAllCookies();
		driver.get(p.getProperty("appurl"));
		driver.manage().window().maximize();
	}
	@AfterClass
	public void teardown() 
	{
		driver.quit();
	}
	
	public String randomstring()
	{
		String randomstrings=RandomStringUtils.randomAlphabetic(5);
		return randomstrings;
	}
	public String randomnumber() 
	{
		String randomnums=RandomStringUtils.randomNumeric(10);
		return randomnums;
	}
	
	public String captureScreen(String tname) throws IOException
	{
		String timestamp=new SimpleDateFormat("YYYY.MM.dd.HH.mm.ss").format(new Date());
		
		TakesScreenshot screenshot=(TakesScreenshot)driver;
		File sourcefile=screenshot.getScreenshotAs(OutputType.FILE);
		
		String targetfilepath=System.getProperty("user.dir")+".\\screenshots\\"+tname+"_"+timestamp+".png";
		File targetfile=new File(targetfilepath);
		
		sourcefile.renameTo(targetfile);
		
		return targetfilepath;
	}
}
