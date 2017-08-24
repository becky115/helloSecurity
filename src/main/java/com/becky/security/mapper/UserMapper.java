package com.becky.security.mapper;

import com.becky.security.domain.UserDomain;

public interface UserMapper {

	public abstract UserDomain select(String userId);

}
