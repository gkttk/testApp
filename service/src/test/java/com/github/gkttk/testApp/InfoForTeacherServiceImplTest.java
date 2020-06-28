package com.github.gkttk.testApp;

import com.github.gkttk.testApp.api.InfoForTeacherDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InfoForTeacherServiceImplTest {
    @Mock
    private static InfoForTeacherDao infoForTeacherDao;
    @InjectMocks
    private static InfoForTeacherServiceImpl infoForTeacherService;

    @Test
    public void testGetResultsPagination(){
        when(infoForTeacherDao.getResultsPagination(1,5))
                .thenReturn(Arrays.asList(new InfoForTeacher("test1", "test@1", "theme1", 50d),
                       new InfoForTeacher("test2", "test@2", "theme2", 40d),
                       new InfoForTeacher("test3", "test@3", "theme1", 20d),
                       new InfoForTeacher("test4", "test@4", "theme3", 30d),
                       new InfoForTeacher("test5", "test@5", "theme2", 10d)));
      List<InfoForTeacher> result = infoForTeacherService.getResultsPagination(1, 5);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(5, result.size());
    }

    @Test
    public void testGetResults(){
        when(infoForTeacherDao.getAllResults()).
                thenReturn(Arrays.asList(new InfoForTeacher("test", "test@", "theme", 50d)));
        List<InfoForTeacher> result = infoForTeacherService.getResults();
        result.forEach(infoForTeacher -> Assertions.assertAll(()->infoForTeacher.getUserLogin().equals("test"),
                ()->infoForTeacher.getUserEmail().equals("test@"),
                ()->infoForTeacher.getThemeName().equals("theme")));
    }

}
