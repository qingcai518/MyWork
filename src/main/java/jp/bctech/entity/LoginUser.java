package jp.bctech.entity;

import org.springframework.security.core.authority.AuthorityUtils;

public class LoginUser extends org.springframework.security.core.userdetails.User {
	private static final long serialVersionUID = -5282875209239027918L;
	private final User user;

	public LoginUser(User user) {
		super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList("ROLE_USER"));
		this.user = user;
	}

	public User getUser() {
		return user;
	}
}