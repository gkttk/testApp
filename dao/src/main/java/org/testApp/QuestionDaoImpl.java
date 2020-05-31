package org.testApp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testApp.api.QuestionDao;

import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    private final SessionFactory sessionFactory;
    private static final Logger log = LoggerFactory.getLogger(QuestionDaoImpl.class);


    public QuestionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Question> getQuestions(int themeId) {
        String hql = "FROM Question where theme_id =: themeIdParam"; //theme_id
        List<Question> questions = null;
        try{
            Session session = sessionFactory.getCurrentSession();
            questions = session.createQuery(hql, Question.class).setParameter("themeIdParam", themeId).list();
            log.info("GetQuestions with themeId:{}", themeId);
        } catch (HibernateException e) {
            log.error("Fail to GetQuestions(Hibernate) with themeId {}", themeId);
        }
        return questions;
    }


//вопросы без ответов(null)
  /*  @Override
    public List<Question> getQuestions(int theme_id) {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM question WHERE theme_id = ?";
        int question_id = 0;
        String question_text = null;
        Question question = null;
        try (Connection connection = MySQLConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1,theme_id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                question_id = resultSet.getInt("id");
                question_text = resultSet.getString("text");
                question = new Question(question_id, question_text, null, theme_id);
                questions.add(question);
            }
            return questions;
        } catch (SQLException e) {
            log.error("Fail to getQuestions by theme_id: {}", theme_id);
            throw new RuntimeException(e);
        }
    }*/  //JDBC


}
