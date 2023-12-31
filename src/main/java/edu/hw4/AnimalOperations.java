package edu.hw4;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import static java.util.Map.entry;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toMap;

public abstract class AnimalOperations {

    public static final List<Animal> ANIMALS_EXAMPLE = List.of(
        new Animal("Murka", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6, false),
        new Animal("Zuza", Animal.Type.DOG, Animal.Sex.F, 1, 30, 3, false),
        new Animal("Popka Durak Pervyi", Animal.Type.BIRD, Animal.Sex.M, 1, 10, 2, false),
        new Animal("Guppy", Animal.Type.FISH, Animal.Sex.F, 2, 5, 1, false),
        new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 8, 1, 1, true),
        new Animal("Dangerous Spider", Animal.Type.SPIDER, Animal.Sex.F, 0, 35, 3, true),
        new Animal("Shark", Animal.Type.FISH, Animal.Sex.F, 20, 1000, 3000, true),
        new Animal("Sharik", Animal.Type.DOG, Animal.Sex.M, 8, 100, 35, true)
    );
    public static final List<Animal> ANIMALS_EXAMPLE_SECOND = List.of(
        new Animal("Murka5", Animal.Type.CAT, Animal.Sex.M, -4, 400, 6, false),
        new Animal("Zazz", Animal.Type.FISH, Animal.Sex.F, 1, 300, 350, false),
        new Animal("Popka Durak", Animal.Type.BIRD, Animal.Sex.M, 1, 10, 2, false),
        new Animal("Guppy&", Animal.Type.FISH, Animal.Sex.F, 2, 5, 1, false),
        new Animal("Spider(", Animal.Type.SPIDER, Animal.Sex.M, 11, 1, 1, true),
        new Animal("Dangerous SpiderAAA", Animal.Type.SPIDER, Animal.Sex.F, 0, 35, 300, true),
        new Animal("Shark Shark", Animal.Type.FISH, Animal.Sex.F, 20, 1000, 1000, true),
        new Animal("Sharig", Animal.Type.DOG, Animal.Sex.M, 8, 100, 35, true)
    );

    //#1
    public static List<Animal> fromSmallToBig(List<Animal> animals) {
        return animals.stream().sorted(Comparator.comparing(Animal::height)).collect(Collectors.toList());
    }

    //#2
    public static List<Animal> fromHeavyToLight(List<Animal> animals, int k) {
        return animals.stream().sorted(Comparator.comparing(Animal::weight).reversed()).limit(k)
            .collect(Collectors.toList());
    }

    //#3
    public static Map<Animal.Type, Integer> speciesRange(List<Animal> animals) {
        return animals.stream().collect(groupingBy(Animal::type, summingInt(animal -> 1)));
    }

    //#4
    public static Animal longestName(List<Animal> animals) {
        return animals.stream().max(Comparator.comparing(animal ->
            animal.name().length())).orElseThrow();
    }

    //#5
    public static Animal.Sex mostFrequent(List<Animal> animals) {
        return animals.stream()
            .collect(groupingBy(Animal::sex))
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByKey())
            .max(Comparator.comparing(sexListEntry -> sexListEntry.getValue().size()))
            .orElseThrow()
            .getKey();
    }

    //#6
    public static Map<Animal.Type, Animal> heaviestAnimal(List<Animal> animals) {
        return animals.stream().sorted(Comparator.comparing(Animal::weight).reversed())
            .collect(groupingBy(Animal::type))
            .entrySet()
            .stream()
            .map(k ->
                entry(k.getKey(), k.getValue().getFirst())).collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue
            ));
    }

    //#7
    public static Animal mostOldKAnimal(List<Animal> animals, int k) {
        return animals.stream().sorted(Comparator.comparing(Animal::age)).toList().get(k + 1);
    }

    //#8
    public static Optional<Animal> mostHeaviestKAnimalLowerK(List<Animal> animals, int k) {
        return animals.stream().filter(animal -> animal.height() < k).max(Comparator.comparing(Animal::weight));
    }

    //#9
    public static int allPawsNumber(List<Animal> animals) {
        return animals.stream().map(Animal::paws).reduce(0, Integer::sum);
    }

    //#10
    public static List<Animal> ageNumberNotPawsNumber(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.age() != animal.paws()).toList();
    }

    //#11
    @SuppressWarnings("MagicNumber")
    public static List<Animal> canByteAndMoreThan100(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.bites() && animal.height() > 100).toList();
    }

    //#12
    public static int numberAnimalsWeightMoreHeight(List<Animal> animals) {
        return (int) animals.stream().filter(animal -> animal.weight() > animal.height()).count();
    }

    //#13
    public static List<Animal> namesInMoreTwoWords(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.name().split(" ").length > 2).toList();
    }

    //#14
    public static boolean isDogMoreKHeight(List<Animal> animals, int k) {
        return !animals.stream().filter(animal -> animal.type() == Animal.Type.DOG && animal.height() > k).toList()
            .isEmpty();
    }

    //#15
    public static int sumWeightFromKToLAge(List<Animal> animals, int k, int l) {
        return animals.stream().filter(animal -> animal.age() < l && animal.age() > k)
            .mapToInt(Animal::weight).sum();
    }

    //#16
    public static List<Animal> tripleSorting(List<Animal> animals) {
        return animals.stream()
            .sorted(Comparator.comparing(Animal::type)
                .thenComparing(Animal::sex)
                .thenComparing(Animal::name))
            .toList();
    }

    //#17
    public static boolean spidersBitesMoreDogs(List<Animal> animals) {
        Map<Animal.Type, List<Animal>> spAndDogs = animals.stream()
            .filter(animal ->
                animal.type() == Animal.Type.DOG && animal.bites()
                    || animal.type() == Animal.Type.SPIDER && animal.bites())
            .collect(groupingBy(Animal::type));
        return spAndDogs.get(Animal.Type.DOG).size() > spAndDogs.get(Animal.Type.SPIDER).size();
    }

    //#18
    public static Animal heaviestFishEver(List<List<Animal>> animalLists) {
        return animalLists.stream()
            .flatMap(Collection::stream)
            .filter(animal ->
                animal.type() == Animal.Type.FISH)
            .max(Comparator.comparing(Animal::weight))
            .orElseThrow();
    }

    //#19
    public static Map<String, Set<ValidationError>> animalRecordsErrors(List<Animal> animals) {
        ValidationError ve = new ValidationError();
        return animals.stream()
            .collect(toMap(Animal::name, ve::checkAnimal))
            .entrySet()
            .stream()
            .filter(entry ->
                !entry.getValue().isEmpty())
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    //#20
    public static Map<String, String> errorFields(List<Animal> animals) {
        return animalRecordsErrors(animals)
            .entrySet()
            .stream()
            .filter(stringSetEntry -> !stringSetEntry.getValue().isEmpty())
            .map(k ->
                entry(
                    k.getKey(),
                    k.getValue().stream().map(it -> it.toString().split(":")[0]).distinct()
                        .collect(Collectors.joining(", next error field: "))
                ))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
