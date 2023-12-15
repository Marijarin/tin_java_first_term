package edu.hw_10;

import edu.hw_10.task1.RandomObjectGenerator;
import edu.hw_10.task1.UserPOJO;
import edu.hw_10.task1.UserRecord;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.RepeatedTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @RepeatedTest(40)
    void nameNotNull()
        throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();

        var user1 = (UserRecord) randomObjectGenerator.nextObject(UserRecord.class);

        assertThat(user1.name() == null).isFalse();
    }

    @RepeatedTest(40)
    void ageIsNotLessMin()
        throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();
        int min = 50;

        var user2 = (UserPOJO) randomObjectGenerator.nextObject(UserPOJO.class);
        var user3 = (UserPOJO) randomObjectGenerator.nextObject(UserPOJO.class, "create");

        assertThat(user2.getAge() >= min && user3.getAge() >= min).isTrue();
    }

    @RepeatedTest(40)
    void ageIsLessThanMax()
        throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();
        int max = 14;

        var user1 = (UserRecord) randomObjectGenerator.nextObject(UserRecord.class);

        assertThat(user1.age() <= max).isTrue();
    }

}
