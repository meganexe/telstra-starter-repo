package au.com.telstra.simcardactivator.model;

public class QueryActivationResponse {
  private String iccid;
  private String customerEmail;
  private boolean active;

  /**
   * A response for returning the information queried about a SIM's
   * activation status.
   * @param iccid - the ICCID of the SIM card
   * @param customerEmail - the client's email
   * @param active - if the SIM card has been activated
   */
  public QueryActivationResponse(String iccid, String customerEmail, boolean active) {
    this.iccid = iccid;
    this.customerEmail = customerEmail;
    this.active = active;
  }

  public String getIccid() {
    return iccid;
  }

  public String getCustomerEmail() {
    return customerEmail;
  }

  public boolean isActive() {
    return active;
  }

  public void setIccid(String iccid) {
    this.iccid = iccid;
  }

  public void setCustomerEmail(String customerEmail) {
    this.customerEmail = customerEmail;
  }

  public void setActive(boolean active) {
    this.active = active;
  }
}
