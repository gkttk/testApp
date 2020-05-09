package org.testApp.api;

import org.testApp.InfoForTeacher;
import java.util.List;

public interface InfoForTeacherService {
    List<InfoForTeacher> getResults();
    List<InfoForTeacher> getResultsPagination(int numberOfPage, int maxResultsOnPage);

}
