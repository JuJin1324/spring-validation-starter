# spring-validation-starter

## 클래스 자체 유효성 검증
> 스프링 프레임워크가 아닌 DTO 클래스 자체에서 필드에 대한 유효성 검증을 진행한다.  

### dependencies
> ```groovy
> dependencies {
>     ...
>     implementation 'org.springframework.boot:spring-boot-starter-validation'
> }
> ```

### SelfValidating 클래스
> DTO 클래스의 생성자 내부에서 유효성 검증을 하여 객체 생성 시 유효성 검증을 진행하기 위해서 `SelfValidating` 추상 클래스를
> 직접 작성한다.  
> 
> SelfValidating.java
> ```java
> public abstract class SelfValidating<T> {
>     private final Validator validator;
> 
>     public SelfValidating() {
>         ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
>         validator = factory.getValidator();
>     }
> 
>     protected void validateSelf() {
>         Set<ConstraintViolation<T>> violations = validator.validate((T) this);
>         if (!violations.isEmpty()) {
>             throw new ConstraintViolationException(violations);
>         }
>     }
> }
> ```

### 유효성 검증이 필요한 DTO 클래스
> CreateUserCommand.java
> ```java
> @Getter
> public class CreateUserCommand extends SelfValidating<CreateUserCommand> {
>     @NotNull
>     private final String name;
>     @Min(10)
>     private final int age;
> 
>     public CreateUserCommand(String name, int age) {
>         this.name = name;
>         this.age = age;
> 
>         this.validateSelf();
>     }
> }
> ```

### 예외 메시지
> name 필드에 대한 `@NotNull` 애노테이션으로 발생한 디폴트 예외 메시지  
> ```
> javax.validation.ConstraintViolationException: name: must not be null
> ```
>
> age 필드에 대한 `@Min(10)` 애노테이션으로 발생한 디폴트 예외 메시지  
> ```
> javax.validation.ConstraintViolationException: age: must be greater than or equal to 10
> ```
