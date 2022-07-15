package br.acbueno.graphql.service;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Source;
import br.acbueno.graphql.model.Country;
import br.acbueno.graphql.model.Person;


@GraphQLApi
public class GraphQlService {

    @Inject
    PersonService personService;


    @Query("allCountries")
    @Description("Get all Countries")
    public List<Country> getAllCountries(){
        return personService.getCountries();
    }

    @Query()
    @Description("Get a country")
    public Country getCountry(@Name("countryId") int id) {
        return personService.getCountry(id);
    }


    @Query
    @Description("Get a person")
    public Person getPerson(@Name("personId") int id) {
        return personService.getPerson(id);
    }

    @Query("allPersons")
    @Description("get all persons")
    public List<Person> getAllPersons(){
        return personService.getPersons();
    }

    public List<Person> persons(@Source Country country) {
        return personService.getPersonByCity(country);
    }

    @Mutation
    public Person createPerson(@Name("person") Person person) {
        personService.addPerson(person);
        return person;
    }

}
