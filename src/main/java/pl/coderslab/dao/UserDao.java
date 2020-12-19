package pl.coderslab.dao;

import pl.coderslab.model.User;
import pl.coderslab.utils.DbUtil;

import java.sql.*;
import java.util.Arrays;

public class UserDao {

    private static final String CREATE_USER =
            "INSERT INTO users (username, email, password) VALUES (?, ?, ?);";

    private static final String READ_ALL_USER =
            "SELECT * FROM users;";

    private static final String READ_USER =
            "SELECT * FROM users u WHERE u.id = ?;";

    private static final String UPDATE_USER =
            "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?;";

    private static final String DELETE_USER =
            "DELETE FROM users WHERE id = ?;";

    public User insert(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt(1));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User read(int id) {
        try (Connection conn = DbUtil.getConnection();
             PreparedStatement readStatement = conn.prepareStatement(READ_USER);) {
            readStatement.setLong(1, id);
            ResultSet readUserRs = readStatement.executeQuery();
            User user = new User();
            while (readUserRs.next()) {
                user.setId(readUserRs.getInt("id"));
                user.setEmail(readUserRs.getString("email"));
                user.setUserName(readUserRs.getString("username"));
                user.setPassword(readUserRs.getString("password"));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public User[] findAll() {
        try (Connection conn = DbUtil.getConnection();
             Statement selectAll = conn.createStatement();) {
            ResultSet selectAllRs = selectAll.executeQuery(READ_ALL_USER);
            User[] users = new User[0];
            while (selectAllRs.next()) {
                User user = new User();
                user.setId(selectAllRs.getInt("id"));
                user.setEmail(selectAllRs.getString("email"));
                user.setUserName(selectAllRs.getString("username"));
                user.setPassword(selectAllRs.getString("password"));
                users = addToArray(users, user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User[] addToArray(User[] users, User user) {
        User[] tmpUsers = Arrays.copyOf(users, users.length + 1);
        tmpUsers[tmpUsers.length - 1] = user;
        return tmpUsers;
    }

    public void update(User user) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER);
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setInt(4, user.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userId) {
        try (Connection conn = DbUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER);
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String hashPassword(String password){
        return org.mindrot.jbcrypt.BCrypt.hashpw(password, org.mindrot.jbcrypt.BCrypt.gensalt());
    }


}
