package com.github.gkttk.testApp.api;

import com.github.gkttk.testApp.Answer;
import java.util.List;

public interface AnswerDao {
    List<Answer> getAnswers(int questionId);



}
