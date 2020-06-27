package com.github.gkttk.testApp.api;

import com.github.gkttk.testApp.InfoForTeacher;
import java.util.List;

public interface InfoForTeacherDao {
    List<InfoForTeacher> getAllResults();
    List<InfoForTeacher> getResultsPagination(int numberOfPage, int maxResultsOnPage);
}
