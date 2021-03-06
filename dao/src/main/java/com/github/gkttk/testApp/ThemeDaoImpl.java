package com.github.gkttk.testApp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import com.github.gkttk.testApp.api.ThemeDao;
import java.util.List;

@Transactional
public class ThemeDaoImpl implements ThemeDao {

    private final SessionFactory sessionFactory;
    private static final Logger log = LoggerFactory.getLogger(ThemeDaoImpl.class);

    public ThemeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Theme> getAllThemes() {
        String hql = "FROM Theme";
        List<Theme> themes = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            themes = session.createQuery(hql, Theme.class).list();
            log.info("getAllThemes was successful, count of themes:{}", themes.size());
        } catch (HibernateException e) {
            log.error("Can't getAllThemes");
        }
        return themes;
    }

    @Override
    public int saveTheme(Theme theme) {
        int id = -1;
        try {
            Session session = sessionFactory.getCurrentSession();
            id = (int) session.save(theme);
            log.info("saveTheme with themeId :{} - ", id);
        } catch (HibernateException e) {
            log.error("Fail in saveTheme");
        }
        return id;
    }

    @Override
    public Theme getTheme(Integer themeId) {
        Theme themeFromDb = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            themeFromDb = session.get(Theme.class, themeId);
            log.info("GetTheme with themeId :{} - ", themeId);
        } catch (HibernateException e) {
            log.error("Fail in getTheme with themeId:{}", themeId);
        }
        return themeFromDb;
    }

    @Override
    public String getName(int themeId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Theme theme = session.get(Theme.class, themeId);
            log.info("Get theme - " + theme.getName());
            return theme.getName();
        } catch (HibernateException e) {
            log.error("Fail in getName() ThemeDao by Id:{}", themeId);
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
