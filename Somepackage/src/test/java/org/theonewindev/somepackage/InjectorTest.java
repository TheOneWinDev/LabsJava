package org.theonewindev.somepackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InjectorTest {

    private Injector injector;
    private SomeBean someBean;

    /**
     * Инициализация перед каждым тестом. Создает экземпляры инжектора и SomeBean.
     */
    @BeforeEach
    void setUp() {
        injector = new Injector();
        someBean = new SomeBean();
    }

    /**
     * Тест для инъекции зависимости с единственным интерфейсом.
     * Проверяет, что интерфейс корректно внедряется и его метод работает.
     */
    @Test
    void testInjectSingleInterface() {
        // Создаем бин с единственным интерфейсом
        class SomeBeanWithSingleInterface {
            @AutoInjectable
            private SomeInterface someInterface;
        }

        SomeBeanWithSingleInterface bean = new SomeBeanWithSingleInterface();
        // Предполагаем, что в файле свойств есть правильная запись для SomeInterface
        System.setProperty("config.properties", "src/test/resources/config.properties");

        // Выполняем инъекцию зависимости
        injector.inject(bean);

        // Проверяем, что поле внедрено
        assertNotNull(bean.someInterface);

        // Проверяем, что метод работает корректно
        bean.someInterface.doSomething();
    }

    /**
     * Тест для случая, когда в бине нет аннотаций @AutoInjectable.
     * Проверяет, что инъекция не происходит, если поля не аннотированы.
     */
    @Test
    void testInjectWithNoAnnotations() {
        // Создаем бин без аннотаций @AutoInjectable
        class SomeBeanWithoutAnnotations {
            private SomeInterface someInterface;
            private SomeOtherInterface someOtherInterface;
        }

        SomeBeanWithoutAnnotations beanWithoutAnnotations = new SomeBeanWithoutAnnotations();
        injector.inject(beanWithoutAnnotations);

        // Проверяем, что инъекция не происходит, если поля не аннотированы
        assertNull(beanWithoutAnnotations.someInterface);
        assertNull(beanWithoutAnnotations.someOtherInterface);
    }

    /**
     * Новый тест: Проверка корректной инъекции, когда интерфейс указан в файле свойств.
     * Проверяет, что зависимости внедряются правильно, если они указаны в конфигурационном файле.
     */
    @Test
    void testInjectWhenInterfaceIsInProperties() {
        // Создаем бин с полем, аннотированным @AutoInjectable
        class SomeBeanWithValidInterface {
            @AutoInjectable
            private SomeInterface someInterface;
        }

        SomeBeanWithValidInterface bean = new SomeBeanWithValidInterface();

        // Симулируем, что интерфейс SomeInterface корректно настроен в config.properties
        System.setProperty("config.properties", "src/test/resources/config.properties");

        // Выполняем инъекцию зависимостей
        injector.inject(bean);

        // Проверяем, что поле внедрено
        assertNotNull(bean.someInterface);

        // Проверяем, что метод работает корректно
        bean.someInterface.doSomething();
    }
}