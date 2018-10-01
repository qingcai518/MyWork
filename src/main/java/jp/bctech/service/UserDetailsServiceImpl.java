package jp.bctech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import jp.bctech.entity.User;
import jp.bctech.entity.LoginUser;
import jp.bctech.repository.UserRepository;

public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = null;
		try {
			User domainUser = repository.findByEmail(email);
			if (domainUser == null) {
				throw new UsernameNotFoundException("user not found: " + email);
			} else {
				user = new User();
				user.setName(domainUser.getName());
				user.setEmail(domainUser.getEmail());
				user.setId(domainUser.getId());
				user.setCreatedAt(domainUser.getCreatedAt());
				user.setPassword(domainUser.getPassword());
				user.setUpdateAt(domainUser.getUpdateAt());
			}
 		} catch (Exception e) {
			throw new UsernameNotFoundException("user not found: " + email);
		}
		
		return new LoginUser(user);
	}
}
