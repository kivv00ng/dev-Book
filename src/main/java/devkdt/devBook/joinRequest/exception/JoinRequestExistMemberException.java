package devkdt.devBook.joinRequest.exception;

public class JoinRequestExistMemberException extends JoinRequestException {

  public JoinRequestExistMemberException() {
    super("이미 존재하는 회원 입니다.");
  }

  public JoinRequestExistMemberException(String message) {
    super(message);
  }
}
