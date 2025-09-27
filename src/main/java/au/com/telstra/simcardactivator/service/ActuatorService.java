package au.com.telstra.simcardactivator.service;

import au.com.telstra.simcardactivator.model.ActivationResponse;
import au.com.telstra.simcardactivator.model.InternalActivationRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * Service for sending POST request to the Actuator.
 */
@Service
public class ActuatorService {
  private final RestTemplate restTemplate = new RestTemplate();

  public boolean sendActivation(String iccid) {
    InternalActivationRequest payload = new InternalActivationRequest(iccid);

    try {
      ActivationResponse response = restTemplate.postForObject(
              "http://localhost:8444/actuate",
              payload,
              ActivationResponse.class
      );

      return response != null && response.getSuccess();
    } catch (RestClientException err) {
      return false;
    }
  }
}
