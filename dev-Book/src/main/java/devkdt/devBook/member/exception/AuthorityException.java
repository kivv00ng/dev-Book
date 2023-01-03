package devkdt.devBook.member.exception;

public class AuthorityException extends RuntimeException {
    public AuthorityException() {
        this("입력된 value값에 해당하는 권한을 찾을 수 없습니다.");
    }

    public AuthorityException(String message) {
        super(message);
    }
}
