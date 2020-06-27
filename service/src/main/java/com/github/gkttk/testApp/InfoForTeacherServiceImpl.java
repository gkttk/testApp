package com.github.gkttk.testApp;

import com.github.gkttk.testApp.api.InfoForTeacherDao;
import com.github.gkttk.testApp.api.InfoForTeacherService;
import java.util.List;

public class InfoForTeacherServiceImpl implements InfoForTeacherService {


    private InfoForTeacherDao infoForTeacherDao;

    public InfoForTeacherServiceImpl(InfoForTeacherDao infoForTeacherDao) {
        this.infoForTeacherDao = infoForTeacherDao;
    }

    @Override
    public List<InfoForTeacher> getResultsPagination(int numberOfPage,int maxResultOnPage) {
        return infoForTeacherDao.getResultsPagination(numberOfPage, maxResultOnPage);
    }

    @Override
    public List<InfoForTeacher> getResults() {
       return infoForTeacherDao.getAllResults();
    }
}
