package au.com.telstra.simcardactivator.service;

import au.com.telstra.simcardactivator.model.ActivationRequest;
import au.com.telstra.simcardactivator.model.ActivationResponse;
import au.com.telstra.simcardactivator.model.InternalActivationRequest;
import au.com.telstra.simcardactivator.model.QueryActivationResponse;
import au.com.telstra.simcardactivator.model.SimActivationEntity;
import au.com.telstra.simcardactivator.repository.SimActivationRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

/**
 * Service for sending POST request to the Actuator.
 */
@Service
public class ActuatorService {
  private final RestTemplate restTemplate = new RestTemplate();
  private final SimActivationRepository repo;

  public ActuatorService(SimActivationRepository repo) {
    this.repo = repo;
  }

  public boolean sendActivation(ActivationRequest request) {
    InternalActivationRequest payload = new InternalActivationRequest(request.getIccid());
    boolean active;

    try {
      ActivationResponse response = restTemplate.postForObject(
              "http://localhost:8444/actuate",
              payload,
              ActivationResponse.class
      );
      active = response != null && response.getSuccess();
    } catch (RestClientException err) {
      active = false;
    }

    SimActivationEntity entity = new SimActivationEntity(
            request.getIccid(),
            request.getCustomerEmail(),
            active
    );

    repo.save(entity);

    return active;
  }

  public QueryActivationResponse getActivationById(long simCardId) {
    SimActivationEntity entity = repo.findById(simCardId)
            .orElseThrow(() ->  new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Activation not found"));
    return new QueryActivationResponse(
            entity.getIccid(),
            entity.getCustomerEmail(),
            entity.isActive()
    );
  }
}
