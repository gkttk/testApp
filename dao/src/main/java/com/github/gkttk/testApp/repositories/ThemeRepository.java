package com.github.gkttk.testApp.repositories;

import com.github.gkttk.testApp.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ThemeRepository extends JpaRepository<Theme, Integer> {

    @Query("select name from Theme where id = :themeId")
    String findNameByThemeId(@Param("themeId") int ThemeId);
}
