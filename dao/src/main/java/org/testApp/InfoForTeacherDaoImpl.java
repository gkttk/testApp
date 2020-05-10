package org.testApp;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testApp.api.InfoForTeacherDao;
import org.testApp.hibernateUtil.HibernateUtil;
import java.util.List;

public class InfoForTeacherDaoImpl implements InfoForTeacherDao {
    private static final Logger log = LoggerFactory.getLogger(InfoForTeacherDaoImpl.class);
    private static volatile InfoForTeacherDao instance;

    private InfoForTeacherDaoImpl() {
    }

    public static InfoForTeacherDao getInstance() {
        if (instance == null) {
            instance = new InfoForTeacherDaoImpl();
        }
        return instance;
    }


    @Override
    public List<InfoForTeacher> getResultsPagination(int numberOfPage, int maxResultsOnPage) {
        String sql = "SELECT u.login as userLogin, u.email as userEmail, t.name as themeName, q.score as score\n" +
                "FROM user u, theme t\n" +
                "JOIN questionnaire q ON t.id = q.theme_id WHERE q.user_id = u.id";
        Transaction transaction = null;
        List<InfoForTeacher> results = null;
        try(Session session = HibernateUtil.getSession()){
            transaction = session.beginTransaction();
            results = session.createSQLQuery(sql)
                    .addScalar("userLogin", StandardBasicTypes.STRING)
                    .addScalar("userEmail", StandardBasicTypes.STRING)
                    .addScalar("themeName", StandardBasicTypes.STRING)
                    .addScalar("score", StandardBasicTypes.DOUBLE)
                    .setFirstResult((numberOfPage - 1) * maxResultsOnPage)
                    .setMaxResults(maxResultsOnPage)
                    .setResultTransformer(Transformers.aliasToBean(InfoForTeacher.class))
                    .list();
            transaction.commit();
            log.info("Get all InfoForTeacher, :{} results", results.size());
        }
        catch (HibernateException e){
            log.error("Exception:{}; Can't get all InfoForTeacher", e);
            if(transaction != null){
                transaction.rollback();
            }
        }
        return results;
    }


    @Override
    public List<InfoForTeacher> getAllResults() {
        String sql = "SELECT u.login as userLogin, u.email as userEmail, t.name as themeName, q.score as score\n" +
                "FROM user u, theme t\n" +
                "JOIN questionnaire q ON t.id = q.theme_id WHERE q.user_id = u.id";
        Transaction transaction = null;
        List<InfoForTeacher> results = null;
        try(Session session = HibernateUtil.getSession()){
            transaction = session.beginTransaction();
            results = session.createSQLQuery(sql)
                    .addScalar("userLogin", StandardBasicTypes.STRING)
                    .addScalar("userEmail", StandardBasicTypes.STRING)
                    .addScalar("themeName", StandardBasicTypes.STRING)
                    .addScalar("score", StandardBasicTypes.DOUBLE)
                    .setResultTransformer(Transformers.aliasToBean(InfoForTeacher.class))
                    .list();
            transaction.commit();
            log.info("Get all InfoForTeacher, :{} results", results.size());
        }
        catch (HibernateException e){
            log.error("Exception:{}; Can't get all InfoForTeacher", e);
            if(transaction != null){
                transaction.rollback();
            }
        }
        return results;
    }



    /*@Override
    public List<InfoForTeacher> getAllResults() {
        String query = "SELECT u.login, u.email, t.name, q.score\n" +
                "FROM USER u, theme t\n" +
                "JOIN questionnaire q ON t.id = q.theme_id WHERE q.user_id = u.id";
        List<InfoForTeacher> infosForTeacher = new LinkedList<>();
        try (Connection connection = MySQLConnector.getConnection();
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    String userLogin = resultSet.getString("login");
                    String userEmail = resultSet.getString("email");
                    String themeName = resultSet.getString("name");
                    double score = resultSet.getDouble("score");
                    InfoForTeacher infoForTeacher = new InfoForTeacher(userLogin, userEmail, themeName, score);
                    infosForTeacher.add(infoForTeacher);
                }
                return infosForTeacher;
            }
        } catch (SQLException e) {
            log.error("Fail to getAllResults");
            throw new RuntimeException(e);
        }
    }*/  //JDBC
}
