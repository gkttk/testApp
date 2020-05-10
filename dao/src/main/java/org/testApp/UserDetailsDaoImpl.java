package org.testApp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testApp.api.UserDetailsDao;
import org.testApp.hibernateUtil.HibernateUtil;

public class UserDetailsDaoImpl implements UserDetailsDao {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsDaoImpl.class);
    private static volatile UserDetailsDao instance;

    private UserDetailsDaoImpl() {
    }

    public static synchronized UserDetailsDao getInstance() {
        if (instance == null) {
            instance = new UserDetailsDaoImpl();
        }
        return instance;
    }




    @Override
    public UserDetails getUserDetails(Integer userId) {
        String hql = "FROM UserDetails WHERE user_id =: userIdParam";
        Transaction transaction = null;
        UserDetails userDetails = null;
        try (Session session = HibernateUtil.getSession()){
            transaction = session.beginTransaction();
            userDetails = session.createQuery(hql, UserDetails.class).setParameter("userIdParam", userId).getSingleResult();
            transaction.commit();
            log.info("Get UserDetails for User with id:{}", userId);
        } catch (HibernateException e) {
            log.error("Exception: {}; Can't get UserDetail for User with id:{}", e, userId);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return userDetails;
    }

    @Override
    public Boolean updateUserDetails(UserDetails newUserDetails) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.update(newUserDetails);
            transaction.commit();
            log.info("UserDetails for User with id:{} was updated", newUserDetails.getDetailsUser().getId());
            return true;
        } catch (HibernateException e) {
            log.error("Exception: {}; Can't update UserDetail for User with id:{}", e, newUserDetails.getDetailsUser().getId());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return false;
    }

}
