package inter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.xml.namespace.QName;

import jakarta.xml.soap.*;
/**
 * Servlet implementation class InterServlet
 */
@WebServlet("/InterServlet")
public class InterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            SOAPConnectionFactory soapcf = SOAPConnectionFactory.newInstance();
            SOAPConnection soapc = soapcf.createConnection();

            MessageFactory mf = MessageFactory.newInstance();
            SOAPMessage soapm = mf.createMessage(null, request.getInputStream());
            
            SOAPPart soapp = soapm.getSOAPPart();
            SOAPEnvelope soape = soapp.getEnvelope();
            SOAPBody soapb = soape.getBody();
            SOAPHeader soaph = soape.getHeader();
            // soape.getHeader().detachNode();
            
            SOAPHeaderElement tweak = (SOAPHeaderElement)
    				soaph.getChildElements(new QName("tweak")).next();
            
            String add = tweak.getAttribute("idsToNotify");
            
            Integer specificId = 2;
            
            
            if (add.toLowerCase() == "true") {
				SOAPBodyElement el = (SOAPBodyElement)soapb.getChildElements(new QName("http://bowling.com/", "confirmRequest")).next();
				el.addChildElement(new QName("arg0")).addTextNode(String.valueOf(specificId));
				tweak.detachNode();
			}
            
            String endpoint = "http://0.0.0.0:8000/ConfirmationService";
            SOAPMessage res = soapc.call(soapm, endpoint);
            soapc.close();
            
            SOAPHeader header = res.getSOAPHeader();
            
            
            SOAPBody responseBody = res.getSOAPBody();
            if (responseBody.hasFault()) {
                System.out.println("SOAP fault: " + responseBody.getFault().getFaultString());
            } else {
                res.writeTo(response.getOutputStream());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
