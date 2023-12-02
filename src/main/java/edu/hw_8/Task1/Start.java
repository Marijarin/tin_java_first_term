package edu.hw_8.Task1;

import java.io.IOException;
import java.util.Scanner;

@SuppressWarnings({"MagicNumber", "UncommentedMain"})
public final class Start {
    private Start() {
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Client client = new Client();
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
        client.start();
        while (true) {
            Scanner sc = new Scanner(System.in);
            byte[] request = sc.nextLine().getBytes();
            client.send(request);
            client.waitResponse().orElseThrow();
        }
    }
}
