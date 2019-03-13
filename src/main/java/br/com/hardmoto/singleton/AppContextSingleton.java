package br.com.hardmoto.singleton;

import br.com.hardmoto.config.AppConfig;
import br.com.hardmoto.config.CassandraConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AppContextSingleton {

    private static ApplicationContext appContext;

    public static synchronized ApplicationContext getInstance() {
        if (appContext == null)
            appContext = new AnnotationConfigApplicationContext(AppConfig.class, CassandraConfig.class);
        return appContext;
    }
}