Feature: SIM Activation

  Scenario: Successful activation
    Given a valid ICCID
    When the request is submitted
    Then the activation should succeed

  Scenario: Failing activation
    Given an invalid ICCID
    When the request is submitted
    Then the activation should fail