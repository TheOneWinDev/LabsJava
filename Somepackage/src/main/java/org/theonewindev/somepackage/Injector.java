package org.theonewindev.somepackage;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

/**
 * Класс, отвечающий за инъекцию зависимостей в объекты.
 * <p>
 * Этот класс использует рефлексию для поиска полей, помеченных аннотацией {@link AutoInjectable},
 * и автоматически заполняет их соответствующими объектами на основе настроек из конфигурационного файла {@code config.properties}.
 * </p>
 * <p>
 * Инъекция происходит путем получения имени класса для каждого типа поля и создания его экземпляра.
 * </p>
 *
 * <p>Пример использования:</p>
 * <pre>
 * Injector injector = new Injector();
 * SomeBean someBean = new SomeBean();
 * injector.inject(someBean);
 * </pre>
 *
 * @see AutoInjectable
 * @see Properties
 */
public class Injector {

    /**
     * Внедряет зависимости в поля объекта, помеченные аннотацией {@link AutoInjectable}.
     * <p>
     * Метод находит все поля объекта, проверяет наличие аннотации {@link AutoInjectable},
     * извлекает имя класса из конфигурационного файла и создает объект для каждого поля.
     * </p>
     *
     * @param obj объект, в который нужно инжектировать зависимости.
     * @param <T> тип объекта.
     * @return объект с инжектированными зависимостями.
     * @throws RuntimeException если не удается найти или создать зависимость.
     */
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

    /**
     * Загружает свойства из конфигурационного файла {@code config.properties}.
     * <p>
     * Этот метод читает файл {@code config.properties} из ресурсов и загружает в объект {@link Properties}.
     * </p>
     *
     * @return объект {@link Properties} с загруженными значениями.
     * @throws RuntimeException если не удается загрузить файл конфигурации.
     */
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