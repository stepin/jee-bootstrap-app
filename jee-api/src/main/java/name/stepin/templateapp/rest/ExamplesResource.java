package name.stepin.templateapp.rest;

import lombok.extern.slf4j.Slf4j;
import name.stepin.templateapp.domain.ExampleService;
import name.stepin.templateapp.rest.vo.ExampleDTO;
import name.stepin.templateapp.utils.MediaTypeUtf8;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Path("examples")
@Stateless
@Slf4j
@Produces({MediaTypeUtf8.APPLICATION_JSON, MediaTypeUtf8.APPLICATION_XML})
public class ExamplesResource {

    @EJB
    private ExampleService exampleService;

    @GET
    public List<ExampleDTO> list() {
        return Collections.singletonList(exampleService.example());
    }

    @GET
    @Path("")
    public ExampleDTO show(@Valid @NotNull Integer id) {
        return exampleService.example();
    }
}
