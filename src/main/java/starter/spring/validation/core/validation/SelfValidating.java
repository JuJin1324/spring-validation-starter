package starter.spring.validation.core.validation;

import javax.validation.*;
import java.util.Set;

/**
 * Created by Yoo Ju Jin(jujin1324@daum.net)
 * Created Date : 2023/08/24
 */
public abstract class SelfValidating<T> {
    private final Validator validator;

    public SelfValidating() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    protected void validateSelf() {
        Set<ConstraintViolation<T>> violations = validator.validate((T) this);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
