package devkdt.devBook.evaluation.dto;

import devkdt.devBook.book.domain.Book;
import lombok.Data;

@Data
public class EvaluationRequest {
    private boolean devCourse;
    private boolean junior;
    private boolean middle;

    public EvaluationRequest() {
    }

    public EvaluationRequest(boolean devCourse, boolean junior, boolean middle) {
        this.devCourse = devCourse;
        this.junior = junior;
        this.middle = middle;
    }

    public void addEvaluation(Book book) {
        if (this.isDevCourse()) {
            book.addDevCourse();
        } else if (this.isJunior()) {
            book.addJunior();
        } else {
            book.addMiddle();
        }

    }
}