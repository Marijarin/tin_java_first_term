package edu.hw_8;

import edu.hw_8.Task1.Client;
import edu.hw_8.Task1.RoutingMessageHandler;
import edu.hw_8.Task1.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(OutputCaptureExtension.class)
public class Task1FirstTest {

    @Test
    void answerFromServerIsCorrectWhenCorrectInput(CapturedOutput output) throws InterruptedException, IOException {
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

        String test = "глупый";

        client1.send(test.getBytes());
        client1.waitResponse();
        Thread.sleep(1000);
        assertThat(output).contains("Ты просто бог идиотизма.");
        client1.close();
        server.isRunning = false;
        serverTread.interrupt();
        System.out.close();
    }

}
