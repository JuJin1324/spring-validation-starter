package starter.spring.validation.core.validation;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Created by Yoo Ju Jin(jujin1324@daum.net)
 * Created Date : 2023/08/24
 */
public abstract class SelfValidating<T> {
	private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static final Validator validator = factory.getValidator();
	private final Class<T> selfClass;

	protected SelfValidating(Class<T> selfClass) {
		this.selfClass = selfClass;
	}

	protected void validateSelf() {
		Set<ConstraintViolation<T>> violations = validator.validate(selfClass.cast(this));
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(violations);
		}
	}
}
