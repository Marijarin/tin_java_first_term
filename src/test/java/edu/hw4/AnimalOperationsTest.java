package edu.hw4;

import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnimalOperationsTest {
    @Test
    void biggestSorting() {
        List<Animal> ans = List.of(
            new Animal("Murka", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6, false),
            new Animal("Zuza", Animal.Type.DOG, Animal.Sex.F, 1, 30, 3, false)
        );

        List<Animal> result = AnimalOperations.fromSmallToBig(ans);

        assertThat(result.get(0)).isEqualTo(new Animal("Zuza", Animal.Type.DOG, Animal.Sex.F, 1, 30, 3, false));
    }

    @Test
    void weightReverseSorting(){
        List<Animal> ans = List.of(
            new Animal("Murka", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6, false),
            new Animal("Zuza", Animal.Type.DOG, Animal.Sex.F, 1, 30, 3, false)
        );

        List<Animal> result = AnimalOperations.fromHeavyToLight(ans, 1);

        assertThat(result.get(0)).isEqualTo( new Animal("Murka", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6, false));
    }

    @Test
    void howManyCats(){
        List<Animal> ans = List.of(
            new Animal("Murka", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6, false),
            new Animal("Zuza", Animal.Type.DOG, Animal.Sex.F, 1, 30, 3, false),
            new Animal("Murka", Animal.Type.SPIDER, Animal.Sex.M, 4, 40, 6, false),
            new Animal("Zuza", Animal.Type.CAT, Animal.Sex.F, 1, 30, 3, false)
        );

        Map<Animal.Type, Integer> result = AnimalOperations.speciesRange(ans);

        assertThat(result.get(Animal.Type.CAT)).isEqualTo(2);
    }

    @Test
    void checkLongestName(){
        List<Animal> ans = List.of(
            new Animal("Murka Murka", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6, false),
            new Animal("Zuza Murka", Animal.Type.DOG, Animal.Sex.F, 1, 30, 3, false),
            new Animal("Murka", Animal.Type.SPIDER, Animal.Sex.M, 4, 40, 6, false),
            new Animal("Zuza", Animal.Type.CAT, Animal.Sex.F, 1, 30, 3, false)
        );

        Animal result = AnimalOperations.longestName(ans);

        assertThat(result.name()).isEqualTo("Murka Murka");
    }

    @Test
    void mostFreqSex(){
        List<Animal> ans = List.of(
            new Animal("Murka Murka", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6, false),
            new Animal("Zuza Murka", Animal.Type.DOG, Animal.Sex.F, 1, 30, 3, false),
            new Animal("Murka", Animal.Type.SPIDER, Animal.Sex.M, 4, 40, 6, false),
            new Animal("Zuza", Animal.Type.CAT, Animal.Sex.F, 1, 30, 3, false)
        );

        var result= AnimalOperations.mostFrequent(ans);

        assertThat(result).isEqualTo(Animal.Sex.M);
    }

    @Test
    void heaviestAnimal(){
        List<Animal> ans = List.of(
            new Animal("Murka Murka", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6, false),
            new Animal("Zuza Murka", Animal.Type.DOG, Animal.Sex.F, 1, 30, 3, false),
            new Animal("Murka", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6, false),
            new Animal("Zuza", Animal.Type.CAT, Animal.Sex.F, 1, 30, 3, false)
        );

        var result= AnimalOperations.heaviestAnimal(ans);

        assertThat(result.get(Animal.Type.CAT)).isEqualTo( new Animal("Murka Murka", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6, false));
    }

    @Test
    void mostOldAnimalInRange(){
        List<Animal> ans = List.of(
            new Animal("Murka Murka", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6, false),
            new Animal("Zuza Murka", Animal.Type.DOG, Animal.Sex.F, 2, 30, 3, false),
            new Animal("Murka", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6, false),
            new Animal("Zuza", Animal.Type.CAT, Animal.Sex.F, 1, 30, 3, false)
        );

        var result = AnimalOperations.mostOldKAnimal(ans, 2);

        assertThat(result).isEqualTo(new Animal("Murka", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6, false));
    }

    @Test
    void mostHeaviestKAnimalLowerK(){
        List<Animal> ans = List.of(
            new Animal("Murka Murka", Animal.Type.CAT, Animal.Sex.M, 4, 40, 6, false),
            new Animal("Zuza Murka", Animal.Type.DOG, Animal.Sex.F, 2, 35, 10, false),
            new Animal("Murka", Animal.Type.CAT, Animal.Sex.M, 4, 30, 6, false),
            new Animal("Zuza", Animal.Type.CAT, Animal.Sex.F, 1, 30, 3, false)
        );

        var result = AnimalOperations.mostHeaviestKAnimalLowerK(ans, 35).orElseThrow();

        assertThat(result).isEqualTo(new Animal("Murka", Animal.Type.CAT, Animal.Sex.M, 4, 30, 6, false));
    }

    @Test
    void allPawsNumber(){
        List<Animal> ans = List.of(
            new Animal("Murka Murka", Animal.Type.FISH, Animal.Sex.M, 4, 40, 6, false),
            new Animal("Zuza Murka", Animal.Type.BIRD, Animal.Sex.F, 2, 35, 10, false),
            new Animal("Murka", Animal.Type.CAT, Animal.Sex.M, 4, 30, 6, false),
            new Animal("Zuza", Animal.Type.SPIDER, Animal.Sex.F, 1, 30, 3, false)
        );

        var result = AnimalOperations.allPawsNumber(ans);

        assertThat(result).isEqualTo(14);
    }
    @Test
    void ageNumberNotPawsNumber(){
        List<Animal> ans = List.of(
            new Animal("Murka Murka", Animal.Type.FISH, Animal.Sex.M, 4, 40, 6, false),
            new Animal("Zuza Murka", Animal.Type.BIRD, Animal.Sex.F, 2, 35, 10, false),
            new Animal("Murka", Animal.Type.CAT, Animal.Sex.M, 4, 30, 6, false),
            new Animal("Zuza", Animal.Type.SPIDER, Animal.Sex.F, 8, 30, 3, false)
        );

        var result = AnimalOperations.ageNumberNotPawsNumber(ans);

        assertThat(result).isEqualTo(List.of( new Animal("Murka Murka", Animal.Type.FISH, Animal.Sex.M, 4, 40, 6, false)));
    }

    @Test
    void canByteAndMoreThan100(){
        List<Animal> ans = List.of(
            new Animal("Murka Murka", Animal.Type.FISH, Animal.Sex.M, 4, 140, 6, true),
            new Animal("Zuza Murka", Animal.Type.BIRD, Animal.Sex.F, 2, 35, 10, true),
            new Animal("Murka", Animal.Type.CAT, Animal.Sex.M, 4, 30, 6, false),
            new Animal("Zuza", Animal.Type.SPIDER, Animal.Sex.F, 8, 130, 3, false)
        );

        var result = AnimalOperations.canByteAndMoreThan100(ans);

        assertThat(result).isEqualTo(List.of( new Animal("Murka Murka", Animal.Type.FISH, Animal.Sex.M, 4, 140, 6, true)));
    }

    @Test
    void numberAnimalsWeightMoreHeight(){
        List<Animal> ans = List.of(
            new Animal("Murka Murka", Animal.Type.FISH, Animal.Sex.M, 4, 140, 6, true),
            new Animal("Zuza Murka", Animal.Type.BIRD, Animal.Sex.F, 2, 35, 110, true),
            new Animal("Murka", Animal.Type.CAT, Animal.Sex.M, 4, 6, 6, false),
            new Animal("Zuza", Animal.Type.SPIDER, Animal.Sex.F, 8, 130, 3, false)
        );

        var result = AnimalOperations.numberAnimalsWeightMoreHeight(ans);

        assertThat(result).isEqualTo(1);
    }

    @Test
    void  namesInMoreTwoWords(){
        List<Animal> ans = List.of(
            new Animal("Murka Murka I", Animal.Type.FISH, Animal.Sex.M, 4, 140, 6, true),
            new Animal("Zuza Murka", Animal.Type.BIRD, Animal.Sex.F, 2, 35, 110, true),
            new Animal("Murka", Animal.Type.CAT, Animal.Sex.M, 4, 6, 6, false),
            new Animal("Zuza", Animal.Type.SPIDER, Animal.Sex.F, 8, 130, 3, false)
        );

        var result = AnimalOperations.namesInMoreTwoWords(ans);

        assertThat(result).isEqualTo(List.of(new Animal("Murka Murka I", Animal.Type.FISH, Animal.Sex.M, 4, 140, 6, true)));
    }
    


}
