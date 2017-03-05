package name.stepin.templateapp.example;

import name.stepin.templateapp.mapper.ExampleMapper;
import name.stepin.templateapp.rest.vo.ExampleDTO;
import name.stepin.templateapp.rest.vo.ExampleDTO2;
import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class ExampleMapperTest {
    @Test
    public void testToSecond() throws Exception {
        ExampleMapper mapper = ExampleMapper.INSTANCE;

        ExampleDTO exampleDTO = new ExampleDTO();
        exampleDTO.setName("name");
        exampleDTO.setDescription("description");
        exampleDTO.setAmount(1);
        exampleDTO.setExtra1("extra1");

        ExampleDTO2 exampleDTO2 = mapper.toSecond(exampleDTO);

        assertEquals(exampleDTO2.getName(), "name");
        assertEquals(exampleDTO2.getInfo(), "description");
        assertEquals(exampleDTO2.getAmount(), (Integer)1);
        assertEquals(exampleDTO2.getExtra2(), null);
    }

    @Test
    public void testFromSecond() throws Exception {
        ExampleMapper mapper = ExampleMapper.INSTANCE;

        ExampleDTO2 exampleDTO2 = new ExampleDTO2();
        exampleDTO2.setName("name");
        exampleDTO2.setInfo("description");
        exampleDTO2.setAmount(1);
        exampleDTO2.setExtra2("extra2");

        ExampleDTO exampleDTO = mapper.fromSecond(exampleDTO2);

        assertEquals(exampleDTO.getName(), "name");
        assertEquals(exampleDTO.getDescription(), "description");
        assertEquals(exampleDTO.getAmount(), 1);
        assertEquals(exampleDTO.getExtra1(), null);
    }

}
