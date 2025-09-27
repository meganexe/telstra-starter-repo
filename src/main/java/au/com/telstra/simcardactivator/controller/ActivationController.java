package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.model.ActivationRequest;
import au.com.telstra.simcardactivator.model.QueryActivationResponse;
import au.com.telstra.simcardactivator.service.ActuatorService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    boolean success = dsService.sendActivation(request);
  }

  @GetMapping("/activations")
  public QueryActivationResponse getActivation(@RequestParam long simCardId) {
    return dsService.getActivationById(simCardId);
  }
}
