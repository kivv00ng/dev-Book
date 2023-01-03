package devkdt.devBook.member.exception;


public class WrongPhoneNumberException extends MemberException {

    public WrongPhoneNumberException(String inputPhoneNumber) {
        super(inputPhoneNumber + "은 잘못된 PhoneNumber 형식입니다. ");
    }
}
