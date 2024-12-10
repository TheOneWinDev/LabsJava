package org.theonewindev.somepackage;

import java.lang.annotation.*;

/**
 * Аннотация, используемая для пометки полей, которые должны быть автоматически инжектированы.
 * Эта аннотация указывает, что поле должно быть заполнено с помощью механизма инъекции зависимостей.
 * <p>
 * Аннотация применяется к полям классов, и механизм инъекции будет искать соответствующие
 * реализации, чтобы автоматически присвоить значения этим полям во время работы программы.
 * </p>
 *
 * <p>Пример использования:</p>
 * <pre>
 * public class SomeClass {
 *     {@code @AutoInjectable}
 *     private SomeInterface someInterface;
 * }
 * </pre>
 *
 * @see Injector
 */
@Target(ElementType.FIELD)  // Указывает, что аннотация может применяться только к полям
@Retention(RetentionPolicy.RUNTIME)  // Аннотация доступна во время выполнения программы
public @interface AutoInjectable {
}