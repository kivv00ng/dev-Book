package devkdt.devBook.joinRequest.exception;

public class JoinRequestNotFoundException extends JoinRequestException {

  public JoinRequestNotFoundException() {
  }

  public JoinRequestNotFoundException(Long joinRequestId) {
    super(joinRequestId + "에 해당하는 joinRequest를 찾을 수 없습니다.");
  }
}
