package name.stepin.templateapp.rest;

import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("echo")
@Stateless
@Slf4j
public class EchoResource {

    @GET
    public String echo(@QueryParam("msg") String msg) {
        log.info("Echo {}", msg);
        return msg;
    }
}
