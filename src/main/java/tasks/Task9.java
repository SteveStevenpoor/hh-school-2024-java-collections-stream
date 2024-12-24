package tasks;

import common.Person;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/*
Далее вы увидите код, который специально написан максимально плохо.
Постарайтесь без ругани привести его в надлежащий вид
P.S. Код в целом рабочий (не везде), комментарии оставлены чтобы вам проще понять чего же хотел автор
P.P.S Здесь ваши правки необходимо прокомментировать (можно в коде, можно в PR на Github)
 */
public class Task9 {
    // Removing head of a list is O(n). Better filter the stream
    public List<String> getNames(List<Person> persons) {
        if (persons.isEmpty()) {
            return Collections.emptyList();
        }

        return persons.subList(1, persons.size()).stream()
                .map(Person::firstName)
                .collect(Collectors.toList());
    }

    // HashSet has unique elements already
    public Set<String> getDifferentNames(List<Person> persons) {
        return new HashSet<>(getNames(persons));
    }

    // Join is more readable
    public static String convertPersonToString(Person person) {
        return String.join(" ", person.firstName(), person.secondName(), person.middleName());
    }

    // I think streams are better overall but idk about making convertPersonToString. AFAIK it's ok
    // for such example
    public Map<Integer, String> getPersonNames(Collection<Person> persons) {
        return persons.stream().collect(Collectors.toMap(Person::id, Task9::convertPersonToString));
    }

    // I am not sure about consistency of persons1. And also it could be unretainable collection.
    // But I think you wanted me to do this
    public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
        return persons1.retainAll(persons2);
    }

    // count() is more convenient
    public long countEven(Stream<Integer> numbers) {
        return numbers.filter(num -> num % 2 == 0).count();
    }

    // Integer.class has it's underlaying value as a hash code itself. So hash set will be sorted on
    // a creation
    public static void listVsSet() {
        List<Integer> integers =
                IntStream.rangeClosed(1, 10000).boxed().collect(Collectors.toList());
        List<Integer> snapshot = new ArrayList<>(integers);
        Collections.shuffle(integers);
        Set<Integer> set = new HashSet<>(integers);
        assert snapshot.toString().equals(set.toString());
    }

    public static void main(String[] argv) {
        listVsSet();
    }
}
