package devkdt.devBook.book.exception;

public class ImageIOException extends RuntimeException {

    public ImageIOException() {
    }

    public ImageIOException(Long bookId) {
        this(bookId + "아이디를 가지는 책의 이미지 저장에서 문제가 발생하였습니다.");
    }

    public ImageIOException(String message) {
        super(message);
    }
}
