package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.Entity.UserEntity;
import com.cg.dao.UserDao;
import com.cg.exception.CustomException;

//@Service
@Component
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public UserEntity getUserProfile(String email) {
		// TODO Auto-generated method stub
		return userDao.getUserProfile(email);
	}
	

//	@Override
//	public UserEntity updateUserProfile(UserEntity user, int id) throws CustomException {
//		// TODO Auto-generated method stub
//		return null;
//	}


	@Override
	public UserEntity updateUserProfile(UserEntity user, String userId) throws CustomException  {
		// TODO Auto-generated method stub
		return userDao.updateUserProfile(user, userId);
	}


	@Override
	public int registerUser(UserEntity user) {
		// TODO Auto-generated method stub
		return userDao.registerUser(user);
	}


	@Override
	public int loginUser(UserEntity user) {
		// TODO Auto-generated method stub
		return 0;
	}

}
