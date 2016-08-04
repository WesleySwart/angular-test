package hello;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.gargoylesoftware.htmlunit.javascript.host.Console;

@RestController
public class AWSSESController
{
	static final String FROM = "ybaker613@gmail.com";
	static final String TO = "ybaker613@gmail.com";

	static final String BODY = "Congratulations! You've sent an email through Amazon SES by using the AWS SDK for Java.";
	static final String SUBJECT = "Amazon SES test (AWS SDK for Java)";

	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/sendEmail")
	public @ResponseBody String SendEmail() throws JSONException
	{
		
		 // Construct an object to contain the recipient address.
        Destination destination = new Destination().withToAddresses(new String[]{TO});
        
        // Create the subject and body of the message.
        Content subject = new Content().withData(SUBJECT);
        Content textBody = new Content().withData(BODY); 
        Body body = new Body().withText(textBody);
        
        // Create a message with the specified subject and body.
        Message message = new Message().withSubject(subject).withBody(body);
        
        // Assemble the email.
        SendEmailRequest request = new SendEmailRequest().withSource(FROM).withDestination(destination).withMessage(message);
        
        try
        {        
            System.out.println("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");
        
            AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient();
           
            Region REGION = Region.getRegion(Regions.US_WEST_2);
            client.setRegion(REGION);
       
            // Send the email.
            client.sendEmail(request);  
            
            String email = "{\"email\":\"Email Sent!\"}";     
            return email;
        }
		catch (Exception ex)
		{
			String error = "{\"error\":\"The email was not sent: " + ex.getMessage() + "\"}";    
			return error;
		}


	}


}