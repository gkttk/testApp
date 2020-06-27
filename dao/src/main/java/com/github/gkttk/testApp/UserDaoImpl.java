
package com.github.gkttk.testApp;

import com.github.gkttk.testApp.api.UserDao;
import com.github.gkttk.testApp.enums.Role;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Transactional
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;
    private static final Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getUserHibernate(Integer userId) {
        User userFromDB = null;
        try{
            Session session = sessionFactory.getCurrentSession();
            userFromDB = session.get(User.class, userId);
            log.info("Get User with ID: {} from DB", userId);
        } catch (HibernateException e) {
            log.error("Exception:{}. Can't get User with ID: {} from DB", e, userId);
        }
        return userFromDB;
    }


    @Override
    public Integer addHibernate(User user) {
        Integer id = null;
        try{
            Session session = sessionFactory.getCurrentSession();
            id = (Integer) session.save(user);
            log.info("User " + user.getLogin() + " saved");

        } catch (HibernateException e) {
            log.error("Add user error in DAO module" + e);
        }
        return id;
    }


    @Override
    public List<User> getUsersHibernate() {
        List<User> usersFromDB = null;
        try{
            Session session = sessionFactory.getCurrentSession();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);
            query.select(query.from(User.class));
            usersFromDB = session.createQuery(query).getResultList();
            log.info("Get Users from DB");
        } catch (HibernateException e) {
            log.error("Exception in getUsers " + e);
        }
        return usersFromDB;
    }



    @Override
    public User getUserByLoginHibernate(String userLogin) {
        String hql = "FROM User WHERE login = :userLogin";
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql, User.class);
            query.setParameter("userLogin", userLogin);
            User user = (User) query.getSingleResult();
            log.info("Get User from users login {}", userLogin);
            return user;
        } catch (HibernateException e) {
            log.error("GetUser by login: {} exception", userLogin);
            return null;
        }
    }

    @Override
    public boolean updateUserHibernate(User user) {
        try{
            Session session = sessionFactory.getCurrentSession();
            session.update(user);
            log.info("User with id:{} was updated", user.getId());
            return true;
        } catch (HibernateException e) {
            log.error("Exception: {} ; Can't update User with id:{}", e, user.getId());
        }
        return false;
    }

    @Override
    public boolean deleteUserHibernate(String login) {
        String hql = "DELETE User WHERE login = :userLogin";
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql);
            query.setParameter("userLogin", login);
            query.executeUpdate();
            log.info("User " + login + " was deleted");
            return true;
        } catch (HibernateException e) {
            log.error("Exception in deleteUserHibernate by login = " + login + " " + e);
            return false;
        }
    }


    @Override
    public long updateUserForAdminHibernate(String oldUserLogin, User newUser) {
        String hql = "UPDATE User SET password = :newPassword, email = :newEmail, role = :newRole where login = :oldLogin";
        String newPassword = newUser.getPassword();
        String newEmail = newUser.getEmail();
        Role newRole = newUser.getRole();
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql);
            query.setParameter("newPassword", newPassword);
            query.setParameter("newEmail", newEmail);
            query.setParameter("newRole", newRole);
            query.setParameter("oldLogin", oldUserLogin);
            int result = query.executeUpdate();
            log.info("User " + oldUserLogin + " was updated");
            return result;
        } catch (HibernateException e) {
            log.error("Exception in updateUserForAdminHibernate by {}, Exception: {}", oldUserLogin, e);
            return 0;
        }
    }

    @Override
    public long updateUserEmailHibernate(String newEmail, User user) {
        String hql = "UPDATE User Set email = :newEmail WHERE login = :userLogin";
        String userLogin = user.getLogin();
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql);
            query.setParameter("newEmail", newEmail);
            query.setParameter("userLogin", userLogin);
            int result = query.executeUpdate();
            log.info("Email for user: {} was updated", userLogin);
            return result;
        } catch (HibernateException e) {
            log.error("Exception in updateUserEmailHibernate by login: {}, Exception: {}", userLogin, e);
            return 0;
        }
    }

    @Override
    public long updateUserPasswordHibernate(String newPassword, User user) {
        String hql = "UPDATE User Set password = :newPassword WHERE login = :userLogin";
        String userLogin = user.getLogin();
        try{
            Session session = sessionFactory.getCurrentSession();
            Query query = session.createQuery(hql);
            query.setParameter("newPassword", newPassword);
            query.setParameter("userLogin", userLogin);
            int result = query.executeUpdate();
            log.info("Password for user: {} was updated", userLogin);
            return result;
        } catch (HibernateException e) {
            log.error("Exception in updateUserPasswordHibernate by login: {}, Exception: {}", userLogin, e);
            return 0;
        }
    }

    public int countOfUsers() {
        String hql = "SELECT COUNT(u) FROM User u";
        int result = -1;
        try{
            Session session = sessionFactory.getCurrentSession();
            result = session.createQuery(hql, Long.class).uniqueResult().intValue();
            log.info("get count of users, result: {}", result);
        } catch (HibernateException e) {
            log.error("Fail to get count of rows in user table");
        }
        return result;
    }



     /*@Override
    public List<User> getUsersHibernate(UserFilter userFilter) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM User");
            List<User> users = query.getResultList();
            log.info("Get Users from DB");
            transaction.commit();
            return users;
        } catch (HibernateException e) {
            log.error("Exception in getUsers " + e);
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
    }*/  //hibernate

     /*   @Override
    public int countOfUsers() {
        String query = "SELECT COUNT(*) FROM user";
        try (Connection connection = MySQLConnector.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                int count = 0;
                if (resultSet.next()) {
                    count = resultSet.getInt(1);
                }
                return count;
            }
        } catch (SQLException e) {
            log.error("Fail to get count of rows in user table");
            throw new RuntimeException(e);
        }
    }*/  //JDBC

     /* @Override
    public long add(User user) {
        String query = "INSERT INTO user (login, password, email, role) VALUES (?,?,?,?)";
        String login = user.getLogin();
        String password = user.getPassword();
        String email = user.getEmail();
        String role = user.getRole().name();
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, role);
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                long userId = 0;
                if (resultSet.next()) {
                    userId = resultSet.getLong(1);
                }
                log.info("User with login: {} and id: {} was added",login, userId);
                return userId;
            }

        } catch (SQLException e) {
            log.error("Fail to add user with login: {} and password: {}",login, password);
            throw new RuntimeException(e);
        }
    }//готово*/ //JDBC add

     /* @Override
    public List<User> getUsers(UserFilter userFilter) {
        String query = "SELECT * FROM user";
        List<User> users = new ArrayList<>();
        try (Connection connection = MySQLConnector.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String login = resultSet.getString("login");
                    String password = resultSet.getString("password");
                    String email = resultSet.getString("email");
                    Role role = Role.valueOf(resultSet.getString("role"));
                    User user = new User(id, login, password, email, role);
                    users.add(user);
                }
                return users;
            }
        } catch (SQLException e) {
            log.error("Fail to get users");
            throw new RuntimeException(e);
        }
    }//готово*/ //JDBC getUsers

     /* @Override
    public User getUser(String userLogin) {
        User user = null;
        String query = "SELECT * FROM user WHERE login = ?";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, userLogin);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String login = resultSet.getString("login");
                    String password = resultSet.getString("password");
                    String email = resultSet.getString("email");
                    Role role = Role.valueOf(resultSet.getString("role"));
                    user = new User(id, login, password, email, role);
                }
                return user;
            }
        } catch (SQLException e) {
            log.error("Fail to get user with login: {}", userLogin);
            throw new RuntimeException(e);
        }
    } //готово*/  //JDBC GetUser

     /*@Override
    public boolean delete(String login) {
        String query = "DELETE FROM user where login = ?";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(query)) {
            pStatement.setString(1, login);
            pStatement.executeUpdate();
            log.info("user with login: {} was deleted", login);
            return true;
        } catch (SQLException e) {
            log.error("Fail to delete user  userlogin: {}", login);
            throw new RuntimeException(e);
        }
    }//готово*/ //JDBC delete

     /* @Override
    public long updateUserForAdmin(String oldUserLogin, User newUser) {
        String query = "UPDATE user SET password = ?, email = ?, role = ? WHERE login = ?";
        String newPassword = newUser.getPassword();
        String newEmail = newUser.getEmail();
        String newRole = newUser.getRole().name();
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, newEmail);
            preparedStatement.setString(3, newRole);
            preparedStatement.setString(4, oldUserLogin);
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                long updatedUserId = 0;
                if (resultSet.next()) {
                    updatedUserId = resultSet.getLong(1);
                }
                log.info("user with login {} was updated", oldUserLogin);
                return updatedUserId;
            }
        } catch (SQLException e) {
            log.error("Fail to update user with login: {}", oldUserLogin);
            throw new RuntimeException(e);
        }
    }*/  //JDBC UpdateUserForAdmin

     /*@Override
    public long updateUserEmail(String newEmail, User user) {
        String query = "UPDATE user SET email = ? WHERE login = ?";
        String userLogin = user.getLogin();
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, newEmail);
            preparedStatement.setString(2, userLogin);
            preparedStatement.executeUpdate();
            log.info("user with login {} change his email to {}", userLogin, newEmail);
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                long updatedUserId = 0;
                if (resultSet.next()) {
                    updatedUserId = resultSet.getLong(1);
                }
                return updatedUserId;
            }
        } catch (SQLException e) {
            log.error("Fail to update user email with userlogin: {}", userLogin);
            throw new RuntimeException(e);
        }
    }*/  //JDBC updateUserEmail

      /*  @Override
    public long updateUserPassword(String newPassword, User user) {
        String query = "UPDATE user SET password = ? WHERE login = ?";
        String userLogin = user.getLogin();
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, userLogin);
            preparedStatement.executeUpdate();
            log.info("user with login {} change his password to {}", userLogin, newPassword);
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                long updatedUserId = 0;
                if (resultSet.next()) {
                    updatedUserId = resultSet.getLong(1);
                }
                return updatedUserId;
            }
        } catch (SQLException e) {
            log.error("Fail to update user password with userlogin: {}", userLogin);
            throw new RuntimeException(e);
        }
    }*/ //JDBC updateUserPassword


}

