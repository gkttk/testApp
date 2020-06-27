package com.github.gkttk.testApp.api;

import com.github.gkttk.testApp.InfoForTeacher;
import java.util.List;

public interface InfoForTeacherService {
    List<InfoForTeacher> getResults();
    List<InfoForTeacher> getResultsPagination(int numberOfPage, int maxResultsOnPage);

}
