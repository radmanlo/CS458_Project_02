# CS458_Project_02

This is an android java native project.

You are able to run the code by any emulator or any android device if you get the APK.

The version of the Android is 5.0 lollipop.

For running this project we used android studio which is provided by the IntelliJ IDEA

To run the tests, an Appium Server needs to be running on your computer. We ran the tests on the emulator provided by the Android Studio IDE. The "Desired Capabilities" provided to the Appium server can be found in the main method of the SurveyTest.java file which can be found in the Test folder. You may need to change the deviceName inside the "Desired Capabilities" depending on where you are running the application and where you will be running the tests. SurveyTest.java file is provided in this project, however it is not intended to run in this project.

To run the tests inside the SurveyTest.java file, you will need to create a new Java project and add the following Maven dependencies:
        <!-- https://mvnrepository.com/artifact/io.appium/java-client -->
        <dependency>
            <groupId>io.appium</groupId>
            <artifactId>java-client</artifactId>
            <version>7.6.0</version>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>3.141.59</version>
        </dependency>
