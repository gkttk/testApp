package com.github.gkttk.testApp;

import com.github.gkttk.testApp.api.ThemeDao;
import com.github.gkttk.testApp.repositories.ThemeRepository;
import net.sf.ehcache.CacheManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import com.github.gkttk.testApp.config.DaoConfig;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class ThemeDaoImplTest {
    @Autowired
    private ThemeDao themeDao;

    @Autowired
    private ThemeRepository themeRepository;


    @Test
    public void testGetThemeSpringData() {
        int id = 1;
        Theme theme = themeRepository.getOne(id);
        Assertions.assertAll(() -> Assertions.assertNotNull(theme),
                () -> Assertions.assertEquals(id, theme.getId()));
    }

    @Test
    public void testSaveThemeSpringDao() {
        Theme theme = themeRepository.save(new Theme(null, "testTheme", 1));
        Assertions.assertAll(() -> Assertions.assertNotNull(theme),
                () -> Assertions.assertNotNull(theme.getId()));
    }

    @Test
    public void testGetNameSpringData() {
        int id = 1;
        String themeName = themeRepository.findNameByThemeId(id);
        Assertions.assertAll(() -> Assertions.assertNotNull(themeName),
                () -> Assertions.assertEquals("Объекты и классы", themeName));

    }


    @Test
    public void testGetAllThemesSpringData(){
        List<Theme> themes = themeRepository.findAll();
        Assertions.assertAll(()-> Assertions.assertNotNull(themes),
                ()-> Assertions.assertNotEquals(0, themes.size()),
                ()-> themes.forEach(theme -> Assertions.assertNotNull(theme.getId())));
    }



    @Test
    public void testGetAllThemeNames() {
        List<Theme> result = themeDao.getAllThemes();
        Assertions.assertNotNull(result);
    }


    @Test
    public void testSaveTheme() {
        Theme theme = new Theme(null, "Тема1", 1);//тема
        //вопрос 1 с ответами
        Question q1 = new Question(null, "ТестовыйВопрос1", new ArrayList<>(), theme);
        Answer forQ1_1 = new Answer(null, "Первый ответ на вопрос №1", "true", q1);
        Answer forQ1_2 = new Answer(null, "Второй ответ на вопрос №1", "false", q1);
        q1.getAnswers().add(forQ1_1);
        q1.getAnswers().add(forQ1_2);
        //вопрос 2 с ответами
        Question q2 = new Question(null, "ТестовыйВопрос2", new ArrayList<>(), theme);
        Answer forQ2_1 = new Answer(null, "Первый ответ на вопрос №2", "false", q2);
        Answer forQ2_2 = new Answer(null, "Второй ответ на вопрос №2", "true", q2);
        q2.getAnswers().add(forQ2_1);
        q2.getAnswers().add(forQ2_2);

        //добавляю вопросы в тему
        theme.gettQuestions().add(q1);
        theme.gettQuestions().add(q2);


        int resultId = themeDao.saveTheme(theme);
        Assertions.assertNotEquals(-1, resultId);


    }

    @Test
    public void testCacheTheme() {
        int themeId = 3;
        Theme theme1 = themeDao.getTheme(themeId);
        Theme theme2 = themeDao.getTheme(themeId);
        Theme theme3 = themeDao.getTheme(themeId);
        int size = CacheManager.ALL_CACHE_MANAGERS.get(0).getCache("org.testApp.Theme").getSize();
        Assertions.assertTrue(size > 0);
    }


    @Test
    public void testGetThemeHibernate() {
        Integer themeId = 3;
        Theme theme = themeDao.getTheme(themeId);
        Assertions.assertNotNull(theme);
        Assertions.assertAll(() -> Assertions.assertEquals("Коллекции", theme.getName()),
                () -> Assertions.assertEquals(3, theme.getId()));
    }


    @Test
    public void testGetName() {
        Integer id = 1;
        String themeName = themeDao.getName(id);
        Assertions.assertEquals("Объекты и классы", themeName);
    }

}
