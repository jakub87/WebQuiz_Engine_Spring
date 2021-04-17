package engine.model.DTO;

public class AnswerDTO {
    private int answer[];

    public AnswerDTO(int[] answer) {
        this.answer = answer;
    }

    public AnswerDTO() {
    }

    public int[] getAnswer() {
        return answer;
    }

    public void setAnswer(int[] answer) {
        this.answer = answer;
    }
}
