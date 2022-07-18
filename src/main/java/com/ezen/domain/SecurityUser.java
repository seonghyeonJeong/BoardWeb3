package com.ezen.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User {

	public SecurityUser(Member member) {
		super(member.getId(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));
	}


}
