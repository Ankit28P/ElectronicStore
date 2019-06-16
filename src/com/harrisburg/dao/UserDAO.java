package com.harrisburg.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.harrisburg.entity.Users;

public class UserDAO extends JpaDAO<Users> implements GenericDAO<Users> {

	public UserDAO() {
	}

	public Users createUser(Users user) {
		return super.create(user);
	}

	@Override
	public Users update(Users user) {
		return super.update(user);
	}

	@Override
	public Users get(Object id) {
		return super.find(Users.class, id);
	}

	public Users findByEmail(String email) {
		List<Users> listUser = super.findWithNamedQuery("Users.findByEmail", "email", email);

		if (listUser != null && listUser.size() == 1) {
			return listUser.get(0);
		}
		return null;
	}

	public boolean checkLogin(String email, String password) {
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("email", email);
		parameter.put("password", password);

		List<Users> listUsers = super.findWithNamedQuery("Users.checkLogin", parameter);
		if (listUsers.size() == 1) {
			return true;
		}
		return false;
	}

	@Override
	public void delete(Object id) {
		super.delete(Users.class, id);
	}

	@Override
	public List<Users> listAll() {
		return super.findWithNamedQuery("Users.findAll");
	}

	@Override
	public Long count() {
		return super.countWithNamedQuery("Users.countAll");
	}

}
