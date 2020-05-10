package org.testApp.api;

import org.testApp.Theme;

public interface ThemeService {
    Theme getTheme(int themeId);
    String getThemeName(int themeId);
}
