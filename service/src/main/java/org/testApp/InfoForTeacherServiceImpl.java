package org.testApp;

import org.springframework.transaction.annotation.Transactional;
import org.testApp.api.InfoForTeacherDao;
import org.testApp.api.InfoForTeacherService;
import java.util.List;

public class InfoForTeacherServiceImpl implements InfoForTeacherService {


    private InfoForTeacherDao infoForTeacherDao;


    public InfoForTeacherServiceImpl(InfoForTeacherDao infoForTeacherDao) {
        this.infoForTeacherDao = infoForTeacherDao;
    }


    @Override
    @Transactional
    public List<InfoForTeacher> getResultsPagination(int numberOfPage,int maxResultOnPage) {
        return infoForTeacherDao.getResultsPagination(numberOfPage, maxResultOnPage);
    }

    @Override
    @Transactional
    public List<InfoForTeacher> getResults() {
       return infoForTeacherDao.getAllResults();
    }
}
