package com.cg.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cg.Entity.UserEntity;
import com.cg.exception.CustomException;
import com.cg.repository.UserRepository;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private UserRepository userRepo;
	
	List<UserEntity> users = new ArrayList<>();
	
	public UserDaoImpl() {
//		users.add(new UserEntity());
//		users.add(new UserEntity());
//		users.add(new UserEntity());
//		users.add(new UserEntity());
//		
	}

	@Override
	public UserEntity getUserProfile(String email) {
		// TODO Auto-generated method stub
		return userRepo.getByEmail(email);
	}

	@Override
	public UserEntity updateUserProfile(UserEntity user, String userId) throws CustomException  {
		// TODO Auto-generated method stub
		UserEntity ue = userRepo.findById(userId).get();
		if(!ue.equals(null)) {
			userRepo.save(ue);
		}
		else {
			throw new CustomException("No user found by Id");
		}
		return ue;
		
	}

	@Override
	public int registerUser(UserEntity user) {
		// TODO Auto-generated method stub
		userRepo.save(user);
		return 1;
	}

	@Override
	public int loginUser(UserEntity user) {
		// TODO Auto-generated method stub
		return 1;
	}

}
