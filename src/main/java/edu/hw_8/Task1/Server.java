package edu.hw_8.Task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;

public class Server {
    private static final Logger log = Logger.getAnonymousLogger();
    private static final String CONNECTION_ACCEPT = "New connection from the client %s has been accepted";
    private static final int CLIENT_MESSAGE_CAPACITY = 100;
    private final int port;
    private final RoutingMessageHandler routingMessageHandler;

    public boolean isRunning = true;

    public Server(RoutingMessageHandler routingMessageHandler) {
        this.port = 7777;
        this.routingMessageHandler = routingMessageHandler;
    }

    public void start() throws IOException {
        try (Selector selector = Selector.open()) {
            try (ServerSocketChannel serverSocket = configureServerSocketChannel(selector)) {
                while (isRunning) {
                    ByteBuffer buffer = ByteBuffer.allocate(CLIENT_MESSAGE_CAPACITY);
                    selector.select();
                    Set<SelectionKey> selectedKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectedKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        handleSelectionKey(selector, serverSocket, buffer, key);
                        iterator.remove();
                    }
                }
            }
           if(!isRunning) {
               selector.keys()
                   .forEach(selectionKey -> {
                       try {
                           selectionKey.channel().close();
                       } catch (IOException e) {
                           throw new RuntimeException(e);
                       }
                   });
           }
        }
    }

    private void handleSelectionKey(Selector selector, ServerSocketChannel serverSocket, ByteBuffer buffer, SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            configureSocketChannel(selector, serverSocket);
        }
        if (key.isReadable()) {
            SocketChannel client = (SocketChannel) key.channel();
            int r = client.read(buffer);
            if (r == -1) {
                client.close();
            } else {
                buffer.flip();
                routingMessageHandler.handle(client, buffer.array());
                buffer.clear();
            }

        }

    }

    private void configureSocketChannel(Selector selector, ServerSocketChannel serverSocket) throws IOException {
        SocketChannel client = serverSocket.accept();
        log.info(CONNECTION_ACCEPT.formatted(client.getRemoteAddress().toString()));
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }

    private ServerSocketChannel configureServerSocketChannel(Selector selector) throws IOException {
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        return serverSocket;
    }
}
