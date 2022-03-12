package net.csdn.ac.check.core.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Past;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 自定义校验，必须是过去的时间
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/3/12 10:23
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Repeatable(PastDate.List.class)
@Documented
@Constraint(validatedBy = PastDateValidator.class)
public @interface PastDate {

    String message() default "{net.csdn.ac.check.core.validation.PastDate.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    /**
     * Defines several {@link Past} annotations on the same element.
     *
     * @see Past
     */
    @Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
    @Retention(RUNTIME)
    @Documented
    @interface List {

        PastDate[] value();
    }

}
