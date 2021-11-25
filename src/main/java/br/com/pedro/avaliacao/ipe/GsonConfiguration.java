package br.com.pedro.avaliacao.ipe;

import org.springframework.boot.autoconfigure.gson.GsonBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.pedro.avaliacao.ipe.converters.SpringfoxJsonToGsonAdapter;
import springfox.documentation.spring.web.json.Json;

@Configuration
public class GsonConfiguration {

    @Bean
    public GsonBuilderCustomizer typeAdapterRegistration() {
        return builder -> {
            builder.registerTypeAdapter(Json.class, new SpringfoxJsonToGsonAdapter());
        };
    }
}