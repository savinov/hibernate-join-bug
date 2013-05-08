package ru.guroo.labs.hibernate.join;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.ImprovedNamingStrategy;
import org.hibernate.criterion.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * Unit tests demonstrating my confusion.
 */
public class JoinTest {
    private SessionFactory sessionFactory;
    private Session session;

    @Before
    public void setUp() throws SQLException {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .configure("hibernate-test.cfg.xml")
                .configure("hibernate-hsqldb.cfg.xml")
                .setNamingStrategy(new ImprovedNamingStrategy())
                .buildSessionFactory();
        session = sessionFactory.openSession();
    }

    @After
    public void tearDown() {
        if (session != null) {
            session.close();
            session = null;
        }
        if (sessionFactory != null) {
            sessionFactory.close();
            sessionFactory = null;
        }
    }

    @Test
    public void join() {
        // Save 10 entities
        for (int i = 0; i < 10; i++) {
            EntityPk id = new EntityPk();
            RelatedEntity1 relatedEntity1 = new RelatedEntity1(i, i);
            RelatedEntity2 relatedEntity2 = new RelatedEntity2(i, i);
            session.saveOrUpdate(relatedEntity1);
            session.flush();
            session.saveOrUpdate(relatedEntity2);
            session.flush();
            id.setRelatedEntity1(relatedEntity1);
            id.setRelatedEntity2(relatedEntity2);
            Entity entity = new Entity();
            entity.setId(id);
            session.saveOrUpdate(entity);
            session.flush();

        }

        // No validation errors here
        Criteria criteria = session.createCriteria(Entity.class);
        criteria.createCriteria("id.relatedEntity1").addOrder(Order.asc("id"));

        // Generated invalid SQL, because have OrderBy without Join
        // For example: select this_.related_entity1 as related1_0_0_,
        //     this_.related_entity2 as related2_0_0_
        //     from entity this_ order by relatedent1_.id asc
        List<Entity> entities = criteria.list();
    }
}
