package org.theonewindev.somepackage;

/**
 * Класс {@link SomeBean} представляет собой контейнер для зависимостей, которые автоматически инжектируются
 * с помощью аннотации {@link AutoInjectable}.
 * <p>
 * Этот класс содержит два поля: {@link #field1} и {@link #field2}, которые инжектируются через {@link Injector}.
 * Метод {@link #foo()} вызывает методы {@link SomeInterface#doSomething()} и {@link SomeOtherInterface#doSomething()}
 * для выполнения логики, связанной с этими интерфейсами.
 * </p>
 *
 * @see AutoInjectable
 * @see Injector
 * @see SomeInterface
 * @see SomeOtherInterface
 */
public class SomeBean {

    /**
     * Поле, которое инжектируется с помощью аннотации {@link AutoInjectable}.
     * Это поле будет автоматически заполнено объектом, реализующим {@link SomeInterface}.
     */
    @AutoInjectable
    private SomeInterface field1;

    /**
     * Поле, которое инжектируется с помощью аннотации {@link AutoInjectable}.
     * Это поле будет автоматически заполнено объектом, реализующим {@link SomeOtherInterface}.
     */
    @AutoInjectable
    private SomeOtherInterface field2;

    /**
     * Метод, который вызывает методы {@link SomeInterface#doSomething()} и {@link SomeOtherInterface#doSomething()}.
     * Этот метод используется для демонстрации работы инжектированных зависимостей.
     */
    public void foo() {
        field1.doSomething();
        field2.doSomething();
    }
}