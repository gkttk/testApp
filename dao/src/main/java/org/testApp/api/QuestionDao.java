package org.testApp.api;

import org.testApp.Question;
import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions(int themeId);


}
