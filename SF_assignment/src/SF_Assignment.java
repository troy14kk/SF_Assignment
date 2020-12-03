import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class SF_Assignment {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		SoftAssert sAssert = new SoftAssert();

		driver.manage().window().maximize();
		String id_password = "sfwebhtml:t63KUfxL5vUyFLG4eqZNUcuRQ@";
		String label_name = "";
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		String DB_URL = "jdbc:mysql://localhost/SourceFuse";
		Connection conn=null;
		Statement st=null;
		// Login with Id and password
		driver.get("http://" + id_password + "sfwebhtml.sourcefuse.com/automation-form-demo-for-interview/");

		// Testcase 1
		System.out.println("Executing Testcase 1!!");

		driver.findElement(By.cssSelector(".btn.btn-primary")).click();
		List<WebElement> input_fields = driver.findElements(By.cssSelector(".form-control"));

		List<WebElement> labels_textfields = driver.findElements(By.xpath("//label"));

		List<WebElement> radio = driver.findElements(By.cssSelector("input.form-check-input"));

		for (int i = 0; i < input_fields.size(); i++) {

			if (input_fields.get(i).getAttribute("required") != null) {
				label_name = labels_textfields.get(i).getText();
				System.out.println(label_name);
			}

		}

		// checking for radio button separately because they don't have similar html
		// attributes as the text fields
		if (driver.findElement(By.xpath("//input[@id='yes']")).getAttribute("required") != null) {
			System.out.println(driver.findElement(By.xpath("//label[@for='relocate']")).getText());

		}
		System.out.println("Testcase 1 execution completed!");

		// Testcase 2 & 3
		System.out.println("Executing Testcase 2 & 3!!");

		for (int j = 0; j < input_fields.size(); j++) // for all the text fields
		{
			sAssert.assertTrue(input_fields.get(j).isDisplayed());
			Assert.assertTrue(input_fields.get(j).isDisplayed());

		}

		for (int k = 0; k < radio.size(); k++) {
			sAssert.assertTrue(radio.get(k).isDisplayed());
			Assert.assertTrue(radio.get(k).isDisplayed());
		}
		sAssert.assertAll();
		System.out.println("Testcase 2 & 3 execution completed!");

		// Testcase 4
		System.out.println("Executing Testcase 4!!");

		driver.findElement(By.xpath("(//*[@class='form-group']/div)[1]/input")).sendKeys("Shamik");
		driver.findElement(By.xpath("(//*[@class='form-group']/div)[2]/input")).sendKeys("Chatterjee");
		driver.findElement(By.xpath("(//*[@class='form-group']/div)[3]/input")).sendKeys("shamik.prithu@gmail.com");
		driver.findElement(By.xpath("(//*[@class='form-group']/div)[4]/input")).sendKeys("Jungleworks");
		driver.findElement(By.xpath("(//*[@class='form-group']/div)[5]/input")).sendKeys("8585020301");
		driver.findElement(By.xpath("(//*[@class='form-group']/div)[6]/div/input")).sendKeys("11/24/1995");
		driver.findElement(By.xpath("(//*[@class='form-group']/div)[7]/input")).sendKeys("Test Automation Engineer");
		driver.findElement(By.xpath("(//*[@class='form-group']/div)[8]/input"))
				.sendKeys("https://www.linkedin.com/in/shamik-chatterjee-388339140/");
		driver.findElement(By.xpath("(//*[@class='form-group']/div)[9]/input")).sendKeys("10 LPA");
		driver.findElement(By.xpath("(//*[@class='form-group']/div)[10]/input")).sendKeys("7th Jan 2021");
		driver.findElement(By.xpath("//textarea[@id='address']")).sendKeys("B-26, Kendriya Vihar, Gurgaon");
		driver.findElement(By.xpath("//input[@type='file']"))
				.sendKeys("C:\\Users\\HP\\Downloads\\Shamik_Chatterjee_-_Software_Test_Engineer.pdf");
		driver.findElement(By.xpath("//input[@id='yes']")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		System.out.println("Testcase 4 execution completed!");
		
		System.out.println("Executing Testcase 5 & 6!!");
		SimpleDateFormat sdfo = new SimpleDateFormat("yyyy-MM-dd");
		Date dob= sdfo.parse("1995-11-24"); //saving the actual entered date for verification in further steps
		try
		{
			  Class.forName(JDBC_DRIVER);

		      //STEP 3: Open a connection
		      System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(DB_URL,user_name,password);

		      //STEP 4: Execute a query
		      System.out.println("Creating statement...");
		      st = conn.createStatement();
		      String sql;
		      sql = "SELECT * from tb_employee where firstname='Shamik'";
		      ResultSet rs = st.executeQuery(sql);
		      while(rs.next())
		      {
		    	  //fetching all the data from the database
		    	  String first_name=rs.getString("firstName");
		    	  String last_name=rs.getString("lastName");
		    	  String last_Name=rs.getString("lastName");
		    	  String email=rs.getString("email");
		    	  String current_company= rs.getString("currentCompany");
		    	  String phone_no= rs.getString("phone")
		    	  Date DOB= rs.getDate("dateOfBirth");
		    	  String apply_position= rs.getString("applyPos");
		    	  String portfolio= rs.getString("portFolio");
		    	  String sal_req= rs.getString("salReq");
		    	  String start_period= rs.getString("startTime");
		    	  String address=rs.getString("address");
		    	  int relocate= rs.getInt("is_relocate"); //assuming 0- yes, 1- no, 2- not sure
		    	  int email_triggered= rs.getInt("email_sent"); //fetching value of the email trigger column. 1- email sent, 2- email not sent
		    	  
		    	  //verifying the data alongside the data entered in the actual form
		    	  sAssert.assertTrue(first_name.equals("Shamik"));
		    	  sAssert.assertTrue(last_name.equals("Chatterjee"));
		    	  sAssert.assertTrue(email.equals("shamik.prithu@gmail.com"));
		    	  sAssert.assertTrue(current_company.equals("Jungleworks"));
		    	  sAssert.assertTrue(phone_no.equals("8585020301"));
		    	  sAssert.assertTrue(dob.compareTo(DOB) == 0);
		    	  sAssert.assertTrue(apply_position.equals("Test Automation Engineer"));
		    	  sAssert.assertTrue(portfolio.equals("https://www.linkedin.com/in/shamik-chatterjee-388339140/"));
		    	  sAssert.assertTrue(sal_req.equals("10 LPA"));
		    	  sAssert.assertTrue(start_period.equals("7th Jan 2021"));
		    	  sAssert.assertTrue(address.equals("B-26, Kendriya Vihar, Gurgaon"));
		    	  sAssert.assertTrue(relocate==0);
		    	  sAssert.assertTrue(email_triggered==1);
		    	  
		    	  
		    	  
		      }
		}
		catch(SQLException se){
	         se.printStackTrace();
	      }
		


		

	}

}
