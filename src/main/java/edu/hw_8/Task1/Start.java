package edu.hw_8.Task1;

import java.io.IOException;
import java.util.Scanner;

@SuppressWarnings({"MagicNumber", "UncommentedMain"})
public final class Start {
    private Start() {
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Client client1 = new Client();
        Client client2 = new Client();
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
        client2.start();
        while (true) {
            Scanner sc = new Scanner(System.in);
            byte[] request1 = sc.nextLine().getBytes();
            client1.send(request1);
            client1.waitResponse().orElseThrow();
            byte[] request2 = sc.nextLine().getBytes();
            client2.send(request2);
            client2.waitResponse().orElseThrow();
        }
    }
}
