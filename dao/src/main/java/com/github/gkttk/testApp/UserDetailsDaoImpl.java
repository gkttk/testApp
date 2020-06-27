package com.github.gkttk.testApp;

import com.github.gkttk.testApp.api.UserDetailsDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class UserDetailsDaoImpl implements UserDetailsDao {
    private final SessionFactory sessionFactory;
    private static final Logger log = LoggerFactory.getLogger(UserDetailsDaoImpl.class);

    public UserDetailsDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserDetails getUserDetails(Integer userId) {
        String hql = "FROM UserDetails WHERE user_id =: userIdParam";
        UserDetails userDetails = null;
        try{
            Session session = sessionFactory.getCurrentSession();
            userDetails = session.createQuery(hql, UserDetails.class).setParameter("userIdParam", userId).getSingleResult();
            log.info("Get UserDetails for User with id:{}", userId);
        } catch (HibernateException e) {
            log.error("Exception: {}; Can't get UserDetail for User with id:{}", e, userId);
        }
        return userDetails;
    }

    @Override
    public Boolean updateUserDetails(UserDetails newUserDetails) {
        try{
            Session session = sessionFactory.getCurrentSession();
            session.update(newUserDetails);
            log.info("UserDetails for User with id:{} was updated", newUserDetails.getDetailsUser().getId());
            return true;
        } catch (HibernateException e) {
            log.error("Exception: {}; Can't update UserDetail for User with id:{}", e, newUserDetails.getDetailsUser().getId());
        }
        return false;
    }

}
