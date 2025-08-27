package vn.iotstar.services;

import vn.iotstar.models.UserModel;

public interface UserService {
	
	UserModel login(String username, String password);
	UserModel findByUsername(String username);
	
}
