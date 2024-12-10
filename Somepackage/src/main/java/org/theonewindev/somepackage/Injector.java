package org.theonewindev.somepackage;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

public class Injector {

    public <T> T inject(T obj) {
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        Properties properties = loadProperties();

        for (Field field : fields) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                Class<?> fieldType = field.getType();
                String implementationClassName = properties.getProperty(fieldType.getName());

                try {
                    Class<?> implementationClass = Class.forName(implementationClassName);
                    Object instance = implementationClass.getDeclaredConstructor().newInstance();
                    field.setAccessible(true);
                    field.set(obj, instance);
                } catch (Exception e) {
                    throw new RuntimeException("Injection failed for field: " + field.getName(), e);
                }
            }
        }

        return obj;
    }

    private Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Unable to find config.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties", e);
        }
        return properties;
    }
}