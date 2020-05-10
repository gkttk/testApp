package org.testApp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testApp.api.ThemeDao;
import org.testApp.hibernateUtil.HibernateUtil;

public class ThemeDaoImpl implements ThemeDao {

    private static final Logger log = LoggerFactory.getLogger(ThemeDaoImpl.class);
    private static volatile ThemeDao instance;

    private ThemeDaoImpl() {
    }

    public static synchronized ThemeDao getInstance() {
        if (instance == null) {
            instance = new ThemeDaoImpl();
        }
        return instance;
    }

    public Theme getTheme(Integer themeId) {
        Transaction transaction = null;
        Theme themeFromDb = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            themeFromDb = session.get(Theme.class, themeId);
            transaction.commit();
            log.info("GetTheme with themeId:{} - ", themeId);
        } catch (HibernateException e) {
            log.error("Exception - {} in getTheme with themeId:{}", e, themeId);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return themeFromDb;
    }


    public String getName(Integer theme_id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            Theme theme = session.get(Theme.class, theme_id);
            transaction.commit();
            log.info("Get theme - " + theme.getName());
            return theme.getName();
        } catch (HibernateException e) {
            log.error("Exception - {} in getName() ThemeDao by Id:{}", theme_id, e);
            if (transaction != null) {
                transaction.rollback();
            }
            return null;
        }
    }



  /* public String getThemeNameByThemeId(int themeId){
        String query = "SELECT name FROM theme WHERE id = ?";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, themeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                String theme_name = null;
                if (resultSet.next()) {
                     theme_name = resultSet.getString("name");
                }
                return theme_name;
            }
        } catch (SQLException e) {
            log.error("Fail to getThemeName with theme_id is {}", themeId);
           throw new RuntimeException(e);
        }
    }*/ //JDBC

}
