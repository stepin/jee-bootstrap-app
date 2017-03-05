package name.stepin.templateapp.example;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExampleService {

    public void example() {
        ExampleDTO exampleDTO = new ExampleDTO();
        exampleDTO.setName("name");
        log.info(exampleDTO.toString());
    }
}
