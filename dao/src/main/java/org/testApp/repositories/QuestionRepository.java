package org.testApp.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.testApp.Question;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    @Query("FROM Question where theme_id =:themeIdParam")
    List<Question> findAllByQTheme(@Param("themeIdParam") int themeId);

}
