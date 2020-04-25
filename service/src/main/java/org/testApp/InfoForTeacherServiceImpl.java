package org.testApp;

import org.testApp.api.InfoForTeacherDao;
import org.testApp.api.InfoForTeacherService;

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
    public List<InfoForTeacher> getResults() {
       return infoForTeacherDao.getAllResults();
    }
}
