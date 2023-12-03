package edu.hw_8;

import edu.hw_8.Task2.another_one.CustomExecutorService;
import edu.hw_8.Task2.another_one.CustomExecutors;
import edu.hw_8.Task2.another_one.FibTask;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(OutputCaptureExtension.class)
public class Task2TPAnotherTest {
    @Test
    void FibTaskCountsCorrectly(CapturedOutput output) {
        int n = 5;
        Runnable r = new FibTask(n);
        r.run();
        assertThat(output.getOut()).containsOnlyOnce("5");
    }

    @Test
    void customThreadPoolWorks(CapturedOutput output) {
        int n = 5;

        try (CustomExecutorService service = CustomExecutors.FixedThreadPool(5)) {
            for (int i = 0; i <= n; i++) {
                service.execute(new FibTask(i));
            }
            Thread.sleep(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertThat(output.getOut()).containsOnlyOnce("5");
    }
}
