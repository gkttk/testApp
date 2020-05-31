package org.testApp;

import net.sf.ehcache.CacheManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.testApp.api.ThemeDao;
import org.testApp.config.DaoConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class ThemeDaoImplTest {
    @Autowired
    private ThemeDao themeDao;

    @Test
    public void testCacheTheme(){
        int themeId = 3;
        Theme theme1 = themeDao.getTheme(themeId);
        Theme theme2 = themeDao.getTheme(themeId);
        Theme theme3 = themeDao.getTheme(themeId);
        int size = CacheManager.ALL_CACHE_MANAGERS.get(0).getCache("org.testApp.Theme").getSize();
        Assertions.assertTrue(size > 0);
    }

    @Test
    public void testGetThemeHibernate(){
        Integer themeId = 3;
        Theme theme = themeDao.getTheme(themeId);
        Assertions.assertNotNull(theme);
        Assertions.assertAll(()-> Assertions.assertEquals("Коллекции", theme.getName()),
                ()-> Assertions.assertEquals(3, theme.getId()));
    } //hibernate


    @Test
    public void testGetName(){
        Integer id = 1;
        String themeName = themeDao.getName(id);
        Assertions.assertEquals("Объекты и классы", themeName);
    }

}
