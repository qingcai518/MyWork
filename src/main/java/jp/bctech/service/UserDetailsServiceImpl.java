package jp.bctech.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = null;
		try {
			
		} catch (Exception e) {
			throw new UsernameNotFoundException("user not found");
		}
		
		if (user == null) {
			throw new UsernameNotFoundException("user not found : " + email);
		}
		
		return new LoginUser(user);
	}
}
