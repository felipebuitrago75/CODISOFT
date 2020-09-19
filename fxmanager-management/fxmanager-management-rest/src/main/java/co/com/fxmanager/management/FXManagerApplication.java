package co.com.fxmanager.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import co.com.fxmanager.auth.config.SerializationConfig;
import co.com.fxmanager.auth.domain.services.config.SyncronicWebFluxConfig;
import co.com.fxmanager.auth.security.config.AuthorizationServerConfig;
import co.com.fxmanager.auth.security.config.ResourceServerConfig;
import co.com.fxmanager.auth.security.config.SecurityConfig;



@SpringBootApplication
@EnableJpaRepositories("co.com.fxmanager")
@EntityScan( "co.com.fxmanager" )
@ComponentScan("co.com.fxmanager")
@Import(value = { SecurityConfig.class, AuthorizationServerConfig.class, ResourceServerConfig.class,SyncronicWebFluxConfig.class,SerializationConfig.class })
public class FXManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FXManagerApplication.class, args);
	}
}
