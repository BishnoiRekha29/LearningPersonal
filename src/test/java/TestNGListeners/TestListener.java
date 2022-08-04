package TestNGListeners;

import com.DriverFactory.DriverFactory;
import com.DriverFactory.Wrapper;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Parameters;

public class TestListener implements ITestListener {
    RemoteWebDriver driver;

    // @Parameters(value={"browser"})
    public void onStart(ITestContext context) {
         System.out.println("onStart method started");
    }

    public void onFinish(ITestContext context) {
        System.out.println("onFinish method started");
      //  driver.quit();
    }

    public void onTestStart(ITestResult result) {

        System.out.println("New Test Started" +result.getName());
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println("onTestSuccess Method" +result.getName());


    }

    public void onTestFailure(ITestResult result) {
        System.out.println("onTestFailure Method" +result.getName());
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println("onTestSkipped Method" +result.getName());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("onTestFailedButWithinSuccessPercentage" +result.getName());
    }
}