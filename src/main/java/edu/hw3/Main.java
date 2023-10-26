package edu.hw3;

import edu.hw3.task1.Task1;
import edu.hw3.task2.Task2;
import edu.hw3.task3.Task3;
import edu.hw3.task4.Task4;
import edu.hw3.task5.Contacts;
import edu.hw3.task5.Person;
import edu.hw3.task5.SortingType;
import edu.hw3.task6.Stock;
import edu.hw3.task6.StockMarket;
import edu.hw3.task6.StockMarketImpl;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import edu.hw3.task7.NullComparator;
import edu.hw3.task8.BackwardIterator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
       /* LOGGER.info("Hello and welcome!");

        String source = "Hello world!";
        Task1 t1 = new Task1();
        LOGGER.info(t1.atbash(source));
        Task2 t2 = new Task2();
        LOGGER.info(t2.clusterize("((())())(()"));
        Task3 t3 = new Task3();
        LOGGER.info(t3.freqDict(new Integer[] {1, 1, 2, 2}));
        Task4 t4 = new Task4();
        LOGGER.info(t4.convertToRoman(99));
        Contacts cts = new Contacts();
        List<Person> cs = List.of(new Person("John", "Locke"),
            new Person("Thomas"),
            new Person("David", "Hume"),
            new Person("Rene", "Descartes")
        );
        cts.addContacts(cs);
        LOGGER.info(cts.parseContacts(cts.getContacts(), SortingType.ASC));
        LOGGER.info(cts.parseContacts(cts.getContacts(), SortingType.DESC));
        Contacts ects = new Contacts();
        LOGGER.info(ects.parseContacts(null, SortingType.ASC));
        LOGGER.info(ects.parseContacts(List.of(), SortingType.DESC));
        StockMarket s = new StockMarketImpl();
        s.add(new Stock("DFG", 1.0));
        s.add(new Stock("AAA", 2.0));
        s.add(new Stock("YHY", 1.8));
        LOGGER.info(s.mostValuableStock());
        TreeMap<String, String> tm = new TreeMap<>(new NullComparator<>(Comparator.naturalOrder()));
        tm.put(null,null);
        tm.put("am", "am");
        tm.put(null, "ff");
        tm.put("bb","ff");
        LOGGER.info(tm.lastKey());
        LOGGER.info(tm.firstKey());*/
        List<Integer> ints = List.of(1, 2, 3, 4, 5, 6);
        Iterator<Integer> i = new BackwardIterator<>(ints);
        while (i.hasNext()) {
            var element = i.next();
            LOGGER.info(element);
        }
    }
}
