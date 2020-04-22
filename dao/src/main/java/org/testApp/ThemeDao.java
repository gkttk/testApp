package org.testApp;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testApp.ConnectUtils.MySQLConnector;
import org.testApp.api.IThemeDao;
import org.testApp.hibernateUtil.HibernateUtil;
import org.testApp.hibernateUtil.SFUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThemeDao implements IThemeDao{

    private static final Logger log = LoggerFactory.getLogger(ThemeDao.class);
    private static volatile IThemeDao instance;

    private ThemeDao() {}

    public static synchronized IThemeDao getInstance() {
        if (instance == null) {
            instance = new ThemeDao();
        }
        return instance;
    }

    public String getName(Integer theme_id){
       /* Session session = SFUtil.getSession();*/
        try(Session session = HibernateUtil.getSession()){
            Theme theme = session.get(Theme.class, theme_id);
            log.info("Get theme - " + theme.getName());
            return theme.getName();
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
    }*/

}
