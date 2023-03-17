package devkdt.devBook.evaluation.dto;

import devkdt.devBook.book.domain.Book;
import lombok.Getter;

@Getter
public class EvaluationRequest {

  public static final String DISLIKE = "dislike";
  public static final String DEV_COURSE = "devCourse";
  public static final String JUNIOR = "junior";
  public static final String MIDDLE = "middle";

  public String evaluationType;

  public EvaluationRequest() {
  }

  public EvaluationRequest(String evaluationType) {
    this.evaluationType = evaluationType;
  }

  public void addEvaluation(Book book) {
    if (this.evaluationType.equals(DISLIKE)) {
      book.addDisLike();
    } else if (this.evaluationType.equals(DEV_COURSE)) {
      book.addDevCourse();
    } else if (this.evaluationType.equals(JUNIOR)) {
      book.addJunior();
    } else {
      book.addMiddle();
    }
  }
}