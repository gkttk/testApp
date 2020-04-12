package org.testApp.api;

import org.testApp.Answer;
import java.util.List;

public interface IAnswerDao {
    List<Answer> getAnswers(int question_id);



}
