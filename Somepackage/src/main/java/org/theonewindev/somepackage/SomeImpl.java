package org.theonewindev.somepackage;

/**
 * Класс {@link SomeImpl} реализует интерфейс {@link SomeInterface} и предоставляет
 * конкретную реализацию метода {@link SomeInterface#doSomething()}.
 * <p>
 * Этот класс выводит символ "A" на консоль при вызове метода {@link SomeInterface#doSomething()}.
 * </p>
 *
 * @see SomeInterface
 */
public class SomeImpl implements SomeInterface {

    /**
     * Реализация метода {@link SomeInterface#doSomething()}. Этот метод выводит на экран символ "A".
     */
    public void doSomething() {
        System.out.println("A");
    }
}