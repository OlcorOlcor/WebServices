package com.bowling;

public class Employee {
    static Integer lastId = 0;
    private Integer id;
    
    public Employee() {
        id = lastId + 1;
        lastId++;
    }
    
    public void notifyRegistration() {
        System.out.println("Employee has been notified");
    }

    public Integer getId() {
        return id;
    }
}
