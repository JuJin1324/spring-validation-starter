package starter.spring.validation.user.service;

import lombok.Getter;
import starter.spring.validation.core.validation.SelfValidating;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by Yoo Ju Jin(jujin1324@daum.net)
 * Created Date : 2023/08/24
 */

@Getter
public class CreateUserCommand extends SelfValidating<CreateUserCommand> {
    @NotNull
    private final String name;
    @Min(10)
    private final int age;

    public CreateUserCommand(String name, int age) {
        super(CreateUserCommand.class);

        this.name = name;
        this.age = age;

        this.validateSelf();
    }
}
