import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.MobileElement;

import java.net.MalformedURLException;
import java.net.URL;

public class SurveyTest {
    static int errorCount = 0;
    static AppiumDriver driver;
    static MobileElement submitBtn, nameField, genderField, birthdateField, cityField, vaccinatedField, vaccineTypeField, sideEffectField, positiveCaseField, message, closeBtn;

    public static void main(String[] args) throws InterruptedException {
        try {
            openSurvey();
        } catch (MalformedURLException e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

//        emptyInputTest();
//        driver.resetApp();
//        specialCharactersInputTest();
//        driver.resetApp();
//        prohibitedDateTest();
//        driver.resetApp();
        illogicalVaccinationInputTest();
        driver.quit();

        System.out.println(errorCount + " errors found.");
    }

    public static void openSurvey() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:deviceName", "emulator-5554");
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("appium:appPackage", "com.example.cs458_project_02");
        capabilities.setCapability("appium:appActivity", "com.example.cs458_project_02.MainActivity");

        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AppiumDriver<MobileElement>(url, capabilities);

        System.out.println("Application Started");
    }

    public static void initializeElements() {
        submitBtn = (MobileElement) driver.findElement(By.id("com.example.cs458_project_02:id/button"));
        nameField = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.LinearLayout[1]/android.widget.FrameLayout/android.widget.EditText");
        genderField = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.Spinner[1]/android.widget.TextView");
        birthdateField = (MobileElement) driver.findElementById("com.example.cs458_project_02:id/Date");
        cityField = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.LinearLayout[3]/android.widget.FrameLayout/android.widget.EditText");
        vaccinatedField = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.Spinner[2]/android.widget.TextView");
        vaccineTypeField = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.Spinner[3]/android.widget.TextView");
        sideEffectField = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.Spinner[4]/android.widget.TextView");
        positiveCaseField = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.Spinner[5]/android.widget.TextView");
    }

    public static void emptyInputTest() throws InterruptedException {
        initializeElements();
        //All empty
        submitBtn.click(); Thread.sleep(500);

        message = (MobileElement) driver.findElement(By.id("android:id/message"));
        closeBtn = (MobileElement) driver.findElementById("android:id/button1");

        if (message.isDisplayed() && !message.getText().equals("Name-Surname is Empty")) {
            System.out.println("Problem in emptyInputTest! 1");
            errorCount++;
        }
        closeBtn.click(); Thread.sleep(500);

        //All empty except name
        nameField.sendKeys("Ali Veli");

        submitBtn.click(); Thread.sleep(500);

        message = (MobileElement) driver.findElement(By.id("android:id/message"));
        closeBtn = (MobileElement) driver.findElementById("android:id/button1");

        if (message.isDisplayed() && !message.getText().equals("Please Specify Your Gender")) {
            System.out.println("Problem in emptyInputTest! 2");
            errorCount++;
        }
        closeBtn.click(); Thread.sleep(500);
        //All empty except name and gender
        genderField.click(); Thread.sleep(500);

        MobileElement genderChoice = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[3]");
        genderChoice.click(); Thread.sleep(500);

        submitBtn.click(); Thread.sleep(500);

        message = (MobileElement) driver.findElement(By.id("android:id/message"));
        closeBtn = (MobileElement) driver.findElementById("android:id/button1");

        if (message.isDisplayed() && !message.getText().equals("Please Specify Your Birth Date")) {
            System.out.println("Problem in emptyInputTest! 3");
            errorCount++;
        }
        closeBtn.click(); Thread.sleep(500);

        //All empty except name and gender and birth date
        birthdateField.sendKeys("23/03/1998");

        submitBtn.click(); Thread.sleep(500);

        message = (MobileElement) driver.findElement(By.id("android:id/message"));
        closeBtn = (MobileElement) driver.findElementById("android:id/button1");

        if (message.isDisplayed() && !message.getText().equals("Please Specify Your Are Vaccinated or not")) {
            System.out.println("Problem in emptyInputTest! 4");
            errorCount++;
        }
        closeBtn.click(); Thread.sleep(500);

        //All empty except name,gender,birth date and vaccinatedField
        vaccinatedField.click(); Thread.sleep(500);

        MobileElement vaccinatedChoice = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]");
        vaccinatedChoice.click(); Thread.sleep(500);

        submitBtn.click(); Thread.sleep(500);

        message = (MobileElement) driver.findElement(By.id("android:id/message"));
        closeBtn = (MobileElement) driver.findElementById("android:id/button1");

        if (message.isDisplayed() && !message.getText().equals("Please Specify Your City")) {
            System.out.println("Problem in emptyInputTest! 5");
            errorCount++;
        }
        closeBtn.click(); Thread.sleep(500);

        //All empty except name, gender, birthdate, vaccinatedField and city
        cityField.sendKeys("Ankara");
        submitBtn.click(); Thread.sleep(500);

        message = (MobileElement) driver.findElement(By.id("android:id/message"));
        closeBtn = (MobileElement) driver.findElementById("android:id/button1");

        if (message.isDisplayed() && !message.getText().equals("Please Specify Your Vaccine Type")) {
            System.out.println("Problem in emptyInputTest! 6");
            errorCount++;
        }
        closeBtn.click(); Thread.sleep(500);

        //sideEffectField and positiveCaseField are empty
        vaccineTypeField.click(); Thread.sleep(500);

        MobileElement vaccineChoice = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[3]");
        vaccineChoice.click(); Thread.sleep(500);

        submitBtn.click(); Thread.sleep(500);

        message = (MobileElement) driver.findElement(By.id("android:id/message"));
        closeBtn = (MobileElement) driver.findElementById("android:id/button1");

        if (message.isDisplayed() && !message.getText().equals("Please Specify Your Side Effects")) {
            System.out.println("Problem in emptyInputTest! 7");
            errorCount++;
        }
        closeBtn.click(); Thread.sleep(500);

        //positiveCaseField is empty
        sideEffectField.click(); Thread.sleep(500);

        MobileElement sideEffectChoice = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]");
        sideEffectChoice.click(); Thread.sleep(500);

        submitBtn.click(); Thread.sleep(500);

        message = (MobileElement) driver.findElement(By.id("android:id/message"));
        closeBtn = (MobileElement) driver.findElementById("android:id/button1");

        if (message.isDisplayed() && !message.getText().equals("Please Specify Positive Cases")) {
            System.out.println("Problem in emptyInputTest! 8");
            errorCount++;
        }
        closeBtn.click(); Thread.sleep(500);
    }

    public static void specialCharactersInputTest() throws InterruptedException {
        initializeElements();
        //filling the fields that can't have special characters
        genderField.click(); Thread.sleep(500);

        MobileElement genderChoice = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[3]");
        genderChoice.click(); Thread.sleep(500);

        vaccinatedField.click(); Thread.sleep(500);

        MobileElement vaccinatedChoice = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]");
        vaccinatedChoice.click(); Thread.sleep(500);

        vaccineTypeField.click(); Thread.sleep(500);
        MobileElement vaccineChoice = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[3]");
        vaccineChoice.click(); Thread.sleep(500);

        sideEffectField.click(); Thread.sleep(500);
        MobileElement sideEffectChoice = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]");
        sideEffectChoice.click(); Thread.sleep(500);

        positiveCaseField.click(); Thread.sleep(500);
        MobileElement caseChoice = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]");
        caseChoice.click(); Thread.sleep(500);

        //Special Character in name
        nameField.sendKeys("Ben10");
        birthdateField.sendKeys("23/03/1998");
        cityField.sendKeys("Ankara");

        submitBtn.click(); Thread.sleep(500);

        message = (MobileElement) driver.findElement(By.id("android:id/message"));
        closeBtn = (MobileElement) driver.findElementById("android:id/button1");

        if (message.isDisplayed() && !message.getText().contains("Wrong Format")) {
            System.out.println("Problem in specialCharactersInputTest! 1");
            errorCount++;
        }
        closeBtn.click(); Thread.sleep(500);
        //Special Character in birthdate
        nameField.sendKeys("Ali Veli");
        birthdateField.sendKeys("23.03.1998");
        cityField.sendKeys("Ankara");

        submitBtn.click(); Thread.sleep(500);

        message = (MobileElement) driver.findElement(By.id("android:id/message"));
        closeBtn = (MobileElement) driver.findElementById("android:id/button1");

        if (message.isDisplayed() && !message.getText().contains("Invalid")) {
            System.out.println("Problem in specialCharactersInputTest! 2");
            errorCount++;
        }
        closeBtn.click(); Thread.sleep(500);
        //Special Character in city
        nameField.sendKeys("Ali Veli");
        birthdateField.sendKeys("23/03/1998");
        cityField.sendKeys("Ankara&");

        submitBtn.click(); Thread.sleep(500);

        message = (MobileElement) driver.findElement(By.id("android:id/message"));
        closeBtn = (MobileElement) driver.findElementById("android:id/button1");

        if (message.isDisplayed() && !message.getText().contains("Wrong Format")) {
            System.out.println("Problem in specialCharactersInputTest! 3");
            errorCount++;
        }
        closeBtn.click(); Thread.sleep(500);

        //No special character, should submit successfuly
        nameField.sendKeys("Ali Veli");
        birthdateField.sendKeys("23/03/1998");
        cityField.sendKeys("Ankara");

        submitBtn.click(); Thread.sleep(500);

        message = (MobileElement) driver.findElement(By.id("android:id/message"));
        closeBtn = (MobileElement) driver.findElementById("android:id/button1");

        if (message.isDisplayed() && !message.getText().contains("Thank You")) {
            System.out.println("Problem in successfulSubmitTest!");
            errorCount++;
        }
        closeBtn.click(); Thread.sleep(500);
    }

    public static void prohibitedDateTest() throws InterruptedException {
        initializeElements();
        //filling all the fields except birthdate with valid inputs
        genderField.click(); Thread.sleep(500);
        MobileElement genderChoice = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[3]");
        genderChoice.click(); Thread.sleep(500);

        vaccinatedField.click(); Thread.sleep(500);
        MobileElement vaccinatedChoice = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]");
        vaccinatedChoice.click(); Thread.sleep(500);

        vaccineTypeField.click(); Thread.sleep(500);
        MobileElement vaccineChoice = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[3]");
        vaccineChoice.click(); Thread.sleep(500);

        sideEffectField.click(); Thread.sleep(500);
        MobileElement sideEffectChoice = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]");
        sideEffectChoice.click(); Thread.sleep(500);

        positiveCaseField.click(); Thread.sleep(500);
        MobileElement caseChoice = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]");
        caseChoice.click(); Thread.sleep(500);

        nameField.sendKeys("Ali Veli");
        cityField.sendKeys("Ankara");

        //entering the birthdate of someone who isn't at least 18 years old
        birthdateField.sendKeys("01/01/2011");

        submitBtn.click(); Thread.sleep(500);

        message = (MobileElement) driver.findElement(By.id("android:id/message"));
        closeBtn = (MobileElement) driver.findElementById("android:id/button1");

        if (message.isDisplayed() && !message.getText().contains("Too Young")) {
            System.out.println("Problem in prohibitedDateTest!");
            errorCount++;
        }
        closeBtn.click(); Thread.sleep(500);
    }

    public static void illogicalVaccinationInputTest() throws InterruptedException {
        initializeElements();
        //filling all the fields except vaccinated and side effects with valid inputs
        genderField.click(); Thread.sleep(500);
        MobileElement genderChoice = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[3]");
        genderChoice.click(); Thread.sleep(500);

        vaccineTypeField.click(); Thread.sleep(500);
        MobileElement vaccineChoice = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[3]");
        vaccineChoice.click(); Thread.sleep(500);

        positiveCaseField.click(); Thread.sleep(500);
        MobileElement caseChoice = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]");
        caseChoice.click(); Thread.sleep(500);

        nameField.sendKeys("Ali Veli");
        cityField.sendKeys("Ankara");
        birthdateField.sendKeys("01/01/1998");

        //filling vaccinated field NO and side effect field YES
        //app should display an error
        vaccinatedField.click(); Thread.sleep(500);

        MobileElement vaccinatedChoice = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[3]");
        vaccinatedChoice.click(); Thread.sleep(500);

        sideEffectField.click(); Thread.sleep(500);
        MobileElement sideEffectChoice = (MobileElement) driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.CheckedTextView[2]");
        sideEffectChoice.click(); Thread.sleep(500);

        submitBtn.click(); Thread.sleep(500);

        message = (MobileElement) driver.findElement(By.id("android:id/message"));
        closeBtn = (MobileElement) driver.findElementById("android:id/button1");

        if (message.isDisplayed() && !message.getText().contains("You Are Not Vaccinated")) {
            System.out.println("Problem in illogicalVaccinationInputTest!");
            errorCount++;
        }
        closeBtn.click(); Thread.sleep(500);
    }
}
