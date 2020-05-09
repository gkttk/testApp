package org.testApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testApp.api.InfoForTeacherDao;
import java.util.List;

public class InfoForTeacherDaoImplTest {

    private static InfoForTeacherDao infoForTeacherDao;

    @BeforeAll
    public static void createInstance() { infoForTeacherDao = InfoForTeacherDaoImpl.getInstance();}

    @Test
    public void testGetAllResultHibernate(){
        List<InfoForTeacher> infoForTeacherFromDB = infoForTeacherDao.getAllResults();
        Assertions.assertNotNull(infoForTeacherFromDB);
    }


   /* @Test
    public void testGetAllResults(){
        List<InfoForTeacher> infoForTeachers = infoForTeacherDao.getAllResults();
        Assertions.assertNotNull(infoForTeachers);
    }*/  //JDBC

}
