package org.testApp.api;

import org.testApp.Question;
import java.util.List;

public interface QuestionService {
    List<Question> getQuestions(int theme_id);
    int checkQuestion(Question question, List<String> answers);
}
