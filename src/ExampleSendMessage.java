import com.messagebird.MessageBirdClient;
import com.messagebird.MessageBirdService;
import com.messagebird.MessageBirdServiceImpl;
import com.messagebird.objects.MessageResponse;
import com.messagebird.exceptions.GeneralException;
import com.messagebird.exceptions.UnauthorizedException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


public class ExampleSendMessage {

    public static void main(String[] args) {

        // First create your service object
        final MessageBirdService wsr = new MessageBirdServiceImpl("n7DIu4rWxeYhw8phlrdPGdJiB");

        // Add the service to the client
        final MessageBirdClient messageBirdClient = new MessageBirdClient(wsr);
        try {
            System.out.println("Sending message:");
            final List<BigInteger> phones = new ArrayList<BigInteger>();
            phones.add(new BigInteger("9466000037"));
            final MessageResponse response = messageBirdClient.sendMessage("MessageBird", "My message to be send", phones);
            System.out.println(response.toString());

        } catch (Exception exception) {
            if (exception != null) {
                System.out.println(exception);
            }
            exception.printStackTrace();
        }
    }
}