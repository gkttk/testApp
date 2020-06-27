package com.github.gkttk.testApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import com.github.gkttk.testApp.api.InfoForTeacherDao;
import com.github.gkttk.testApp.config.DaoConfig;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class InfoForTeacherDaoImplTest {
    @Autowired
    private InfoForTeacherDao infoForTeacherDao;

    @Test
    public void testGetAllResultHibernate() {
        List<InfoForTeacher> infoForTeacherFromDB = infoForTeacherDao.getAllResults();
        Assertions.assertNotNull(infoForTeacherFromDB);
    }


}
