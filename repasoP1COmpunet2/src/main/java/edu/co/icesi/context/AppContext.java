package edu.co.icesi.context;

import edu.co.icesi.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppContext {

    private static final ApplicationContext context =
            new ClassPathXmlApplicationContext("beans.xml");

    private AppContext() {}

    public static ApplicationContext getContext() {
        return context;
    }
}