package edu.hw_8.Task1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoutingMessageHandler {
    private static final Logger LOG = Logger.getAnonymousLogger();
    private static final String INCOMING_MESSAGE = "Incoming message from the client %s: %s";
    private static final String CLIENT_RESPONSE = "Response to the client %s request: %s";
    private final Map<String, String> handlerMap = new HashMap<>();
    private final ByteBuffer byteBuffer;

    public RoutingMessageHandler(int byteBufferCapacity) {
        this.byteBuffer = ByteBuffer.allocate(byteBufferCapacity);
        handlerMap.put(
            "личности", "Не переходи на личности там, где их нет"
        );
        handlerMap.put(
            "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами"
        );
        handlerMap.put(
            "глупый",
            "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."
        );
        handlerMap.put(
            "интеллект", "Чем ниже интеллект, тем громче оскорбления"
        );

    }

    public void handle(SocketChannel socketChannel, byte[] message) {
        String decoded = new String(message, StandardCharsets.UTF_8).trim();
        byteBuffer.clear();
        try {
            LOG.log(Level.SEVERE, INCOMING_MESSAGE.formatted(socketChannel.getRemoteAddress().toString(), decoded));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (handlerMap.containsKey(decoded)) {
            byteBuffer
                .put(handlerMap.get(decoded).getBytes())
                .flip();
            socketChannelSendWrapper(socketChannel);
        } else {
            socketChannelSendEmptyWrapper(socketChannel);
        }
    }

    private void socketChannelSendWrapper(SocketChannel socketChannel) {
        try {
            LOG.info(CLIENT_RESPONSE.formatted(
                socketChannel.getRemoteAddress().toString(),
                new String(byteBuffer.array(), StandardCharsets.UTF_8).trim()
            ));
            socketChannel.write(byteBuffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void socketChannelSendEmptyWrapper(SocketChannel socketChannel) {
        try {
            LOG.info(CLIENT_RESPONSE.formatted(
                socketChannel.getRemoteAddress().toString(),
                "Не могу прислать подходящую фразу, попробуй еще"
            ));
            socketChannel.write(byteBuffer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
