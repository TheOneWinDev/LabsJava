package org.theonewindev.somepackage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InjectorTest {

    private Injector injector;
    private SomeBean someBean;

    @BeforeEach
    void setUp() {
        injector = new Injector();
        someBean = new SomeBean();
    }

    @Test
    void testInjectSingleInterface() {
        // Create a bean with a single interface
        class SomeBeanWithSingleInterface {
            @AutoInjectable
            private SomeInterface someInterface;
        }

        SomeBeanWithSingleInterface bean = new SomeBeanWithSingleInterface();
        // Assuming the properties file has a valid entry for SomeInterface
        System.setProperty("config.properties", "src/test/resources/config.properties");

        // Inject the dependency
        injector.inject(bean);

        // Assert that the field is injected
        assertNotNull(bean.someInterface);

        // Check if the method works correctly
        bean.someInterface.doSomething();
    }

    @Test
    void testInjectWithNoAnnotations() {
        // Create a bean without any @AutoInjectable annotations
        class SomeBeanWithoutAnnotations {
            private SomeInterface someInterface;
            private SomeOtherInterface someOtherInterface;
        }

        SomeBeanWithoutAnnotations beanWithoutAnnotations = new SomeBeanWithoutAnnotations();
        injector.inject(beanWithoutAnnotations);

        // Assert that no injection happens when there are no annotations
        assertNull(beanWithoutAnnotations.someInterface);
        assertNull(beanWithoutAnnotations.someOtherInterface);
    }

    // New test: Check valid injection with the correct interface in properties
    @Test
    void testInjectWhenInterfaceIsInProperties() {
        // Create a bean with a field annotated with @AutoInjectable
        class SomeBeanWithValidInterface {
            @AutoInjectable
            private SomeInterface someInterface;
        }

        SomeBeanWithValidInterface bean = new SomeBeanWithValidInterface();

        // Simulate that the SomeInterface is correctly mapped in config.properties
        System.setProperty("config.properties", "src/test/resources/config.properties");

        // Inject the dependencies
        injector.inject(bean);

        // Assert that the field is injected
        assertNotNull(bean.someInterface);

        // Check if the method works correctly
        bean.someInterface.doSomething();
    }
}