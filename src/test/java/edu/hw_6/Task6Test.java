package edu.hw_6;

import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    PortScanner portScanner = new PortScanner();

    @Test
    void isTCPFreePort0() {
        PortInfo portInfo = portScanner.whichOccupiedTCP(0);

        assertThat(portInfo.isOccupied()).isFalse();
    }

    @Test
    void isUDPFreePort0() {
        PortInfo portInfo = portScanner.whichOccupiedUDP(0);

        assertThat(portInfo.isOccupied()).isFalse();
    }

    @Test
    void areOccupiedSeveralPorts() {
        List<PortInfo> occupied = portScanner.occupiedOrNot().stream().filter(PortInfo::isOccupied).toList();

        assertThat(occupied.size()).isGreaterThan(10);
    }

    @Test
    void portInfoIsRecorded() {
        int limit = 50;
        List<PortInfo> fiftyFirstCommonPorts = portScanner.portInfo();

        assertThat(fiftyFirstCommonPorts.size()).isGreaterThanOrEqualTo(limit);
    }
}
