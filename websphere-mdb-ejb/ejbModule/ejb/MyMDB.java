package ejb;

import jar.Echo;

import javax.annotation.PostConstruct;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * Session Bean implementation class MySessionBean
 */
@MessageDriven(activationConfig = { @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") }, mappedName = "jms/SomeQueue")
public class MyMDB implements javax.jms.MessageListener {

	Echo echo;
	
    /**
     * Default constructor. 
     */
    public MyMDB() {
        // TODO Auto-generated constructor stub
    	
    }

    @PostConstruct
    public void init() {
    	System.out.println("initializing ejb");
    	echo = new Echo();
    }

	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		TextMessage text = (TextMessage)message;
    	try {
    		String msg = text.getText();
			echo.echo("Message from JMS: " + text.getText());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
