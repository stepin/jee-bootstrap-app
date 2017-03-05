package name.stepin.templateapp.rest;

import lombok.extern.slf4j.Slf4j;
import name.stepin.templateapp.domain.ExampleService;
import name.stepin.templateapp.rest.vo.ExampleDTO;
import name.stepin.templateapp.utils.FlashStore;
import name.stepin.templateapp.utils.MediaTypeUtf8;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import java.util.Collections;
import java.util.List;

@Path("examples")
@Stateless
@Slf4j
@Produces({MediaTypeUtf8.APPLICATION_JSON, MediaTypeUtf8.APPLICATION_XML})
public class ExamplesResource {

    @EJB
    private ExampleService exampleService;

    @EJB
    private FlashStore flashStore;

    @GET
    @Path("new")
    public String newForm() {
        return "";
    }

    @POST
    public void create(@Valid @NotNull ExampleDTO exampleDTO) {

    }

    @GET
    public List<ExampleDTO> index() {
        return Collections.singletonList(exampleService.example());
    }

    @GET
    @Path("{id}/edit")
    public String editForm(@PathParam("id") Integer id) {
        return "";
    }

    @POST
    @Path("{id}")
    public ExampleDTO update(@PathParam("id") Integer id, @Valid @NotNull ExampleDTO exampleDTO) {
        return exampleService.example();
    }

    @GET
    @Path("{id}")
    public ExampleDTO show(@PathParam("id") Integer id) {
        return exampleService.example();
    }

    @DELETE
    @Path("{id}")
    public ExampleDTO delete(@PathParam("id") Integer id) {
        return exampleService.example();
    }
}
