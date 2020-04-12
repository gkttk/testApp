package org.testApp.api;

import org.testApp.Question;
import java.util.List;

public interface IQuestionDao {
    List<Question> getQuestions(int theme_id);

}
