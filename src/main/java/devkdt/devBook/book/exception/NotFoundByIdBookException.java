package devkdt.devBook.book.exception;

public class NotFoundByIdBookException extends BookException {

    public NotFoundByIdBookException(Long postId) {
        this(postId + "에 해당하는 책을 찾을 수 없습니다.");
    }

    public NotFoundByIdBookException(String message) {
        super(message);
    }
}
