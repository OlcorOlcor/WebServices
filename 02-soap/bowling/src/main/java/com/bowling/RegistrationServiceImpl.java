package com.bowling;

import jakarta.jws.WebService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@WebService(endpointInterface = "com.bowling.RegistrationService")
public class RegistrationServiceImpl implements RegistrationService {
    List<Registration> registratrions;
    
    private static final int SLOT_DURATION = 30;

    private boolean checkIfAvailable(LocalDateTime date, int slots) {
        if (slots <= 0) {
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
    public String register(String name, String surname, String email, int slots, LocalDateTime date, String ISIC, String coupon) {
        if (name == "" || surname == "") {
            return "Registration failed: Name and surname must not be empty.";
        }

        if (!checkIfAvailable(date, slots)) {
            return "Registration failed: Your chosen time slot is already taken.";
        }

        Pattern emailRegex = Pattern.compile("^[a-zA-Z]+@[a-zA-Z]+(\\.[a-zA-Z]+)+$");
        Matcher m = emailRegex.matcher(email);
        if (!m.find()) {
            return "Registration failed: Invalid email format.";
        }

        // VALID

        Registration reg = new Registration(name, surname, email, date, slots, ISIC, coupon);
        this.registratrions.add(reg);

        double cost = getPrice(slots, ISIC, coupon);
        return "Registration successful! The final cost is " + cost + ".";
    }
    
}
