package ru.job4j.autostorage.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created on 10.05.2018.
 */
public class CarTest {

    private static SessionFactory factory;

    @BeforeClass
    public static void init() {
        factory = new Configuration()
                .configure()
                .buildSessionFactory();
    }

    @Test
    public void getSomeCarTestWhenSuccessfulTest() {
        Session session = factory.openSession();
        session.beginTransaction();
        Car car = session.createQuery("from Car where id = :id", Car.class).setParameter("id", 1).getSingleResult();
        session.getTransaction().commit();
        String expected = "Car{" +
                "id=1, " +
                "carName='Kamaz', " +
                "gearbox=Gearbox{" +
                "id=1, " +
                "gearboxName='gearbox-1'}, " +
                "transmission=Transmission{" +
                "id=1, " +
                "transmissionName='АКПП'}, " +
                "engine=Engine{" +
                "id=1, " +
                "engineName='МАЗ-642550-2120'}}";
        String result = car.toString();
        assertThat(result, is(expected));
    }
}