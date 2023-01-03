package devkdt.devBook.member.exception;

public class NotFoundByIdMemberException extends MemberException {

    public NotFoundByIdMemberException(Long memberId) {
        this(memberId + "에 해당하는 member를 찾을 수 없습니다.");
    }

    public NotFoundByIdMemberException(String message) {
        super(message);
    }
}
