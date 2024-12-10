package org.theonewindev.somepackage;

/**
 * Класс {@link OtherImpl} реализует интерфейс {@link SomeInterface} и предоставляет
 * конкретную реализацию метода {@link SomeInterface#doSomething()}.
 * <p>
 * Этот класс выводит символ "B" на консоль при вызове метода {@link SomeInterface#doSomething()}.
 * </p>
 *
 * @see SomeInterface
 */
public class OtherImpl implements SomeInterface {

    /**
     * Реализация метода {@link SomeInterface#doSomething()}. Этот метод выводит на экран символ "B".
     */
    public void doSomething() {
        System.out.println("B");
    }
}