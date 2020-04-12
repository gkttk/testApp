package org.testApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testApp.api.IInfoForTeacherDao;
import org.testApp.api.IInfoForTeacherService;

import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InfoForTeacherServiceTest {
    @Mock
    private static IInfoForTeacherDao infoForTeacherDao;
    @InjectMocks
    private static IInfoForTeacherService infoForTeacherService;

    @BeforeAll
    public static void createInstance() {
        infoForTeacherService = InfoForTeacherService.getInstance();
    }

    @Test
    public void testGetResults(){
        when(infoForTeacherDao.getAllResults()).
                thenReturn(Arrays.asList(new InfoForTeacher("test", "test@", "theme", 50)));
        List<InfoForTeacher> result = infoForTeacherService.getResults();
        result.forEach(infoForTeacher -> Assertions.assertAll(()->infoForTeacher.getUserLogin().equals("test"),
                ()->infoForTeacher.getUserEmail().equals("test@"),
                ()->infoForTeacher.getThemeName().equals("theme")));
    }

}
