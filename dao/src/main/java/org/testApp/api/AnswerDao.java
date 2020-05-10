package org.testApp.api;

import org.testApp.Answer;
import java.util.List;

public interface AnswerDao {
    List<Answer> getAnswers(int questionId);



}
