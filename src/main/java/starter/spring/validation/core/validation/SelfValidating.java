package starter.spring.validation.core.validation;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by Yoo Ju Jin(jujin1324@daum.net)
 * Created Date : 2023/08/24
 */
public abstract class SelfValidating<T> {
    protected void validateSelf() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        try (factory) {
            Set<ConstraintViolation<T>> violations = factory.getValidator().validate((T) this);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }
        }
    }
}
