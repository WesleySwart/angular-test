package hello;

import java.util.List;
import java.util.Map.Entry;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;

@RestController
public class AWSSQSController
{
	static AWSCredentials credentials = new ProfileCredentialsProvider().getCredentials();
	static String myQueueUrl = null;
	static AmazonSQS sqs =  new AmazonSQSClient(credentials);
	
	
	public static void createQueue()
	{
		Region usWest2 = Region.getRegion(Regions.US_WEST_2);
		sqs.setRegion(usWest2);
		
		//Create Queue
		System.out.println("Creating a new SQS queue called Queue.\n");
		CreateQueueRequest createQueueRequest = new CreateQueueRequest("Queue");
		myQueueUrl = sqs.createQueue(createQueueRequest).getQueueUrl();
		// List queues
        System.out.println("Listing all queues in your account.\n");
        for (String queueUrl : sqs.listQueues().getQueueUrls()) {
            System.out.println("  QueueUrl: " + queueUrl);
        }
        System.out.println();
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/sendMessage")
	public @ResponseBody String SendSQSMessage(@RequestParam(value="message", defaultValue="This is my message text.") String text) 
	{
		createQueue();
		try
		{
			//Send a message
			System.out.println("Sending a message to MyQueue.\n");
			sqs.sendMessage(new SendMessageRequest(myQueueUrl, text));
			return "{\"message\":\"Message Sent\"}";
		}
		catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which means your request made it " +
                    "to Amazon SQS, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which means the client encountered " +
                    "a serious internal problem while trying to communicate with SQS, such as not " +
                    "being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
		return null;
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("/receiveMessage")
	public @ResponseBody String ReceiveSQSMessage()
	{
		try
		{
			//Receive a message
			System.out.println("Receiving messages from MyQueue.\n");
			ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(myQueueUrl);
			List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
            for (Message message : messages) {
                return("{\"message\":\"  Message\n    MessageId:     " + message.getMessageId() + 
                	   "\n\"    ReceiptHandle: "+ message.getReceiptHandle() +
                	   "\n\"    MD5OfBody:     " + message.getMD5OfBody() + 
                	   "\n\"    Body:          " + message.getBody() + "\"}");
                /*for (Entry<String, String> entry : message.getAttributes().entrySet()) {
                    System.out.println("  Attribute");
                    System.out.println("    Name:  " + entry.getKey());
                    System.out.println("    Value: " + entry.getValue());
                }*/
            }
            System.out.println();
            if(messages.size() == 0)
            	return("{\"message\":\"There's no message to be received.\"}");
		}
		catch (AmazonServiceException ase)
		{
            System.out.println("Caught an AmazonServiceException, which means your request made it " +
                    "to Amazon SQS, but was rejected with an error response for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } 
		catch (AmazonClientException ace)
		{
            System.out.println("Caught an AmazonClientException, which means the client encountered " +
                    "a serious internal problem while trying to communicate with SQS, such as not " +
                    "being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
		return null;
	}

}
