package org.testApp.api;

import org.testApp.Theme;

import java.util.List;

public interface ThemeService {
    Theme getTheme(int themeId);
    String getThemeName(int themeId);
    int addNewTheme(Theme newTheme);
    List<Theme> getAllThemes();
}
