package dao;

public interface UserService {
	  void registerUser(User user) throws Exception;
	  User validateLogin(String username, String password) throws Exception;
	}
