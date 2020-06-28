package com.github.gkttk.testApp;

import com.github.gkttk.testApp.api.TempNewThemeDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import com.github.gkttk.testApp.config.DaoConfig;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DaoConfig.class)
@Transactional
public class TestNewThemeImplTest {
    @Autowired
    private TempNewThemeDao tempNewThemeDao;

    @Test
    public void testAddTempNewTheme() {
        TempNewTheme tempNewThemeTest = new TempNewTheme(1, "testUser", 10);
        long result = tempNewThemeDao.addTempNewTheme(tempNewThemeTest);
        Assertions.assertNotEquals(-1, result);
    }

    @Test
    public void testGetAllTempNewThemes() {
        TempNewTheme tempNewThemeTest = new TempNewTheme(1, "testUser", 10);
        tempNewThemeDao.addTempNewTheme(tempNewThemeTest);
        List<TempNewTheme> result = tempNewThemeDao.getAllTempNewThemes();
        Assertions.assertNotNull(result);
    }

    @Test
    public void testGetTempNewThemeByOwnerId() {
        TempNewTheme tempNewThemeTest = new TempNewTheme(1, "testUser", 10);
        int id = tempNewThemeDao.addTempNewTheme(tempNewThemeTest);
        TempNewTheme result = tempNewThemeDao.getTempNewTheme(id);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result, tempNewThemeTest);
    }

    @Test
    public void testUpdateTempNewTheme() {
        TempNewTheme tempNewThemeTest = new TempNewTheme(1, "Новая тема", 10);
        int id = tempNewThemeDao.addTempNewTheme(tempNewThemeTest);
        TempNewTheme resultFromDb = tempNewThemeDao.getTempNewTheme(id);
        resultFromDb.setPermit(true);
        boolean result = tempNewThemeDao.updateTempNewTheme(resultFromDb);
        Assertions.assertTrue(result);
    }

    @Test
    public void testGetTempNewThemesByOwnerId() {
        TempNewTheme tempNewThemeTest = new TempNewTheme(1, "testUser", 10);
        tempNewThemeDao.addTempNewTheme(tempNewThemeTest);
        int ownerId = tempNewThemeTest.getOwnerId();
        List<TempNewTheme> resultList = tempNewThemeDao.getTempNewThemesByOwnerId(ownerId);
        Assertions.assertNotNull(resultList);

    }

    @Test
    public void testDeleteTempNewTheme() {
        TempNewTheme tempNewThemeTest = new TempNewTheme(1, "testUser", 10);
        int id = tempNewThemeDao.addTempNewTheme(tempNewThemeTest);
        int result = tempNewThemeDao.deleteTempNewTheme(id);
        Assertions.assertNotEquals(0, result);
    }


}
