package org.testApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.testApp.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Integer> {

    @Query("select name from Theme where id = :themeId")
    String findNameByThemeId(@Param("themeId") int ThemeId);
}
