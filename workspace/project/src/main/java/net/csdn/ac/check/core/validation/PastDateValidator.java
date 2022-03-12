package net.csdn.ac.check.core.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * 自定义校验，必须是过去的时间
 *
 * @author lzpeng
 * @version 1.0
 * @date 2022/3/12 10:28
 */
public class PastDateValidator implements ConstraintValidator<PastDate, Object> {
    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        String dateStr = String.valueOf(value);
        LocalDate localDate = LocalDate.parse(dateStr);
        return localDate.isBefore(LocalDate.now());
    }
}
