package co.com.fxmanager.auth.domain.services.config;

import org.codehaus.jackson.map.DeserializationConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.fasterxml.jackson.annotation.JsonInclude;

@Configuration
public class SyncronicWebFluxConfig {

	private static final String THREAD_NAME_PREFIX = "default_task_executor_thread";

	@Bean
	public TaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(4);
		executor.setMaxPoolSize(4);
		executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
		executor.initialize();
		return executor;
	}

//	@Bean
//	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
//	    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
//	    builder.serializationInclusion(JsonInclude.Include.NON_NULL);
//	    builder.featuresToDisable(DeserializationConfig.Feature.USE_GETTERS_AS_SETTERS);
//	    return builder;
//	}
}
