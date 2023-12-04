package edu.hw3;

import edu.hw3.task5.Contacts;
import edu.hw3.task5.Person;
import edu.hw3.task5.SortingType;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    @DisplayName("One contact without surname, Asc")
    void notEmptyContactsAsc() {
        var cts = new Contacts();
        var cs = List.of(
            new Person("John", "Locke"),
            new Person("Thomas"),
            new Person("David", "Hume"),
            new Person("Rene", "Descartes")
        );
        cts.addContacts(cs);

        var result = cts.parseContacts(cs, SortingType.ASC);

        assertThat(result.get(0)).isEqualTo(new Person("Rene", "Descartes"));
    }

    @Test
    @DisplayName("One contact without surname, Desc")
    void notEmptyContactsDesc() {
        var cts = new Contacts();
        var cs = List.of(
            new Person("John", "Locke"),
            new Person("Thomas"),
            new Person("David", "Hume"),
            new Person("Rene", "Descartes")
        );
        cts.addContacts(cs);

        var result = cts.parseContacts(cs, SortingType.DESC);

        assertThat(result.get(0)).isEqualTo(new Person("Thomas"));
    }

    @Test
    @DisplayName("null, Desc")
    void nullContactsDesc() {
        var ects = new Contacts();

        var result = ects.parseContacts(null, SortingType.DESC);

        assertThat(result).isEqualTo(List.of());
    }

    @Test
    @DisplayName("null, Asc")
    void nullContactsAsc() {
        var ects = new Contacts();

        var result = ects.parseContacts(null, SortingType.ASC);

        assertThat(result).isEqualTo(List.of());
    }

    @Test
    @DisplayName("empty, Desc")
    void emptyContactsDesc() {
        var ects = new Contacts();

        var result = ects.parseContacts(List.of(), SortingType.DESC);

        assertThat(result).isEqualTo(List.of());
    }

    @Test
    @DisplayName("empty, Asc")
    void emptyContactsAsc() {
        var ects = new Contacts();

        var result = ects.parseContacts(List.of(), SortingType.ASC);

        assertThat(result).isEqualTo(List.of());
    }
}
