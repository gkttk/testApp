package org.testApp.api;

import org.testApp.Theme;

import java.util.List;

public interface ThemeDao {

    String getName(Integer themeId);
    Theme getTheme(Integer themeId);
    Integer saveTheme(Theme theme);
    List<Theme> getAllThemes();
}
