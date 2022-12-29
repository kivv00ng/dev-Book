package devkdt.devBook.dto;

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
}