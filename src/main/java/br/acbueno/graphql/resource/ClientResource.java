package br.acbueno.graphql.resource;

import static io.smallrye.graphql.client.core.Document.document;
import static io.smallrye.graphql.client.core.Field.field;
import static io.smallrye.graphql.client.core.Operation.operation;
import java.util.List;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import br.acbueno.graphql.model.Country;
import br.acbueno.graphql.model.Person;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.graphql.client.GraphQLClient;
import io.smallrye.graphql.client.Response;
import io.smallrye.graphql.client.core.Document;
import io.smallrye.graphql.client.dynamic.api.DynamicGraphQLClient;

@Path("/client")
public class ClientResource {

    @Inject
    PersonClientApi personClientApi;

    @GET
    @Path("/persons")
    @Blocking
    public List<Person> getAllPersons(){
        return personClientApi.getAllPersons();
    }

    @GET
    @Path("/persons/{id}")
    @Blocking
    public Person getPerson(int id) {
        return personClientApi.getPerson(id);
    }

    @GET
    @Path("/countries")
    @Blocking
    public List<Country> getAllCountries() {
        return personClientApi.getAllCountries();
    }

    @Inject
    @GraphQLClient("query-dynamic")
    DynamicGraphQLClient dynamicGraphQLClient;

    @GET
    @Path("/dynamic")
    @Blocking
    public List<Person> getAllPersonsUsingDynamicClient() throws Exception {
        Document document = document(
                operation(field("allPersons",
                    field("name"))));
            Response response = dynamicGraphQLClient.executeSync(document);
            JsonArray personsArray = response.getData().getJsonArray("allPersons");
            List<Person> persons = response.getList(Person.class, "allPersons");

            return persons;
    }

}
