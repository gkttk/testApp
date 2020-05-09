package org.testApp.api;

import org.testApp.InfoForTeacher;
import java.util.List;

public interface InfoForTeacherDao {
    List<InfoForTeacher> getAllResults();
    List<InfoForTeacher> getResultsPagination(int numberOfPage, int maxResultsOnPage);
}
