package org.testApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testApp.ConnectUtils.MySQLConnector;
import org.testApp.api.AnswerDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerDaoImpl implements AnswerDao {
    private static volatile AnswerDao instance;

    private static final Logger log = LoggerFactory.getLogger(AnswerDaoImpl.class);

    private AnswerDaoImpl() {}

    public static synchronized AnswerDao getInstance() {
        if (instance == null) {
            instance = new AnswerDaoImpl();
        }
        return instance;

    }

    @Override
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
    }
}
