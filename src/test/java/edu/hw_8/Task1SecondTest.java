package edu.hw_8;

import edu.hw_8.Task1.Client;
import edu.hw_8.Task1.RoutingMessageHandler;
import edu.hw_8.Task1.Server;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import java.io.IOException;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(OutputCaptureExtension.class)
public class Task1SecondTest {
    @Test void answerFromServerIsCorrectWhenNotCorrectInput(CapturedOutput output1)
        throws InterruptedException, IOException {
        Client client1 = new Client();
        RoutingMessageHandler routingMessageHandler = new RoutingMessageHandler(1024);
        Server server = new Server(routingMessageHandler);
        Thread serverTread = new Thread(() -> {
            try {
                server.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        serverTread.start();
        Thread.sleep(100);
        client1.start();

        String test = "глупыйй";

        client1.send(test.getBytes());
        client1.waitResponse();
        client1.close();
        server.isRunning = false;
        serverTread.interrupt();

        assertThat(output1).containsOnlyOnce(test);
    }
}
