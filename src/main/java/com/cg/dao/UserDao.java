package com.cg.dao;

import com.cg.Entity.UserEntity;
import com.cg.exception.CustomException;

public interface UserDao {
	public UserEntity getUserProfile(String email);
	public UserEntity updateUserProfile(UserEntity user, String userId) throws CustomException;
	public int registerUser(UserEntity user);
	public int loginUser(UserEntity user);
}
