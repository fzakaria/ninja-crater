package jackson;

import static java.util.Arrays.asList;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import ninja.exceptions.NinjaException;
import ninja.utils.NinjaProperties;

import java.util.HashSet;
import java.util.Set;


public class CraterModule extends SimpleModule {



    private final ObjectMapper mapper;
    private final NinjaProperties ninjaProperties;

    public CraterModule(ObjectMapper mapper, NinjaProperties ninjaProperties) {
        super("CraterModule", new Version(0,0,1,null,"me.crater","api"));
        this.mapper = mapper;
        this.ninjaProperties = ninjaProperties;
    }

    @Override
    public void setupModule(SetupContext context) {

        context.setMixInAnnotations(NinjaException.class, NinjaExceptionMixin.class);
        context.setMixInAnnotations(Throwable.class, ThrowableMixin.class);
        //We only want to provide the stack trace if we are in Dev/Test otherwise we are leaking information
        Set<String> exceptionPropertiesToExclude = new HashSet<>();
        if (ninjaProperties.isProd()) {
            exceptionPropertiesToExclude.add("stackTrace");
        }
        FilterProvider filters = new SimpleFilterProvider().addFilter("exceptionFilter",
                SimpleBeanPropertyFilter.serializeAllExcept(exceptionPropertiesToExclude));
        mapper.setFilters(filters);

    }

}
