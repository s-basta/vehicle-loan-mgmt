package com.vehicleloan.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vehicleloan.config.Database;
import com.vehicleloan.constant.Gender;
import com.vehicleloan.constant.TypeOfEmployment;

@Service
public class UserDAOImplementation implements UserDAO{
	Connection conn=null;

	public UserDAOImplementation() {
		conn = Database.getConnection();
	}

    public void setConnection(Connection conn) {
        if(conn != null) this.conn = conn;
    }
	
	public User resultSetToUserConvertor(ResultSet resultSet) throws SQLException {
		return new User(
                resultSet.getInt("userId"),
                resultSet.getString("firstName"),
                resultSet.getString("lastName"),
                resultSet.getDate("dateOfBirth").toLocalDate(),
                Gender.valueOf(resultSet.getString("gender")),
                resultSet.getString("username"),
                resultSet.getString("email"),
                resultSet.getString("mobile"),
                resultSet.getString("password"),
                resultSet.getBoolean("isAdmin"),
                resultSet.getString("typeOfEmployment") == null ? null : TypeOfEmployment.valueOf(resultSet.getString("typeOfEmployment")),
                resultSet.getDouble("salary"),
                resultSet.getString("panCardNumber"),
                resultSet.getString("aadharNumber")
            );	
	}
	
	public User getByUsername(String username) {
		User user = null;

		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select * from vloanUser where username like '" + username + "'");
			if(result.next()) {
				 user = resultSetToUserConvertor(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	public User getByEmail(String email) {
		User user = null;

		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select * from vloanUser where email like '" + email + "'");
			if(result.next()) {
				 user = resultSetToUserConvertor(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}

	
	@Override
	public boolean create(User user) {
		try {
			String sql = "INSERT INTO `vloanUser` (" +
	                "`firstName`, `lastName`, `dateOfBirth`, `gender`, `username`, `email`, `mobile`, " +
	                "`password`, `isAdmin`, `typeOfEmployment`, `salary`, `panCardNumber`, `aadharNumber`) " +
	                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pst = conn.prepareStatement(sql);
					
			pst.setString(1, user.getFirstName());
            pst.setString(2, user.getLastName());
            pst.setDate(3, java.sql.Date.valueOf(user.getDateOfBirth()));
            pst.setString(4, user.getGender().name());
            pst.setString(5, user.getUsername());
            pst.setString(6, user.getEmail());
            pst.setString(7, user.getMobile());
            pst.setString(8, user.getPassword());
            pst.setBoolean(9, user.getIsAdmin());
            
            if(user.getTypeOfEmployment() != null) pst.setString(10, user.getTypeOfEmployment().name());
            else pst.setNull(10, java.sql.Types.VARCHAR);
            
            if(user.getSalary() != null) pst.setDouble(11, user.getSalary());
            else pst.setNull(11, java.sql.Types.DOUBLE);
            
            if(user.getPanCardNumber() != null) pst.setString(12, user.getPanCardNumber());
            else pst.setNull(12, java.sql.Types.VARCHAR);
            
            if(user.getAadharNumber() != null) pst.setString(13, user.getAadharNumber());
            else pst.setNull(13, java.sql.Types.VARCHAR);
            
            int rows = pst.executeUpdate();
            if(rows > 0) return true;
        }catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	@Override
	public User get(Integer userId) {
		User user = null;
		
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select * from vloanUser where userid=" + userId);
			if(result.next()) {
				user = resultSetToUserConvertor(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	@Override
	public User get(String username, String password) {
		User user = getByEmail(username);
		if(user == null) user = getByUsername(username);	

		if(user == null) return null;
		if(!user.getPassword().equals(password)) return null;
		
		return user;
	}

	
	@Override
	public List<User> getAll() {
		User user = null;
		List<User> users = new ArrayList<User>();
		
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select * from vloanUser");
			
			while(result.next()) {
				user = resultSetToUserConvertor(result);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return users;
	}

	@Override
	public boolean update(User user) {
		if (user == null || user.getUserId() == null) {
            throw new IllegalArgumentException("User and UserId must not be null");
        }
		
        List<String> setClauses = new ArrayList<>();
        List<Object> parameters = new ArrayList<>();

        if (user.getFirstName() != null) {
            setClauses.add("firstName = ? ");
            parameters.add(user.getFirstName());
        }
        if (user.getLastName() != null) {
            setClauses.add("lastName = ? ");
            parameters.add(user.getLastName());
        }
        if (user.getDateOfBirth() != null) {
            setClauses.add("dateOfBirth = ? ");
            parameters.add(java.sql.Date.valueOf(user.getDateOfBirth()));
        }
        if (user.getGender() != null) {
            setClauses.add("gender = ? ");
            parameters.add(user.getGender().name());
        }
        if (user.getUsername() != null) {
            setClauses.add("username = ? ");
            parameters.add(user.getUsername());
        }
        if (user.getEmail() != null) {
            setClauses.add("email = ? ");
            parameters.add(user.getEmail());
        }
        if (user.getMobile() != null) {
            setClauses.add("mobile = ? ");
            parameters.add(user.getMobile());
        }
        if (user.getPassword() != null) {
            setClauses.add("password = ? ");
            parameters.add(user.getPassword());
        }
        if (user.getIsAdmin() != null) {
            setClauses.add("isAdmin = ? ");
            parameters.add(user.getIsAdmin());
        }
        if (user.getTypeOfEmployment() != null) {
            setClauses.add("typeOfEmployment = ? ");
            parameters.add(user.getTypeOfEmployment().name());
        }
        if (user.getSalary() != null) {
            setClauses.add("salary = ? ");
            parameters.add(user.getSalary());
        }
        if (user.getPanCardNumber() != null) {
            setClauses.add("panCardNumber = ? ");
            parameters.add(user.getPanCardNumber());
        }
        if (user.getAadharNumber() != null) {
            setClauses.add("aadharNumber = ? ");
            parameters.add(user.getAadharNumber());
        }

        if (setClauses.isEmpty()) {
            throw new IllegalArgumentException("No fields to update");
        }

        
        String sql = "UPDATE `vloanuser` SET " + String.join(", ", setClauses) + " WHERE userId = ?";
        System.out.println(sql);
        parameters.add(user.getUserId());
        System.out.println(parameters);
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            for (int i = 0; i < parameters.size(); i++) {
                pst.setObject(i + 1, parameters.get(i));
            }

            int rows = pst.executeUpdate();
            if(rows > 0) return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
	}

	@Override
	public boolean deleteById(Integer userId) {
	    if (userId == null) {
	        throw new IllegalArgumentException("UserId must not be null");
	    }

	    String sql = "DELETE FROM vloanUser WHERE userId = ?";
	    try (PreparedStatement pst = conn.prepareStatement(sql)) {
	        pst.setInt(1, userId);
	        int rows = pst.executeUpdate();
	        return rows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}