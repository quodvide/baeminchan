package codesquad.dto;

import codesquad.domain.Role;
import codesquad.domain.User;
import codesquad.exception.NotMatchException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserDto {
    private static final Logger log = LoggerFactory.getLogger(UserDto.class);

    @Pattern(regexp = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$", message = "아이디 형식에 맞지 않습니다.")
    private String userId;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$", message = "8-16자리 영문,숫자,특수문자로 조합되지 않았습니다.")
    private String password;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,16}$", message = "8-16자리 영문,숫자,특수문자로 조합되지 않았습니다.")
    private String rePassword;
    @Pattern(regexp = "^[가-힣]*$", message = "이름은 한글만 가능합니다.")
    @Size(min = 2, max = 16)
    private String name;
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "휴대 전화 번호 형식에 맞지 않습니다.")
    private String phoneNumber;

    public UserDto(String userId, String password, String name, String phoneNumber, String rePassword) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.rePassword = rePassword;
    }

    public UserDto(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public User toUser(String encodedPassword, Role role) throws NotMatchException {
        log.debug("password : {}", encodedPassword);
        if (!matchPassword()) throw new NotMatchException("비밀번호가 일치하지 않습니다.");
        return new User(userId, encodedPassword, name, phoneNumber, role);
    }


    private boolean matchPassword() {
        return rePassword.equals(password);
    }
}
