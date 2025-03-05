package com.bowling;

import jakarta.jws.WebService;
import java.util.List;


@WebService(endpointInterface = "com.bowling.ConfirmationService")
public class ConfirmationServiceImpl implements ConfirmationService {
    List<Employee> employees;

    @Override
    public void confirm() {
        for (Employee e : employees) {
            e.notifyRegistration();
        }
    }
    
}
