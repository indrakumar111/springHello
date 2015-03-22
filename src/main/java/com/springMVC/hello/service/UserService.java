package com.springMVC.hello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springMVC.hello.dao.UserDao;
import com.springMVC.hello.model.User;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	public void saveUser(User user) {
		userDao.saveUser(user);
	}

	public User checkLogin(String username, String password) {
		return userDao.checkLogin(username.trim(), password.trim());
	}

	public List<User> searchAllUser() {
		return userDao.serachAllUser();
	}

	public User EditUser(String userId) {

		return userDao.getUser(userId);
	}

	public void deleteUser(String userId) {

		userDao.deleteUser(userId);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

}
