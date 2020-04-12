package org.testApp.api;

import org.testApp.Question;
import java.util.List;

public interface IQuestionService {
    List<Question> getQuestions(int theme_id);
    int checkQuestion(Question question, List<String> answers);
}
