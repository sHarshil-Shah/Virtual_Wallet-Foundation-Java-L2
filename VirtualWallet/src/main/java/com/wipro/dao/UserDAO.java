package com.wipro.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wipro.bean.User;

@Repository
@Transactional
public class UserDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	HibernateTemplate hibernateTemplate;

	public void addUser(User p) {
		hibernateTemplate.save(p);
	}

	public void updateUser(User p) {
		hibernateTemplate.saveOrUpdate(p);
	}

	public List<User> listUsers() {
		return hibernateTemplate.loadAll(User.class);
	}

	public User getUserById(int id) {
		return hibernateTemplate.load(User.class, id);
	}

	public void removeUser(int id) {
		hibernateTemplate.delete(getUserById(id));
	}

	public User getUserByUNamePass(User user) {
		List<User> users = listUsers();

		for (User u : users) {
			if (u.getuName().equals(user.getuName()) && u.getPass().equals(user.getPass())) {
				return u;
			}
		}

		return null;
	}
}
