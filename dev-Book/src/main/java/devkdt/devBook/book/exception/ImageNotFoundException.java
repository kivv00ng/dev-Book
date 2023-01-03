package devkdt.devBook.book.exception;

public class ImageNotFoundException extends RuntimeException {

    public ImageNotFoundException() {
    }

    public ImageNotFoundException(String fileName) {
        super(fileName + "에 해당하는 이미지를 찾는데 문제가 발생하였습니다.");
    }
}
