package stepDefinitions;

import au.com.telstra.simcardactivator.SimCardActivator;
import au.com.telstra.simcardactivator.model.ActivationRequest;
import au.com.telstra.simcardactivator.model.QueryActivationResponse;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = SimCardActivator.class, loader = SpringBootContextLoader.class)
public class SimCardActivatorStepDefinitions {
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;

    private ActivationRequest request;

    @Given("a valid ICCID")
    public void a_valid_iccid() {
        request = new ActivationRequest();
        request.setIccid("1255789453849037777");
        request.setCustomerEmail("test@email.com");
    }
    @When("the request is submitted")
    public void the_request_is_submitted() {
        restTemplate.postForEntity("http://localhost:" + port + "/activate", request, Void.class);
    }
    @Then("the activation should succeed")
    public void the_activation_should_succeed() {
        QueryActivationResponse response = restTemplate.getForObject(
                "http://localhost:" + port + "/activations?simCardId=1",
                QueryActivationResponse.class
        );
        assertTrue(response.isActive());
    }

    @Given("an invalid ICCID")
    public void an_invalid_iccid() {
        request = new ActivationRequest();
        request.setIccid("8944500102198304826");
        request.setCustomerEmail("test@email.com");
    }

    @Then("the activation should fail")
    public void the_activation_should_fail() {
        QueryActivationResponse response = restTemplate.getForObject(
                "http://localhost:" + port + "/activations?simCardId=2",
                QueryActivationResponse.class
        );
        assertFalse(response.isActive());
    }

}