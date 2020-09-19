package co.com.fxmanager.auth.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.fxmanager.auth.domain.entities.User;
import co.com.fxmanager.auth.domain.services.UserService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/security")
public class SecurityController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<User> create(@RequestBody User user) {
		return Mono.justOrEmpty(userService.save(user));
	}

	@PutMapping(value = "/user/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<User> update(@PathVariable("name") String name, @RequestBody User user) {
		return Mono.justOrEmpty(userService.update(name, user));
	}

}
