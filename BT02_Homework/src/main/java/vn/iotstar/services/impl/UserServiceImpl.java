package vn.iotstar.services.impl;

import java.sql.Date;

import vn.iotstar.dao.UserDao;
import vn.iotstar.dao.impl.UserDaoImpl;
import vn.iotstar.models.User;
import vn.iotstar.services.UserService;

public class UserServiceImpl implements UserService {
	UserDao userDao = new UserDaoImpl();
	
	@Override
    public void insert(User user) {
        userDao.insert(user);
    }

    @Override
    public boolean register(String username, String password, String email, String fullname, String phone) {
        if (checkExistUsername(username) || checkExistEmail(email) || checkExistPhone(phone)) {
            return false; // Trùng lặp
        }

        long millis = System.currentTimeMillis();
        Date createdDate = new Date(millis);
        User newUser = new User();
        newUser.setUserName(username);
        newUser.setPassWord(password); // Nên hash ở đây nếu có BCrypt
        newUser.setEmail(email);
        newUser.setFullName(fullname);
        newUser.setPhone(phone);
        newUser.setRoleid(5); // Mặc định role member
        newUser.setCreatedDate(createdDate);

        userDao.insert(newUser);
        return true;
    }

    @Override
    public boolean checkExistEmail(String email) {
        return userDao.checkExistEmail(email);
    }

    @Override
    public boolean checkExistUsername(String username) {
        return userDao.checkExistUsername(username);
    }

    @Override
    public boolean checkExistPhone(String phone) {
        return userDao.checkExistPhone(phone);
    }
}
