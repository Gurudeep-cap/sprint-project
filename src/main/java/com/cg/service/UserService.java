package com.cg.service;

import com.cg.Entity.UserEntity;
import com.cg.exception.CustomException;

public interface UserService {
	public UserEntity getUserProfile(String email);
	public UserEntity updateUserProfile(UserEntity user, String userId) throws CustomException;
	public int registerUser(UserEntity user);
	public int loginUser(UserEntity user);

}
