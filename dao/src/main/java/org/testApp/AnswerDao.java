package org.testApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testApp.ConnectUtils.MySQLConnector;
import org.testApp.api.IAnswerDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerDao implements IAnswerDao {
    private static volatile IAnswerDao instance;

    private static final Logger log = LoggerFactory.getLogger(AnswerDao.class);

    private AnswerDao() {}

    public static synchronized IAnswerDao getInstance() {
        if (instance == null) {
            instance = new AnswerDao();
        }
        return instance;
    }

    @Override
    public List<Answer> getAnswers(int question_id) {
        List<Answer> answers = new ArrayList<>(6);
        String query = "SELECT * FROM answer WHERE question_id = ?";
        int answer_id = 0;
        String answer_text = null;
        boolean answer_correctness = false;
        Answer answer = null;
        try(Connection connection = MySQLConnector.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1,question_id);
            preparedStatement.executeQuery();
            try(ResultSet resultSet = preparedStatement.getResultSet()) {
                while (resultSet.next()) {
                    answer_id = resultSet.getInt("id");
                    answer_text = resultSet.getString("text");
                    answer_correctness = Boolean.parseBoolean(resultSet.getString("correctness"));
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
