package com.bowling;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Registration {
    String name;
    String surname;
    String email;
    LocalDateTime date;
    int slots;
    String ISIC;
    String coupon;

    public Registration(String name, String surname, String email, LocalDateTime date, int slots, String ISIC, String coupon) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.date = date;
        this.slots = slots;
        this.ISIC = ISIC;
        this.coupon = coupon;
    }
}
