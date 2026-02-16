package tests;

import org.testng.annotations.Test;

public class TestScenario2 extends TestScenario1 {

    @Test(timeOut = 20000)
    public void runScenario2() {
        runScenario1();
    }
}