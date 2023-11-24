package edu.hw_7;

import edu.hw_7.Task3.PeopleServiceSynchronized;
import edu.hw_7.Task3.Person;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
@SuppressWarnings({"MagicNumber", "MultipleStringLiterals", "RegexpSingleLineJava"})
public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();
    public static final List<Person> PEOPLE_EXAMPLE = List.of(
        new Person(
            "Mary",
            """
                Ms Mary Smith
                132, My Street,
                Kingston, New York 12401
                United States""",
            "+341119990110"
        ),
        new Person(
            "Mary",
            """
                Ms Mary Fvvbv
                132, Jkklll,
                Vbbnnhmh, Sawddrrr 12401
                Finland""",
            "+93339990000"
        ),
        new Person(
            "Ary",
            """
                Ms Ary Smith
                132, My Street,
                Kingston, New York 12401
                United States""",
            "+342229990000"
        ),
        new Person(
            "John",
            """
                Mr John Smith
                132, My Street,
                Kingston, New York 12401
                United States""",
            "+351119990000"
        ),
        new Person(
            "Katya",
            """
                Ekaterina B. Vasilyeva,
                132, My Street,
                Moscow, 101000
                Russian Federation""",
            "+72229990011"
        )
    );

    private Main() {
    }

    public static void main(String[] args) {
        PiCounter piCounter = new PiCounter();
        System.out.println(piCounter.showPiStats());
        PiCounterStats piCounterStats = new PiCounterStats();
        System.out.println(piCounterStats.showThreadsStats());
        PeopleServiceSynchronized pss = new PeopleServiceSynchronized(3);
        pss.addTasks(
            pss.addPerson(PEOPLE_EXAMPLE.get(0)),

            pss.addPerson(PEOPLE_EXAMPLE.get(2)),
            pss.addPerson(PEOPLE_EXAMPLE.get(3))
        );
        pss.executeTasks();
        pss.addTasks(
            pss.addPerson(PEOPLE_EXAMPLE.get(1)),
            pss.findByName(PEOPLE_EXAMPLE.get(0).name()),
            pss.findByAddress(PEOPLE_EXAMPLE.get(0).address()),
            pss.findByPhone(PEOPLE_EXAMPLE.get(0).phoneNumber())
        );
        pss.executeTasks();
    }
}
