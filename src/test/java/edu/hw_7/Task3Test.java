package edu.hw_7;

import edu.hw_7.Task3.PeopleServiceLock;
import edu.hw_7.Task3.PeopleServiceSynchronized;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    void canFindExistingPersonDataWithSynchronized(){
        PeopleServiceSynchronized peopleServiceSynchronized = new PeopleServiceSynchronized(3);

        peopleServiceSynchronized.addTasks(

            peopleServiceSynchronized.addPerson(Main.PEOPLE_EXAMPLE.get(2)),
            peopleServiceSynchronized.addPerson(Main.PEOPLE_EXAMPLE.get(1)));

        peopleServiceSynchronized.executeTasks();

        peopleServiceSynchronized.addTasks(
            peopleServiceSynchronized.addPerson(Main.PEOPLE_EXAMPLE.get(3)),
            peopleServiceSynchronized.findByName(Main.PEOPLE_EXAMPLE.get(3).name()),
            peopleServiceSynchronized.findByAddress(Main.PEOPLE_EXAMPLE.get(3).address()),
            peopleServiceSynchronized.findByPhone(Main.PEOPLE_EXAMPLE.get(3).phoneNumber()));

        peopleServiceSynchronized.executeTasks();

        assertThat(
            peopleServiceSynchronized.foundByName.size()
                + peopleServiceSynchronized.foundByAddress.size()
                + peopleServiceSynchronized.foundByPhone.size()).isEqualTo(3);
    }

    @Test
    void canFindExistingPersonDataWithLock(){
        PeopleServiceLock peopleServiceLock = new PeopleServiceLock(3);

        peopleServiceLock.addTasks(

            peopleServiceLock.addPerson(Main.PEOPLE_EXAMPLE.get(2)),
            peopleServiceLock.addPerson(Main.PEOPLE_EXAMPLE.get(1)));

        peopleServiceLock.executeTasks();

        peopleServiceLock.addTasks(
            peopleServiceLock.addPerson(Main.PEOPLE_EXAMPLE.get(3)),
            peopleServiceLock.findByName(Main.PEOPLE_EXAMPLE.get(3).name()),
            peopleServiceLock.findByAddress(Main.PEOPLE_EXAMPLE.get(3).address()),
            peopleServiceLock.findByPhone(Main.PEOPLE_EXAMPLE.get(3).phoneNumber()));

        peopleServiceLock.executeTasks();
assertThat(peopleServiceLock.foundByName.size()
    + peopleServiceLock.foundByAddress.size()
    + peopleServiceLock.foundByPhone.size()).isEqualTo(3);

    }

    @Test
    void canAddSomePeopleWithSynchronized(){
        PeopleServiceSynchronized peopleServiceSynchronized = new PeopleServiceSynchronized(3);

        peopleServiceSynchronized.addTasks(
            peopleServiceSynchronized.addPerson(Main.PEOPLE_EXAMPLE.get(3)),
            peopleServiceSynchronized.addPerson(Main.PEOPLE_EXAMPLE.get(2)),
            peopleServiceSynchronized.addPerson(Main.PEOPLE_EXAMPLE.get(1)));

        peopleServiceSynchronized.executeTasks();

        assertThat(peopleServiceSynchronized.inMemory.size()).isEqualTo(3);
    }

    @Test
    void canAddSomePeopleWithLock(){
        PeopleServiceLock peopleServiceLock = new PeopleServiceLock(3);

        peopleServiceLock.addTasks(
            peopleServiceLock.addPerson(Main.PEOPLE_EXAMPLE.get(3)),
            peopleServiceLock.addPerson(Main.PEOPLE_EXAMPLE.get(2)),
            peopleServiceLock.addPerson(Main.PEOPLE_EXAMPLE.get(1)));

        peopleServiceLock.executeTasks();

        assertThat(peopleServiceLock.inMemory.size()).isEqualTo(3);
    }

    @Test
    void canDeleteSomePeopleWithSynchronized(){
        PeopleServiceSynchronized peopleServiceSynchronized = new PeopleServiceSynchronized(3);

        peopleServiceSynchronized.addTasks(
            peopleServiceSynchronized.addPerson(Main.PEOPLE_EXAMPLE.get(3)),
            peopleServiceSynchronized.addPerson(Main.PEOPLE_EXAMPLE.get(2)),
            peopleServiceSynchronized.addPerson(Main.PEOPLE_EXAMPLE.get(1)));

        peopleServiceSynchronized.executeTasks();

        peopleServiceSynchronized.addTasks(
            peopleServiceSynchronized.deletePerson(Main.PEOPLE_EXAMPLE.get(3).id()),
            peopleServiceSynchronized.deletePerson(Main.PEOPLE_EXAMPLE.get(2).id()),
            peopleServiceSynchronized.deletePerson(Main.PEOPLE_EXAMPLE.get(1).id())
        );

        peopleServiceSynchronized.executeTasks();

        assertThat(peopleServiceSynchronized.inMemory.size()).isEqualTo(0);
    }

    @Test
    void canDeleteSomePeopleWithLock(){
        PeopleServiceLock peopleServiceLock = new PeopleServiceLock(3);

        peopleServiceLock.addTasks(
            peopleServiceLock.addPerson(Main.PEOPLE_EXAMPLE.get(3)),
            peopleServiceLock.addPerson(Main.PEOPLE_EXAMPLE.get(2)),
            peopleServiceLock.addPerson(Main.PEOPLE_EXAMPLE.get(1)));

        peopleServiceLock.executeTasks();

        peopleServiceLock.addTasks(
            peopleServiceLock.deletePerson(Main.PEOPLE_EXAMPLE.get(3).id()),
            peopleServiceLock.deletePerson(Main.PEOPLE_EXAMPLE.get(2).id()),
            peopleServiceLock.deletePerson(Main.PEOPLE_EXAMPLE.get(1).id())
        );

        peopleServiceLock.executeTasks();

        assertThat(peopleServiceLock.inMemory.size()).isEqualTo(0);
    }
}
