package com.github.gkttk.testApp.api;

import com.github.gkttk.testApp.Theme;
import java.util.List;

public interface ThemeDao {
    String getName(int themeId);
    Theme getTheme(Integer themeId);
    int saveTheme(Theme theme);
    List<Theme> getAllThemes();
}
