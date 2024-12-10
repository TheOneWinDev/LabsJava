package org.theonewindev.somepackage;

/**
 * Главный класс приложения, содержащий метод {@link #main(String[])} для запуска программы.
 * <p>
 * Этот класс демонстрирует использование класса {@link Injector} для инъекции зависимостей в объект {@link SomeBean}
 * и вызова метода {@link SomeBean#foo()} для выполнения определенной логики.
 * </p>
 *
 * <p>Пример работы программы:</p>
 * <pre>
 * Main.main(args);
 * </pre>
 *
 * @see Injector
 * @see SomeBean
 */
public class Main {

    /**
     * Основной метод, который запускает программу.
     * <p>
     * В этом методе создается объект {@link SomeBean}, в который инжектируются зависимости с помощью {@link Injector}.
     * Затем вызывается метод {@link SomeBean#foo()} для выполнения бизнес-логики.
     * </p>
     *
     * @param args аргументы командной строки, не используются в текущей реализации.
     */
    public static void main(String[] args) {
        SomeBean sb = new Injector().inject(new SomeBean());
        sb.foo();
    }
}