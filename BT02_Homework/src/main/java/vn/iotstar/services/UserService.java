package vn.iotstar.services;

import vn.iotstar.models.User;

public interface UserService {
	void insert(User user);
	  boolean register(String username, String password, String email, String fullname, String phone);
	  boolean checkExistEmail(String email);
	  boolean checkExistUsername(String username);
	  boolean checkExistPhone(String phone);
}
