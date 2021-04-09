import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import ru.netology.pyas.population.Education;
import ru.netology.pyas.population.Person;
import ru.netology.pyas.population.Sex;

public class Main {

    public static void main(String[] args) {
        Collection<Person> population = getPopulationData();

        long underageCount = population.stream()
            .filter(person -> person.getAge() < 18)
            .count();

        List<String> conscriptSurnames = population.stream()
            .filter(person -> person.getAge() >= 18)
            .filter(person -> person.getAge() <= 27)
            .map(person -> person.getFamily())
            .collect(Collectors.toList());

        List<Person> workersList = population.stream()
            .filter(person -> person.getAge() >= 18)
            .filter(person -> person.getAge() <= (person.getSex() == Sex.MAN ? 65 : 60))
            .sorted(Comparator.comparing(Person::getFamily))
            .collect(Collectors.toList());
    }

    private static Collection<Person> getPopulationData() {
        Random rng = new Random(0);

        String[] names = {"Jack", "Connor", "Harry", "George", "Samuel", "John"};
        String[] surnames = {"Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown"};
        Sex[] sexes = Sex.values();
        Education[] educations = Education.values();

        Collection<Person> population = new ArrayList<>();

        for (int i = 0; i < 10_000_000; ++i) {
            population.add(new Person(
                getRandom(rng, names),
                getRandom(rng, surnames),
                rng.nextInt(100),
                getRandom(rng, sexes),
                getRandom(rng, educations)
            ));
        }

        return population;
    }

    private static <T> T getRandom(Random rng, T[] arr) {
        return arr[rng.nextInt(arr.length)];
    }

}