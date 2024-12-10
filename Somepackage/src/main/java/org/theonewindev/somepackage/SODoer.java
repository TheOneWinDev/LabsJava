package org.theonewindev.somepackage;

/**
 * Класс {@link SODoer} реализует интерфейс {@link SomeOtherInterface} и предоставляет
 * конкретную реализацию метода {@link SomeOtherInterface#doSomething()}.
 * <p>
 * Этот класс выводит символ "C" на консоль при вызове метода {@link SomeOtherInterface#doSomething()}.
 * </p>
 *
 * @see SomeOtherInterface
 */
public class SODoer implements SomeOtherInterface {

    /**
     * Реализация метода {@link SomeOtherInterface#doSomething()}. Этот метод выводит на экран символ "C".
     */
    public void doSomething() {
        System.out.println("C");
    }
}