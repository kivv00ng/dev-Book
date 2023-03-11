package devkdt.devBook.evaluation.exception;

public class DuplicationException extends EvaluationException {

  public DuplicationException() {
  }

  public DuplicationException(Long memberId, Long bookId) {
    super(memberId + "(중복불가)유저는 이미 " + bookId + " 컨텐츠에 투표하였습니다.");
  }
}
