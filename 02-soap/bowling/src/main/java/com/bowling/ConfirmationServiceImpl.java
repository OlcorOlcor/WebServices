package com.bowling;

import jakarta.jws.WebService;
import java.util.ArrayList;
import java.util.List;


@WebService(endpointInterface = "com.bowling.ConfirmationService")
public class ConfirmationServiceImpl implements ConfirmationService {
    List<Employee> employees = new ArrayList<Employee>();

    @jakarta.jws.WebMethod
    @Override
    public void confirmRequest(List<Integer> idsToNotify) {
        if (idsToNotify.isEmpty()) {
            for (Employee e : employees) {
                e.notifyRegistration();
            }
        } else {
            for (Integer id : idsToNotify) {
                for (Employee e : employees) {
                    if (e.getId() == id) {
                        e.notifyRegistration();
                    }
                }
            }
        }
        
    }
    
}
