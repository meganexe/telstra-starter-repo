package au.com.telstra.simcardactivator.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SimActivationEntity {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long id;
  private String iccid;
  private String customerEmail;
  private boolean active;

  /**
   * An entity representing a row in the database which holds
   * SIM activation information.
   * @param iccid - the ICCID of the sim card
   * @param customerEmail - the client's email
   * @param active - if the SIM card is active
   */
  public SimActivationEntity(String iccid, String customerEmail, boolean active) {
    this.iccid = iccid;
    this.customerEmail = customerEmail;
    this.active = active;
  }

  protected SimActivationEntity() {}

  public void setId(Long id) {
    this.id = id;
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

  public Long getId() {
    return id;
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
}
