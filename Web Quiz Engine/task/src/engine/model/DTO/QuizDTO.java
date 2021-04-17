package engine.model.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class QuizDTO {
    @NotBlank
    private String title;
    @NotBlank
    private String text;
    @NotNull
    @Size(min = 2)
    private String [] options;

    private int [] answer;

    public QuizDTO() {
    }

    public QuizDTO(@NotBlank String title, @NotBlank String text, @Size(min = 2) String[] options, int[] answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int[] getAnswer() {
        return answer;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }
}
