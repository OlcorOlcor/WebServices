package com.bowling;

@jakarta.jws.WebService
public interface RegistrationService {
    
    @jakarta.jws.WebMethod
    public String registrationRequest(String name, String surname, String email, int slots, String date, String ISIC, String coupon);
}
