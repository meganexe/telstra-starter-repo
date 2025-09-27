package au.com.telstra.simcardactivator.model;

/**
 * An activation request from the client containing the ICCID of
 * the SIM card and the client's email.
 */
public class ActivationRequest {
  private String iccid;
  private String customerEmail;

  public ActivationRequest() {}

  public String getIccid() {
    return iccid;
  }

  public String getCustomerEmail() {
    return customerEmail;
  }

  public void setIccid(String iccid) {
    this.iccid = iccid;
  }

  public void setCustomerEmail(String customerEmail) {
    this.customerEmail = customerEmail;
  }
}
