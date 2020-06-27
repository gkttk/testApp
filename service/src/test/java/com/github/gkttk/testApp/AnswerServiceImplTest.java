package com.github.gkttk.testApp;

import com.github.gkttk.testApp.api.AnswerDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnswerServiceImplTest {
    @Mock
    private static AnswerDao answerDao;
    @InjectMocks
    private static AnswerServiceImpl answerService;


    @Test
    public void testGetAnswers(){
        when(answerDao.getAnswers(anyInt())).thenReturn(null);
        List<Answer> listAnswers = answerService.getAnswers(1);
        Assertions.assertNull(listAnswers);
    }


}
