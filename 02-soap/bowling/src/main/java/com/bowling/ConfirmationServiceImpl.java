package com.bowling;

import jakarta.jws.WebService;
import java.util.ArrayList;
import java.util.List;


@WebService(endpointInterface = "com.bowling.ConfirmationService")
public class ConfirmationServiceImpl implements ConfirmationService {
    List<Employee> employees = new ArrayList<Employee>();

    @Override
    public void confirmRequest() {
        System.out.println("test");
        for (Employee e : employees) {
            e.notifyRegistration();
        }
    }
    
}
