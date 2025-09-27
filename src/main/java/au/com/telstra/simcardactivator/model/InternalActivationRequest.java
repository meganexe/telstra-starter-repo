package au.com.telstra.simcardactivator.model;

/**
 * An activation request containing the ICCID of the SIM card to
 * send to the actuator.
 */
public class InternalActivationRequest {
  private String iccid;
  public InternalActivationRequest(String iccid) { this.iccid = iccid; }
  public String getIccid() { return iccid; }
}
