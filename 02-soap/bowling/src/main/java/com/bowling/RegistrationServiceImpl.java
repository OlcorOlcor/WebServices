package com.bowling;

import jakarta.jws.WebService;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebService(endpointInterface = "com.bowling.RegistrationService")
public class RegistrationServiceImpl implements RegistrationService {
    List<Registration> registratrions = new ArrayList<Registration>();
    
    private static final int SLOT_DURATION = 30;

    private boolean checkIfAvailable(LocalDateTime date, int slots) {
        if (slots <= 0 || date == null) {
            return false;
        }
        for (Registration r : this.registratrions) {
            if ((date.isAfter(r.date) && date.isBefore(r.date.plusMinutes(r.slots * SLOT_DURATION))) || (date.plusMinutes(slots * SLOT_DURATION).isAfter(r.date) && date.plusMinutes(slots * SLOT_DURATION).isAfter(r.date.plusMinutes(r.slots * SLOT_DURATION)))) {
                return false;
            }
        }
        return true;
    }

    // very simplified
    private double getPrice(int slots, String ISIC, String coupon) {
        double price = 500 * slots;
        double amount = price * 0.9;
        
        if (ISIC != "") {
            price -= amount;
        }

        if (coupon != "") {
            price -= amount;
        }

        return price;
    }

    @Override
    public String registrationRequest(String name, String surname, String email, int slots, String date, String ISIC, String coupon) {
        
        System.out.println("##############");
        System.out.println(surname);
        System.out.println(email);
        System.out.println(slots);
        System.out.println(date);
        System.out.println(ISIC);
        System.out.println(coupon);
        System.out.println("##############");

        LocalDateTime convertedDate;
        try {
            convertedDate = LocalDateTime.parse(date);
        } catch (Exception e) {
            return "Registration failed: Invalid date format.";
        }

        if (name == "" || surname == "" || name == null || surname == null) {
            return "Registration failed: Name and surname must not be empty.";
        }

        if (!checkIfAvailable(convertedDate, slots)) {
            return "Registration failed: Your chosen time slot is already taken.";
        }

        Pattern emailRegex = Pattern.compile("^[a-zA-Z]+@[a-zA-Z]+(\\.[a-zA-Z]+)+$");
        Matcher m = emailRegex.matcher(email);
        if (!m.find()) {
            return "Registration failed: Invalid email format.";
        }

        // VALID

        Registration reg = new Registration(name, surname, email, convertedDate, slots, ISIC, coupon);
        this.registratrions.add(reg);

        double cost = getPrice(slots, ISIC, coupon);
        return "Registration successful! The final cost is " + cost + ".";
    }
    
}
