package org.testApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testApp.ConnectUtils.MySQLConnector;
import org.testApp.api.QuestionnaireDao;
import org.testApp.filters.QuestionnaireFilter;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class QuestionnaireDaoImpl implements QuestionnaireDao {
    private static final Logger log = LoggerFactory.getLogger(QuestionnaireDaoImpl.class);
    private static volatile QuestionnaireDao instance;

    private QuestionnaireDaoImpl() {}

    public static synchronized QuestionnaireDao getInstance() {
        if (instance == null) {
            instance = new QuestionnaireDaoImpl();
        }
        return instance;
    }

    @Override
    public int add(Questionnaire questionnaire, double score) {
        String query = "INSERT INTO questionnaire (theme_id, user_id, score) VALUES (?,?,?)";
        int theme_id = questionnaire.getThemeId();
        int user_id = questionnaire.getStudentId();

        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, theme_id);
            preparedStatement.setInt(2, user_id);
            preparedStatement.setDouble(3, score);
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                int questionnaireId = 0;
                if (resultSet.next()) {
                    questionnaireId = resultSet.getInt(1);
                }
                log.info("Questionnaire with {} saved", questionnaireId);
                return questionnaireId;
            }

        } catch (SQLException e) {
            log.error("Fail to save questionnaire with score: {}" , score);
            throw new RuntimeException(e);
        }
    }//+

    @Override
    public List<Questionnaire> getQuestionnairesForStudent(int studentId){
        String query = "select q.id, t.id, q.score\n" +
                "from questionnaire q\n" +
                "join theme t on t.id = q.theme_id\n" +
                "where user_id = ?\n";
        List<Questionnaire> questionnaires = new LinkedList<>();

        try(Connection connection = MySQLConnector.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, studentId);
           try(ResultSet resultSet = preparedStatement.executeQuery()){
               while(resultSet.next()){
                    int questionnaire_id = resultSet.getInt("q.id");
                    int theme_id = resultSet.getInt("t.id");
                    double questionnaire_score = resultSet.getDouble("score");
                    Questionnaire questionnaire  = new Questionnaire(questionnaire_id, studentId, theme_id, questionnaire_score);
                    questionnaires.add(questionnaire);
               }
               return questionnaires;
           }
        } catch (SQLException e) {
            log.info("Fail to get Questionnaires for user with id {}", studentId);
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteByUserId(int user_id) {
        String query = "DELETE FROM questionnaire where user_id = ?";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(query)) {
            pStatement.setInt(1, user_id);
            pStatement.executeUpdate();
            log.info("questionnare with user_id is {} deleted",user_id);
            return true;
        } catch (SQLException e) {
            log.error("Fail to delete questionnaire with user_id {}", user_id);
            throw new RuntimeException(e);
        }
    }//+

    @Override
    public boolean delete(int questionnaire_id) {
        String query = "DELETE FROM questionnaire where id = ?";
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement pStatement = connection.prepareStatement(query)) {
            pStatement.setInt(1, questionnaire_id);
            pStatement.executeUpdate();
            log.info("questionnare with id is {} deleted",questionnaire_id);
            return true;
        } catch (SQLException e) {
            log.error("Fail to delete questionnaire with id {}", questionnaire_id);
            throw new RuntimeException(e);
        }
    }//+

    //возвращает вопросники без вопросов, для информации учителям
    @Override
    public List<Questionnaire> getQuestionnaires(QuestionnaireFilter questionnaireFilter) {
        String query = "SELECT * FROM questionnaire";
        List<Questionnaire> questionnaires = new LinkedList<>();
        try (Connection connection = MySQLConnector.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    int user_id = resultSet.getInt("user_id");
                   int theme_id = resultSet.getInt("theme_id");
                    double score = resultSet.getDouble("score");
                    Questionnaire questionnaire = new Questionnaire(id, user_id, theme_id,score);
                    questionnaires.add(questionnaire);
                }
                return questionnaires;
            }
        } catch (SQLException e) {
            log.error("Fail to get questionnaires");
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countOfQuestionnaires(){
        String query = "SELECT COUNT(*) FROM questionnaire";
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
            log.error("Fail to get count of rows in questionnaire table");
            throw new RuntimeException(e);
        }
    }
}
