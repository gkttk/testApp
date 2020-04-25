package org.testApp.api;

import org.testApp.Answer;
import java.util.List;

public interface AnswerService {
    List<Answer> getAnswers(int question_id);
}
