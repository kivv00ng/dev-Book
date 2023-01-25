package devkdt.devBook.global.error;

import devkdt.devBook.book.exception.ImageIOException;
import devkdt.devBook.book.exception.ImageNotFoundException;
import devkdt.devBook.book.exception.BookException;
import devkdt.devBook.member.exception.MemberException;
import devkdt.devBook.global.error.dto.ErrorReportRequest;
import devkdt.devBook.global.error.dto.ErrorResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerAdvice {

  private static final Logger log = LoggerFactory.getLogger(ControllerAdvice.class);

  @ExceptionHandler({
      BookException.class,
      MemberException.class
  })
  public ResponseEntity<ErrorResponse> handleInvalidData(RuntimeException e) {
    ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
    return ResponseEntity.badRequest().body(errorResponse);
  }

  @ExceptionHandler({
      ImageIOException.class,
      ImageNotFoundException.class
  })
  public ResponseEntity<ErrorResponse> handleImageIO(RuntimeException e) {
    ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
    return ResponseEntity.internalServerError().body(errorResponse);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponse> handleInvalidRequestBody() {
    ErrorResponse errorResponse = new ErrorResponse("잘못된 형식의 Request Body 입니다!");
    return ResponseEntity.badRequest().body(errorResponse);
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ErrorResponse> handleTypeMismatch() {
    ErrorResponse errorResponse = new ErrorResponse("잘못된 데이터 타입입니다!");
    return ResponseEntity.badRequest().body(errorResponse);
  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ErrorResponse> handleNotSupportedMethod() {
    ErrorResponse errorResponse = new ErrorResponse("지원하지 않는 HTTP 메소드 요청입니다!");
    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorResponse);
  }

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity<ErrorResponse> handleFileSizeLimitedMethod() {
    ErrorResponse errorResponse = new ErrorResponse("허용가능한 파일 사이즈를 초과하였습니다.");
    return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body(errorResponse);
  }


  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleUnexpectedException(final Exception e,
      final HttpServletRequest request) {
    ErrorReportRequest errorReport = new ErrorReportRequest(request, e);
    log.error(errorReport.getLogMessage(), e);

    ErrorResponse errorResponse = new ErrorResponse("예상 하지 못한 서버 에러가 발생하였습니다..");
    return ResponseEntity.internalServerError().body(errorResponse);
  }

}
