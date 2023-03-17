package devkdt.devBook.joinRequest.exception;

public class JoinRequestExistException extends JoinRequestException {

  public JoinRequestExistException() {
    super("이미 회원가입 요청을 보낸 사용자 입니다.");
  }

  public JoinRequestExistException(String message) {
    super(message);
  }
}
