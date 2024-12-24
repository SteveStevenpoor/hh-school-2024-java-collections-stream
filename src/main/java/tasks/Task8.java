package tasks;

import common.Person;
import common.PersonService;
import common.PersonWithResumes;
import common.Resume;

import java.util.*;
import java.util.stream.Collectors;

/*
 Еще один вариант задачи обогащения
 На вход имеем коллекцию персон
 Сервис умеет по personId искать их резюме (у каждой персоны может быть несколько резюме)
 На выходе хотим получить объекты с персоной и ее списком резюме
*/
public class Task8 {
    private final PersonService personService;

    public Task8(PersonService personService) {
        this.personService = personService;
    }

    public Set<PersonWithResumes> enrichPersonsWithResumes(Collection<Person> persons) {
        Set<Resume> resumes =
                personService.findResumes(
                        persons.stream().map(Person::id).collect(Collectors.toSet()));
        Map<Integer, PersonWithResumes> personIdToPWR = new HashMap<>();

        Set<PersonWithResumes> personsWithResumes =
                persons.stream()
                        .map(
                                person ->
                                        personIdToPWR.put(
                                                person.id(),
                                                new PersonWithResumes(person, new HashSet<>())))
                        .collect(Collectors.toSet());
        for (Resume resume : resumes) {
            personIdToPWR.get(resume.personId()).resumes().add(resume);
        }

        return new HashSet<>(personIdToPWR.values());
    }
}
