package com.github.gkttk.testApp.api;

import com.github.gkttk.testApp.Question;
import java.util.List;

public interface QuestionDao {
    List<Question> getQuestions(int themeId);


}
