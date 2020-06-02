package org.testApp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.testApp.api.ThemeDao;
@Transactional
public class ThemeDaoImpl implements ThemeDao {

    private final SessionFactory sessionFactory;
    private static final Logger log = LoggerFactory.getLogger(ThemeDaoImpl.class);


    public ThemeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Theme getTheme(Integer themeId) {
        Theme themeFromDb = null;
        try{
            Session session = sessionFactory.getCurrentSession();
            themeFromDb = session.get(Theme.class, themeId);
            log.info("GetTheme with themeId:{} - ", themeId);
        } catch (HibernateException e) {
            log.error("Exception - {} in getTheme with themeId:{}", e, themeId);
        }
        return themeFromDb;
    }


    public String getName(Integer theme_id) {
        try{
            Session session = sessionFactory.getCurrentSession();
            Theme theme = session.get(Theme.class, theme_id);
            log.info("Get theme - " + theme.getName());
            return theme.getName();
        } catch (HibernateException e) {
            log.error("Exception - {} in getName() ThemeDao by Id:{}", theme_id, e);
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
