package name.stepin.templateapp.example;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExampleMapper {

    ExampleMapper INSTANCE = Mappers.getMapper(ExampleMapper.class);

    @Mapping(target = "info", source = "description")
    @Mapping(target = "extra2", ignore = true)
    ExampleDTO2 toSecond(ExampleDTO in);

    @Mapping(target = "description", source = "info")
    @Mapping(target = "extra1", ignore = true)
    ExampleDTO fromSecond(ExampleDTO2 in);

}
