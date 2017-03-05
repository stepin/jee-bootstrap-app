package name.stepin.templateapp.domain;

import lombok.extern.slf4j.Slf4j;
import name.stepin.templateapp.rest.vo.ExampleDTO;

import javax.ejb.Stateless;

@Stateless
@Slf4j
public class ExampleService {

    public ExampleDTO example() {
        ExampleDTO exampleDTO = new ExampleDTO();
        exampleDTO.setName("name");
        log.info(exampleDTO.toString());
        return exampleDTO;
    }
}
