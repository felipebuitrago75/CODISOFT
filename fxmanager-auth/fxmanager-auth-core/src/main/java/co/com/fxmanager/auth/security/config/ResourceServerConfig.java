package co.com.fxmanager.auth.security.config;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import co.com.fxmanager.auth.domain.entities.AccessType;
import co.com.fxmanager.auth.domain.entities.Permission;
import co.com.fxmanager.auth.domain.entities.Resource;
import co.com.fxmanager.auth.domain.entities.Role;
import co.com.fxmanager.auth.persistence.repositories.PermissionRepository;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "resource_id";

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.resourceId(RESOURCE_ID).stateless(false);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		var authorizeRequests = http.anonymous().disable().authorizeRequests().antMatchers("/security/login/**").permitAll();
		Map<Resource, Set<Role>> rolesByResources = getRolesByResources();
		rolesByResources.forEach((resource, rolesList) -> {
			String roles = rolesList.stream().map(role -> "'"+role.getName() + "'")
					.collect(Collectors.joining(","));
			authorizeRequests.antMatchers(getHttpMethod(resource.getAccessType()), resource.getUri())
					.access("hasAnyRole(" + roles + ")");
		});
		//authorizeRequests.anyRequest().denyAll();
		authorizeRequests.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
	}

	private Map<Resource, Set<Role>> getRolesByResources() {
		Map<Resource, Set<Role>> rolesByResources = Maps.newHashMap();
		List<Permission> permissions = permissionRepository.getPermissionsWithRolesByResource();
		permissions.stream().forEach(permission -> 
			permission.getFunctionality().getResources().stream()
					.filter(r -> permission.getAccessTypes().contains(r.getAccessType())).forEach(resource -> {
						if (rolesByResources.containsKey(resource)) {
							rolesByResources.get(resource).add(permission.getRole());
						} else {
							rolesByResources.put(resource, Sets.newHashSet(permission.getRole()));
						}
					})	
		);
		return rolesByResources;
	}

	private HttpMethod getHttpMethod(AccessType accessType) {
		switch (accessType) {
		case CREATE:
			return HttpMethod.POST;
		case UPDATE:
			return HttpMethod.PUT;
		case READ:
			return HttpMethod.GET;
		case DELETE:
			return HttpMethod.DELETE;
		}
		return null;
	}

}