package co.com.fxmanager.auth.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class SerializationConfig {
	@Bean
	JavaTimeModule javatimeModule() {
		return new JavaTimeModule();
	}

	@Bean
	Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
		return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder
				.failOnUnknownProperties(Boolean.FALSE)
				.serializationInclusion(JsonInclude.Include.NON_ABSENT);
	}

	@Bean
	Jackson2JsonEncoder jackson2JsonEncoder(ObjectMapper mapper) {
		return new Jackson2JsonEncoder(mapper);
	}

	@Bean
	Jackson2JsonDecoder jackson2JsonDecoder(ObjectMapper mapper) {
		return new Jackson2JsonDecoder(mapper);
	}

	@Bean
	WebFluxConfigurer webFluxConfigurer(Jackson2JsonEncoder encoder, Jackson2JsonDecoder decoder) {
		return new WebFluxConfigurer() {
			@Override
			public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
				configurer.defaultCodecs().jackson2JsonEncoder(encoder);
				configurer.defaultCodecs().jackson2JsonDecoder(decoder);
			}
		};

	}
}
