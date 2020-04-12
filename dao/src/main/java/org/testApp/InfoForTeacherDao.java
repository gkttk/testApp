package org.testApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testApp.ConnectUtils.MySQLConnector;
import org.testApp.api.IInfoForTeacherDao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class InfoForTeacherDao implements IInfoForTeacherDao {
    private static final Logger log = LoggerFactory.getLogger(InfoForTeacherDao.class);
    private static volatile IInfoForTeacherDao instance;

    private InfoForTeacherDao() {
    }

    public static IInfoForTeacherDao getInstance() {
        if (instance == null) {
            instance = new InfoForTeacherDao();
        }
        return instance;
    }

    @Override
    public List<InfoForTeacher> getAllResults() {
        String query = "SELECT u.login, u.email, t.name, q.score\n" +
                "FROM USER u, theme t\n" +
                "JOIN questionnaire q ON t.id = q.theme_id WHERE q.user_id = u.id";
        List<InfoForTeacher> infosForTeacher = new LinkedList<>();
        try (Connection connection = MySQLConnector.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    String userLogin = resultSet.getString("login");
                    String userEmail = resultSet.getString("email");
                    String themeName = resultSet.getString("name");
                    double score = resultSet.getDouble("score");
                    InfoForTeacher infoForTeacher = new InfoForTeacher(userLogin, userEmail, themeName, score);
                    infosForTeacher.add(infoForTeacher);
                }
                return infosForTeacher;
            }
        } catch (SQLException e) {
            log.error("Fail to getAllResults");
            throw new RuntimeException(e);
        }
    }
}
