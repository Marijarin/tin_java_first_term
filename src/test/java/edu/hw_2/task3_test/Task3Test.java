package edu.hw_2.task3_test;

import edu.hw_2.task3.ConnectionManager;
import edu.hw_2.task3.DefaultConnectionManager;
import edu.hw_2.task3.FaultyConnectionManager;
import edu.hw_2.task3.PopularCommandExecutor;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

public class Task3Test {
    ConnectionManager md = new DefaultConnectionManager();
    ConnectionManager mf = new FaultyConnectionManager();
    int maxAttempts = 5;
    PopularCommandExecutor p1 = new PopularCommandExecutor(md, maxAttempts);

     @Test
    void executeDefaultManager(){
         PopularCommandExecutor p = new PopularCommandExecutor(md, maxAttempts);

         assertThatNoException().isThrownBy(p::updatePackages);
     }

     @Test
     void executeFaultyManagerThrowsException(){
         PopularCommandExecutor p = new PopularCommandExecutor(mf, 1);

         assertThatException().isThrownBy(() -> p.tryExecute("apt update"));
     }

    @Test
    void executeFaultyManagerNotThrowsException(){
        PopularCommandExecutor p = new PopularCommandExecutor(mf, maxAttempts);

        assertThatNoException().isThrownBy(() -> p.tryExecute("apt update && upgrade"));
    }
}
