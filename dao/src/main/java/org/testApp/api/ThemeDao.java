package org.testApp.api;

import org.testApp.Theme;

public interface ThemeDao {

    String getName(Integer themeId);
    Theme getTheme(Integer themeId);
}
