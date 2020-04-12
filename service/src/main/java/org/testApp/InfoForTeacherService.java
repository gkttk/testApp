package org.testApp;

import org.testApp.api.IInfoForTeacherDao;
import org.testApp.api.IInfoForTeacherService;
import java.util.List;

public class InfoForTeacherService implements IInfoForTeacherService {

    private static volatile IInfoForTeacherService instance;
    private IInfoForTeacherDao infoForTeacherDao = InfoForTeacherDao.getInstance();

    private InfoForTeacherService() {}

    public static synchronized IInfoForTeacherService getInstance(){
        if(instance == null){
            instance = new InfoForTeacherService();
        }
        return instance;
    }

    @Override
    public List<InfoForTeacher> getResults() {
       return infoForTeacherDao.getAllResults();
    }
}
