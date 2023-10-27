package edu.hw4;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import static java.util.Map.entry;

public class AnimalOperations {

    public static final List<Animal> ANIMALS_EXAMPLE = List.of(
        new Animal("Murka", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6, false),
        new Animal("Zuza", Animal.Type.DOG, Animal.Sex.F, 1, 30, 3, false),
        new Animal("Popka Durak Pervyi", Animal.Type.BIRD, Animal.Sex.M, 1, 10, 2, false),
        new Animal("Guppy", Animal.Type.FISH, Animal.Sex.F, 2, 5, 1, false),
        new Animal("Spider", Animal.Type.SPIDER, Animal.Sex.M, 11, 1, 1, true),
        new Animal("DangerousSpider", Animal.Type.SPIDER, Animal.Sex.F, 0, 35, 3, true),
        new Animal("Shark", Animal.Type.FISH, Animal.Sex.F, 20, 1000, 3000, true),
        new Animal("Sharik", Animal.Type.DOG, Animal.Sex.M, 8, 100, 35, true)
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
        return animals.stream().collect(Collectors.groupingBy(Animal::type))
            .entrySet()
            .stream()
            .map(k ->
                entry(k.getKey(), k.getValue().size())).collect(Collectors.toMap(Map.Entry::getKey,
                Map.Entry::getValue
            ));
    }

    //#4
    public static Animal longestName(List<Animal> animals) {
        return animals.stream().max(Comparator.comparing(animal ->
            animal.name().length())).orElseThrow();
    }

    //#5
    public static Animal.Sex mostFrequent(List<Animal> animals) {
        return animals.stream()
            .collect(Collectors.groupingBy(Animal::sex))
            .entrySet()
            .stream()
            .max(Comparator.comparing(sexListEntry -> sexListEntry.getValue().size()))
            .orElseThrow()
            .getKey();
    }

    //#6
    public static Map<Animal.Type, Integer> heaviestAnimal(List<Animal> animals) {
        return animals.stream().sorted(Comparator.comparing(Animal::weight).reversed()).collect(Collectors.groupingBy(Animal::type))
            .entrySet()
            .stream()
            .map(k ->
                entry(k.getKey(), k.getValue().getFirst().weight())).collect(Collectors.toMap(Map.Entry::getKey,
                Map.Entry::getValue
            ));
    }

    //#7
    public static Animal mostOldKAnimal(List<Animal> animals, int k) {
        return animals.stream().sorted(Comparator.comparing(Animal::age)).toList().get(k);
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
        return animals.stream().filter(animal -> animal.age()!= animal.paws()).toList();
    }

    //#11
    public static List<Animal> canByteAndMoreThan100(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.bites() && animal.height()>100).toList();
    }

    //#12
    public static int numberAnimalsWithWeightMoreHeight(List<Animal> animals) {
        return animals.stream().filter(animal -> animal.weight()>animal.height()).toList().size();
    }

    //#13
    public static List<Animal> namesInMoreTwoWords(List<Animal> animals){
        return animals.stream().filter(animal -> animal.name().split(" ").length > 2).toList();
    }

    //#14
    public static boolean IsDogMoreKHeight(List<Animal> animals, int k){
        return animals.stream().filter(animal -> animal.type() == Animal.Type.DOG && animal.height() > k).toList().isEmpty();
    }

    //#15
    public static int sumWeightFromKToLAge(List<Animal> animals, int k, int l) {
        return animals.stream().filter(animal -> animal.age() < l && animal.age() > k).map(Animal::weight).reduce(0, Integer::sum);
    }

    //#16
    
}