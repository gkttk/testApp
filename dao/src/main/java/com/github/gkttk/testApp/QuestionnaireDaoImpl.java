package com.github.gkttk.testApp;

import com.github.gkttk.testApp.api.QuestionnaireDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class QuestionnaireDaoImpl implements QuestionnaireDao {

    private final SessionFactory sessionFactory;
    private static final Logger log = LoggerFactory.getLogger(QuestionnaireDaoImpl.class);

    public QuestionnaireDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Long questionnairesForUserCount(int userId) {
        String hql = "SELECT COUNT(*) FROM Questionnaire WHERE user_id = :userId";
        long rows = -1L;
        try {
            Session session = sessionFactory.getCurrentSession();
            rows = session.createQuery(hql, Long.class).setParameter("userId", userId).getSingleResult();
            log.info("Get count of Questionnaire for User with id:{}, count = {}", userId, rows);
        } catch (HibernateException e) {
            log.error("Can't get count of Questionnaires for User with id:{}", userId);
        }
        return rows;
    }


    @Override
    public List<Questionnaire> getQuestionnairesForUserPagination(int userId, int numberOfPage, int maxResultsOnPage) {
        String hql = "FROM Questionnaire WHERE user_id = :userId";
        List<Questionnaire> result = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            result = session.createQuery(hql, Questionnaire.class)
                    .setParameter("userId", userId)
                    .setFirstResult((numberOfPage - 1) * maxResultsOnPage)
                    .setMaxResults(maxResultsOnPage).list();
            log.info("Questionnaires was gotten for user with id:{}, page number:{}, maxResult:{}", userId, numberOfPage, maxResultsOnPage);
        } catch (HibernateException e) {
            log.error("Can't get Questionnaires for user with id:{}, page number:{}, maxResult:{}", userId, numberOfPage, maxResultsOnPage);
        }
        return result;
    }


    @Override
    public int add(Questionnaire questionnaire) {
        int questionnaireId = -1;
        try {
            Session session = sessionFactory.getCurrentSession();
            questionnaireId = (Integer) session.save(questionnaire);
            log.info("Questionnaire with id:{} was saved", questionnaireId);
        } catch (HibernateException e) {
            log.error("Can't save Questionnaire with id:{}", questionnaire);
        }
        return questionnaireId;
    }

    @Override
    public boolean delete(Integer questionnaireId) {
        try {
            Session session = sessionFactory.getCurrentSession();
            Questionnaire questionnaire = session.get(Questionnaire.class, questionnaireId);
            session.delete(questionnaire);
            log.info("Questionnaire with id:{} was deleted", questionnaireId);
            return true;
        } catch (HibernateException e) {
            log.error("Cant delete Questionnaire with id:{}", questionnaireId);
            return false;
        }
    }

    @Override
    public List<Questionnaire> getQuestionnairesForUser(Integer userId) {
        String hql = "FROM Questionnaire WHERE user_id =: userIdParam";
        List<Questionnaire> questionnairesForUserFromDB = new ArrayList<>();
        try {
            Session session = sessionFactory.getCurrentSession();
            questionnairesForUserFromDB = session.createQuery(hql, Questionnaire.class).setParameter("userIdParam", userId).list();
            log.info("Questionnaires for user with ID: {} was gotten", userId);
        } catch (HibernateException e) {
            log.error("Can't get questionnaires with userID: {} from DB", userId);
        }
        return questionnairesForUserFromDB;
    }

    @Override
    public boolean deleteByUserId(Integer userId) {
        String hql = "DELETE FROM Questionnaire WHERE user_id =: userIdParam";
        try {
            Session session = sessionFactory.getCurrentSession();
            session.createQuery(hql).setParameter("userIdParam", userId).executeUpdate();
            log.info("Questionnaires with userID: {} was deleted", userId);
            return true;
        } catch (HibernateException e) {
            log.error("Can't delete questionnaire with userID: {}", userId);
        }
        return false;
    }

    @Override
    public List<Questionnaire> getQuestionnaires() {
        String hql = "FROM Questionnaire";
        List<Questionnaire> questionnairesFromDB = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            questionnairesFromDB = session.createQuery(hql, Questionnaire.class).list();
            log.info("All Questionnaires was gotten");
        } catch (HibernateException e) {
            log.error("Can't get all questionnaires");
        }
        return questionnairesFromDB;
    }


    @Override
    public Long countOfQuestionnaires() {
        String hql = "SELECT COUNT(*) FROM Questionnaire";
        long rows = -1L;
        try {
            Session session = sessionFactory.getCurrentSession();
            rows = session.createQuery(hql, Long.class).getSingleResult();
            log.info("Get count of Questionnaire, count:{}", rows);
        } catch (HibernateException e) {
            log.error("Can't get count of Questionnaires");
        }
        return rows;
    }


    /*  @Override
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
    }*/  //JDBC

    /*@Override
    public List<Questionnaire> getQuestionnairesForStudent(int studentId) {
        String query = "select q.id, t.id, q.score\n" +
                "from questionnaire q\n" +
                "join theme t on t.id = q.theme_id\n" +
                "where user_id = ?\n";
        List<Questionnaire> questionnaires = new LinkedList<>();

        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, studentId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int questionnaire_id = resultSet.getInt("q.id");
                    int theme_id = resultSet.getInt("t.id");
                    double questionnaire_score = resultSet.getDouble("score");
                    Questionnaire questionnaire = new Questionnaire(questionnaire_id, studentId, theme_id, questionnaire_score);
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
            log.info("questionnare with user_id is {} deleted", user_id);
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
            log.info("questionnare with id is {} deleted", questionnaire_id);
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
                    Questionnaire questionnaire = new Questionnaire(id, user_id, theme_id, score);
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
    public int countOfQuestionnaires() {
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
    }*/ //JDBC
}
