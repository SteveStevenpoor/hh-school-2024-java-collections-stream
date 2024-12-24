package tasks;

import common.Area;
import common.Person;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
Имеются
- коллекция персон Collection<Person>
- словарь Map<Integer, Set<Integer>>, сопоставляющий каждой персоне множество id регионов
- коллекция всех регионов Collection<Area>
На выходе хочется получить множество строк вида "Имя - регион". Если у персон регионов несколько, таких строк так же будет несколько
 */
public class Task6 {

    public static Set<String> getPersonDescriptions(
            Collection<Person> persons,
            Map<Integer, Set<Integer>> personAreaIds,
            Collection<Area> areas) {
        Map<Integer, Area> areaIdToArea =
                areas.stream().collect(Collectors.toMap(Area::getId, Function.identity()));
        return persons.stream()
                .flatMap(
                        person -> {
                            Set<Integer> personAreas = personAreaIds.get(person.id());
                            List<String> personRecords = new ArrayList<>();

                            for (Integer areaId : personAreas) {
                                personRecords.add(
                                        person.firstName()
                                                + " - "
                                                + areaIdToArea.get(areaId).getName());
                            }
                            return personRecords.stream();
                        })
                .collect(Collectors.toSet());
    }
}
