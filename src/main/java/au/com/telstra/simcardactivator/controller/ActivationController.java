package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.model.ActivationRequest;
import au.com.telstra.simcardactivator.service.ActuatorService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * A controller for handling receiving activation requests from the client.
 */
@RestController
public class ActivationController {
  private final ActuatorService dsService;

  public ActivationController(ActuatorService dsService) {
    this.dsService = dsService;
  }

  @PostMapping("/activate")
  public void activateSim(@RequestBody ActivationRequest request) {
    if (dsService.sendActivation(request.getIccid())) {
      System.out.println("Activation successful");
    } else {
      System.out.println("Activation failed");
    }
  }

}
