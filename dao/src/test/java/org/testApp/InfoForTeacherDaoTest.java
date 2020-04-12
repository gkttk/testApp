package org.testApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testApp.api.IInfoForTeacherDao;
import java.util.List;

public class InfoForTeacherDaoTest {

    private static IInfoForTeacherDao infoForTeacherDao;

    @BeforeAll
    public static void createInstance() { infoForTeacherDao = InfoForTeacherDao.getInstance();}

    @Test
    public void testGetAllResults(){
        List<InfoForTeacher> infoForTeachers = infoForTeacherDao.getAllResults();
        Assertions.assertNotNull(infoForTeachers);
    }

}
