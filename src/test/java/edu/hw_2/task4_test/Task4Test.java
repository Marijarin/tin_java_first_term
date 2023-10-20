package edu.hw_2.task4_test;

import edu.hw_2.task4.CallingInfo;
import org.junit.jupiter.api.Test;
import static edu.hw_2.task4_test.AnotherClass.callFromAnother;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {

    @Test
    void callHere() {
        var result = CallingInfo.callingInfo();
        assertThat(result).isEqualTo(new CallingInfo("edu.hw_2.task4_test.Task4Test", "callHere"));
    }

    @Test
    void anotherClassCall() {
        assertThat(callFromAnother()).isEqualTo(new CallingInfo(
            "edu.hw_2.task4_test.AnotherClass",
            "callFromAnother"
        ));
    }
}

class AnotherClass {
    static CallingInfo callFromAnother() {
        return CallingInfo.callingInfo();
    }
}
