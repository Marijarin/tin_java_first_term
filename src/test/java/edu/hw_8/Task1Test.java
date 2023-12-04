package edu.hw_8;

import edu.hw_8.Task1.Client;
import edu.hw_8.Task1.RoutingMessageHandler;
import edu.hw_8.Task1.Server;
import java.io.IOException;
import java.util.logging.LogManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.MethodName.class)
@ExtendWith(OutputCaptureExtension.class)
public class Task1Test {

    @Test void answerAFromServerIsCorrectWhenNotCorrectInput(CapturedOutput output)
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
        Thread.sleep(1000);
        client1.start();

        String test = "глупыйй";

        client1.send(test.getBytes());
        client1.waitResponse();
        assertThat(output).contains("Не могу прислать подходящую фразу, попробуй другое слово");
        client1.close();
        server.isRunning = false;
        serverTread.interrupt();
    }

    @Test
    void answerBFromServerIsCorrectWhenCorrectInput(CapturedOutput output1) throws InterruptedException, IOException {
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
        assertThat(output1).contains("Ты просто бог идиотизма.");
        client1.close();
        server.isRunning = false;
        serverTread.interrupt();
    }

    @AfterEach
    void reset() throws IOException {
        LogManager.getLogManager().readConfiguration();
    }
}
