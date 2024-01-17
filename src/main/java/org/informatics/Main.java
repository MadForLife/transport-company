package org.informatics;

import org.hibernate.SessionFactory;
import org.informatics.configuration.SessionFactoryUtil;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = SessionFactoryUtil.getSessionFactory();
    }
}