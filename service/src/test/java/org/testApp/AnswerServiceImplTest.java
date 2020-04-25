package org.testApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testApp.api.AnswerDao;
import org.testApp.api.AnswerService;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnswerServiceImplTest {
    @Mock
    private static AnswerDao answerDao;
    @InjectMocks
    private static AnswerService answerService;

    @BeforeAll
    public static void createInstance() {
        answerService = AnswerServiceImpl.getInstance();
    }

    @Test
    public void testGetAnswers(){
        when(answerDao.getAnswers(anyInt())).thenReturn(null);
        List<Answer> listAnswers = answerService.getAnswers(1);
        Assertions.assertNull(listAnswers);
    }


}
