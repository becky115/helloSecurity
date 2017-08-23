package com.becky.security.mapper;

import java.util.Map;

public interface UserMapper {

	public abstract Map<String, Object> select(String userId);

}
