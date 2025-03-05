import jakarta.xml.ws.Endpoint;

import com.bowling.ConfirmationServiceImpl;
import com.bowling.RegistrationServiceImpl;

public class Main {
    public static void main(String[] args) {
        Endpoint.publish("http://0.0.0.0:8000/RegistrationService", new RegistrationServiceImpl());
        Endpoint.publish("http://0.0.0.0:8000/Confirm", new ConfirmationServiceImpl());
    }
}