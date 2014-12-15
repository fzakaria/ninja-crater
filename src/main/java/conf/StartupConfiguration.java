package conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import jackson.CraterModule;
import ninja.lifecycle.Start;
import ninja.utils.NinjaProperties;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class StartupConfiguration {

    private final ObjectMapper mapper;

    private final NinjaProperties ninjaProperties;

    @Inject
    public StartupConfiguration(ObjectMapper mapper,NinjaProperties ninjaProperties) {
        this.mapper = mapper;
        this.ninjaProperties = ninjaProperties;
    }

    @Start(order = 90)
    public void configureObjectMapper() {
        // Adding Joda Time parsing and rendering support to Jackson
        mapper.registerModule(new JodaModule());
        mapper.registerModule(new CraterModule(mapper,ninjaProperties));
    }
}
