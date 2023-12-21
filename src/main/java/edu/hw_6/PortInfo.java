package edu.hw_6;

record PortInfo(
    String protocol,
    int port,
    boolean isOccupied,
    String expectedService
) {
}
