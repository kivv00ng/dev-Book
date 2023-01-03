package devkdt.devBook.member.exception;

public class NotFoundLoginMemberException extends MemberException {

    public NotFoundLoginMemberException() {
        this("입력하신 유저가 존재하지 않습니다.");
    }

    public NotFoundLoginMemberException(String message) {
        super(message);
    }
}
