package desi.learn.spring.security;

import static com.google.common.collect.Iterators.transform;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.google.common.base.Function;

public class SecurityService implements SecurityServiceIF {

	@Override
	public String[] getRoles() {
		Collection<? extends GrantedAuthority> col = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		String[] roles = new String[col.size()];
		
		transform(col.iterator(), new Function<GrantedAuthority, String>() {
			@Override
			public String apply(GrantedAuthority input) {
				return input.getAuthority();
			}
		});

		return roles;
	}
}
