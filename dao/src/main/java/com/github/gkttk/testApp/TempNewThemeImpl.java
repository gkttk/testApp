package com.github.gkttk.testApp;

import com.github.gkttk.testApp.api.TempNewThemeDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
public class TempNewThemeImpl implements TempNewThemeDao {

    private final SessionFactory sessionFactory;
    private static final Logger log = LoggerFactory.getLogger(TempNewTheme.class);

    public TempNewThemeImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer addTempNewTheme(TempNewTheme tempNewTheme) {
        int id = -1;
        try {
            Session session = sessionFactory.getCurrentSession();
            id = (int) session.save(tempNewTheme);
            log.info("Add tempNewTheme was successful, id:{}", id);
        } catch (HibernateException e) {
            log.error("Fail to addTempNewTheme", e);

        }
        return id;
    }

    @Override
    public List<TempNewTheme> getAllTempNewThemes() {

        String hql = "FROM TempNewTheme WHERE permit = false";
        List<TempNewTheme> result = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            result = session.createQuery(hql, TempNewTheme.class).list();
            log.info("getAllTempNewThemes was successful, list.size = {}", result.size());
        } catch (HibernateException e) {
            log.error("Fail to getAllTempNewThemes", e);
        }
        return result;
    }

    @Override
    public TempNewTheme getTempNewTheme(int tempThemeId) {
        String hql = "FROM TempNewTheme WHERE id = :tempThemeId";
        TempNewTheme result = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            result = session.createQuery(hql, TempNewTheme.class).setParameter("tempThemeId", tempThemeId).getSingleResult();
            log.info("getTempNewThemeByOwnerId was successful, id:{}", result.getId());
        } catch (HibernateException e) {
            log.error("Fail to getTempNewThemeByOwnerId with ownerId:{}", tempThemeId, e);
        }

        return result;
    }

    @Override
    public boolean updateTempNewTheme(TempNewTheme newTheme) {
         boolean result = false;
        try{
            Session session = sessionFactory.getCurrentSession();
            session.update(newTheme);
            log.info("TempNewTheme was successful updated, id:{}", newTheme.getId());
            result = true;
        }catch (HibernateException e){
            log.error("Fail to updateTempNewTheme with id:{}", newTheme.getId(), e);
        }

        return result;
    }


    @Override
   public List<TempNewTheme> getTempNewThemesByOwnerId(int ownerId){
        String hql = "FROM TempNewTheme WHERE ownerId = :ownerId";
        List<TempNewTheme> result = null;
        try {
            Session session = sessionFactory.getCurrentSession();
            result = session.createQuery(hql, TempNewTheme.class).setParameter("ownerId", ownerId).list();
            log.info("getTempNewThemesByOwnerId was successful, list.size = {} , ownerId:{}", result.size(), ownerId);
        } catch (HibernateException e) {
            log.error("Fail to getTempNewThemesByOwnerId with ownerId:{}",ownerId, e);
        }
        return result;

    }

    @Override
    public int deleteTempNewTheme(int tempNewThemeId) {
        String hql = "DELETE TempNewTheme WHERE Id = :tempNewThemeId";
        int result = 0;
        try {
            Session session = sessionFactory.getCurrentSession();
            result = session.createQuery(hql).setParameter("tempNewThemeId", tempNewThemeId).executeUpdate();
            log.info("deleteTempNewTheme was successful, id = {}", tempNewThemeId);
        } catch (HibernateException e) {
            log.error("Fail to deleteTempNewTheme with id:{}",tempNewThemeId, e);
        }

        return result;



    }
}
