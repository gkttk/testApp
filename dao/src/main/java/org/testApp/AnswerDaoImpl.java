package org.testApp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testApp.api.AnswerDao;

import java.util.List;

public class AnswerDaoImpl implements AnswerDao {

    private final SessionFactory sessionFactory;
    private static final Logger log = LoggerFactory.getLogger(AnswerDaoImpl.class);

    public AnswerDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<Answer> getAnswers(int questionId) {
        String hql = "FROM Answer where question_id = :questionIdParam";
        List<Answer> answers = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            answers = session.createQuery(hql, Answer.class).setParameter("questionIdParam", questionId).list();
            log.info("GetAnswers with id:{}", questionId);
        } catch (HibernateException e) {
            log.error("Fail to getAnswers(Hibernate) by id {}", questionId);
        }
        return answers;
    } //hibernate


   /* @Override
    public List<Answer> getAnswers(int question_id) {
        List<Answer> answers = new ArrayList<>(6);
        String query = "SELECT * FROM answer WHERE question_id = ?";
        int answer_id = 0;
        String answer_text = null;
        String answer_correctness = null;
        Answer answer = null;
        try(Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,question_id);
            preparedStatement.executeQuery();
            try(ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    answer_id = resultSet.getInt("id");
                    answer_text = resultSet.getString("text");
                    answer_correctness = resultSet.getString("correctness");
                    answer = new Answer(answer_id, answer_text, answer_correctness, question_id);
                    answers.add(answer);
                }
                return answers;
            }
        }
        catch (SQLException e){
            log.error("Fail to getAnswers by id {}", question_id);
            throw new RuntimeException(e);
        }
    }*/  //JDBC
}
