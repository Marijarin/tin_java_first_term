package edu.hw_6;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@SuppressWarnings({"MultipleStringLiterals", "MagicNumber"})
public class PortScanner {

    PortInfo whichOccupiedTCP(int port) {
        try (Selector selector = Selector.open()) {
            try (ServerSocketChannel serverSocket = configureServerSocketChannel(selector, port)) {
                serverSocket.accept();
                return new PortInfo("TCP", port, false, "");
            } catch (IOException e) {
                return new PortInfo("TCP", port, true, "");
            }
        } catch (IOException e) {
            return new PortInfo("TCP", port, true, "");
        }

    }

    PortInfo whichOccupiedUDP(int port) {
        try (DatagramChannel datagramChannel = configureDatagramChannel(port)) {
            datagramChannel.getRemoteAddress();
            return new PortInfo("UDP", port, false, "");
        } catch (IOException e) {
            return new PortInfo("UDP", port, true, "");
        }
    }

    List<PortInfo> occupiedOrNot() {
        ArrayList<PortInfo> piList = new ArrayList<>();
        int maxPort = 49151;
        for (int i = 0; i < maxPort; i++) {
            piList.add(whichOccupiedTCP(i));
            piList.add(whichOccupiedUDP(i));
        }
        return piList;
    }

    private ServerSocketChannel configureServerSocketChannel(Selector selector, int port) throws IOException {
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        return serverSocket;
    }

    private DatagramChannel configureDatagramChannel(int port) throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();
        datagramChannel.bind(new InetSocketAddress(port));
        return datagramChannel;
    }

    List<PortInfo> portInfo() {
        String fileName = "common_ports.txt";
        int limit = 50;
        var commonPortsList = new ArrayList<PortInfo>();
        try {
            var portRecordings = Files.readAllLines(Path.of(fileName));
            var pattern = Pattern.compile("^(?<number>\\d+)\\/(?<type>TCP|TCP,UDP|UDP)\\s+(?<name>.+)");
            for (int i = 0; i <= limit; i++) {
                var matcher = pattern.matcher(portRecordings.get(i));
                if (matcher.matches()
                    && !matcher.group("name").contains("резерв")
                    && !matcher.group("name").contains("свобод")) {
                    commonPortsList.add(
                        new PortInfo(
                            matcher.group("type"),
                            Integer.parseInt(matcher.group("number")),
                            true,
                            matcher.group("name").trim()
                        )
                    );
                }
            }
            return commonPortsList;
        } catch (IOException e) {
            return commonPortsList;
        }

    }

    String printInfo() {
        var ports = portInfo();
        StringBuilder sb = new StringBuilder();
        sb.append("""
            Протокол     Порт                        Сервис
                        """);
        sb.append("\n");
        for (PortInfo pi : ports) {
            sb.append(String.format("%-10s", pi.protocol()));
            sb.append("   ");
            sb.append(String.format("%-25s", pi.port()));
            sb.append("   ");
            sb.append(String.format("%-25s", pi.expectedService()));
            sb.append("\n");
        }
        return sb.toString();
    }
}
