package com.bowling;

import java.time.LocalDateTime;

@jakarta.jws.WebService
public interface RegistrationService {
    
    @jakarta.jws.WebMethod
    public String register(String name, String surname, String email, int slots, LocalDateTime date, String ISIC, String coupon);
}
