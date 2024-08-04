package dao.user;

import java.util.List;

public interface UserDAO {
	boolean create(User user);
	User getById(Integer userId);
	User getByUsername(String username);
	User getByEmail(String email);
	List<User> getAll();
	boolean update(User user);
	boolean deleteById(Integer userId);
}
