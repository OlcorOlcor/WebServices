

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;
import javax.servlet.http.*;
//import jakarta.xml.soap.MessageFactory;
//import jakarta.xml.soap.SOAPBody;
//import jakarta.xml.soap.SOAPBodyElement;
//import jakarta.xml.soap.SOAPConnection;
//import jakarta.xml.soap.SOAPConnectionFactory;
//import jakarta.xml.soap.SOAPElement;
//import jakarta.xml.soap.SOAPEnvelope;
//import jakarta.xml.soap.SOAPMessage;
//import jakarta.xml.soap.SOAPPart;
/**
 * Servlet implementation class intermediary
 */
@WebServlet("/intermediary")
public class intermediary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public intermediary() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
	        SOAPConnectionFactory soapcf = SOAPConnectionFactory.newInstance();
	        SOAPConnection soapc = soapcf.createConnection();
	
	        MessageFactory mf = MessageFactory.newInstance();
	        SOAPMessage soapm = mf.createMessage();
	        
	        SOAPPart soapp = soapm.getSOAPPart();
	        SOAPEnvelope soape = soapp.getEnvelope();
	        SOAPBody soapb = soape.getBody();
	
	        soape.getHeader().detachNode();
	
	        QName name = new QName("http://bowling.com/", "confirmRequest");
	        SOAPElement soapel = soapb.addBodyElement(name);
	        String endpoint = "http://0.0.0.0:8000/ConfirmationService";
	        SOAPMessage response = soapc.call(soapm, endpoint);
	        soapc.close();
	        
	        SOAPBody responseBody = response.getSOAPBody();
	        if (responseBody.hasFault()) {
	            System.out.println("SOAP fault: " + responseBody.getFault().getFaultString());
	        } else {
	            System.out.println("success");
	        }


	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
