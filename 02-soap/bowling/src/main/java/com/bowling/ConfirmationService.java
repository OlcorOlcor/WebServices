package com.bowling;

import java.util.List;

@jakarta.jws.WebService
public interface ConfirmationService {
    
    @jakarta.jws.WebMethod
    public void confirmRequest(List<Integer> idstoNotify);
}