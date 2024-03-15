package deti.ua.tqs;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class CalculatorSteps {

    static final Logger log = getLogger(lookup().lookupClass());

    private Calculator calc;

    @Given("a calculator I just turned on")
    public void setup() {
        calc = new Calculator();
    }

    @When("I add {int} and {int}")
    public void add(int arg1, int arg2) {
        log.debug("Adding {} and {}", arg1, arg2);
        calc.push(arg1);
        calc.push(arg2);
        calc.push("+");
    }

    @When("I substract {int} to {int}")
    public void substract(int arg1, int arg2) {
        log.debug("Substracting {} and {}", arg1, arg2);
        calc.push(arg1);
        calc.push(arg2);
        calc.push("-");
    }

    @When("I multiply {int} by {int}")
    public void i_multiply_by(Integer arg1, Integer arg2) {
        log.debug("Multiplying {} by {}", arg1, arg2);
        // Write code here that turns the phrase above into concrete actions
        calc.push(arg1);
        calc.push(arg2);
        calc.push("*");
    }

    @When("I divide {int} by {int}")
    public void i_divide_by(Integer arg1, Integer arg2) {
        log.debug("Dividing {} by {}", arg1, arg2);
        // Write code here that turns the phrase above into concrete actions
        calc.push(arg1);
        calc.push(arg2);
        calc.push("/");
    }

    @Then("the result is {double}")
    public void the_result_is(double expected) {
        Number value = calc.value();
        log.debug("Result: {} (expected {})", value, expected);
        assertEquals(expected, value);
    }

    @Then("the result is infinity")
    public void the_result_is_invalid() {
        Number value = calc.value();
        log.debug("Result: {} (expected {})", value, "infinity");
        assertEquals(Double.POSITIVE_INFINITY, value);
    }


}
