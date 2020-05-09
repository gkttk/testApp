package org.testApp;

import org.testApp.api.InfoForTeacherDao;
import org.testApp.api.InfoForTeacherService;
import org.testApp.api.QuestionnaireDao;

import java.util.List;

public class InfoForTeacherServiceImpl implements InfoForTeacherService {

    private static volatile InfoForTeacherService instance;

    private InfoForTeacherDao infoForTeacherDao = InfoForTeacherDaoImpl.getInstance();


    private InfoForTeacherServiceImpl() {}

    public static synchronized InfoForTeacherService getInstance(){
        if(instance == null){
            instance = new InfoForTeacherServiceImpl();
        }
        return instance;
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
