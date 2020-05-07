package org.testApp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testApp.ConnectUtils.MySQLConnector;
import org.testApp.api.QuestionDao;
import org.testApp.hibernateUtil.HibernateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {

    private static final Logger log = LoggerFactory.getLogger(QuestionDaoImpl.class);
    private static volatile QuestionDao instance;

    private QuestionDaoImpl() {
    }

    public static QuestionDao getInstance() {
        if (instance == null) {
            instance = new QuestionDaoImpl();
        }
        return instance;
    }


    @Override
    public List<Question> getQuestions(int themeId) {
        String hql = "FROM Question where theme_id =: themeIdParam";
        List<Question> questions = null;
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSession()){
            transaction = session.beginTransaction();
            questions = session.createQuery(hql, Question.class).setParameter("themeIdParam", themeId).list();
            transaction.commit();
            log.info("GetQuestions with themeId:{}", themeId);
        }catch (HibernateException e){
            log.error("Fail to GetQuestions(Hibernate) with themeId {}", themeId);
            if(transaction != null){
                transaction.rollback();
            }
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
