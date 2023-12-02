package edu.hw_8.Task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Optional;
import java.util.logging.Logger;

@SuppressWarnings("MagicNumber")
public class Client {
    private static final Logger LOG = Logger.getAnonymousLogger();
    public static final int DEFAULT_BUFFER_CAPACITY = 1000;
    private static final String CONNECTION_CLOSED_UNEXPECTEDLY = "Connection should be opened but it is closed";
    private static final String CONNECTION_SUCCESSFUL = "Connection has been established";
    private static final String CONNECTION_CLOSED = "Connection closed successfully";
    private final ByteBuffer byteBuffer;
    private Selector selector;
    private SocketChannel socketChannel;
    private volatile boolean isConnectionOpened;

    public Client() {
        this.byteBuffer = ByteBuffer.allocate(DEFAULT_BUFFER_CAPACITY);
    }

    public void start() throws IOException {
        int port = 7777;
        socketChannel = SocketChannel.open(new InetSocketAddress(port));
        socketChannel.configureBlocking(false);
        selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_READ);
        isConnectionOpened = true;
        LOG.info(CONNECTION_SUCCESSFUL);
    }

    public void send(byte[] bytes) throws IOException {
        if (!isConnectionOpened) {
            LOG.info(CONNECTION_CLOSED_UNEXPECTEDLY);
            return;
        }
        socketChannel.write(byteBuffer.clear().put(bytes).flip());
        byteBuffer.clear();
    }

    public Optional<byte[]> waitResponse() throws IOException {
        if (!isConnectionOpened) {
            LOG.info(CONNECTION_CLOSED_UNEXPECTEDLY);
            return Optional.empty();
        }
        selector.select();
        byteBuffer.clear();
        socketChannel.read(byteBuffer);
        byteBuffer.clear();
        return Optional.of(byteBuffer.array());
    }

    public void close() throws IOException {
        if (isConnectionOpened) {
            LOG.info(CONNECTION_CLOSED);
            socketChannel.close();
            selector.close();
        }
    }
}
