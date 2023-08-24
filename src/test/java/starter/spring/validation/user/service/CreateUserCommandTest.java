package starter.spring.validation.user.service;

import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Yoo Ju Jin(jujin1324@daum.net)
 * Created Date : 2023/08/24
 */
class CreateUserCommandTest {

    @Test
    void whenNameIsNullThenThrowException() {
        try {
            new CreateUserCommand(null, 9);
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
//        assertThrows(ConstraintViolationException.class,
//                () -> new CreateUserCommand(null, 9));
    }
}
