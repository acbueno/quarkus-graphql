package br.acbueno.graphql.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import br.acbueno.graphql.model.Country;
import br.acbueno.graphql.model.Person;
import lombok.Getter;

@ApplicationScoped
public class PersonService {

    @Getter
    List<Person> persons = new ArrayList<>();

    @Getter
    List<Country> countries = new ArrayList<>();


    public PersonService() {
       Country c1 = new Country("Brasil", "BR");
       Country c2 = new Country("England", "EN");

       Person p1 = new Person("Pele", c1);
       Person p2 = new Person("Hamilton", c2);

       persons.add(p1);
       persons.add(p2);

       countries.add(c1);
       countries.add(c2);

    }

    public Country getCountry(int id) {
        return countries.get(id);
    }

    public Person getPerson(int id) {
        return persons.get(id);
    }

    public List<Person> getPersonByCity(Country country) {
        return persons.stream()
                .filter(person -> person.getCountry().equals(country))
                .collect(Collectors.toList());
    }

    public void addPerson(Person person) {
        persons.add(person);
        countries.add(person.getCountry());
    }

    public List<Person> getPersonByName(String name) {
        return persons.stream()
                .filter(person -> person.getName().equals(name))
                .collect(Collectors.toList());
    }

    public Person deletePerson(int id) {
        return persons.remove(id);
    }

}
