package edu.hw_8;

import edu.hw_8.Task1.Client;
import edu.hw_8.Task1.RoutingMessageHandler;
import edu.hw_8.Task1.Server;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(OutputCaptureExtension.class)
public class Task1Test {

    @Test void answerFromServerIsCorrectWhenNotCorrectInput(CapturedOutput output)
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
        byte[] bytes = client1.waitResponse().orElseThrow();
        System.out.println(new String(bytes,StandardCharsets.UTF_8));
        assertThat(output).contains("Не могу прислать");
        client1.close();
        server.isRunning = false;
        serverTread.interrupt();
    }
}
