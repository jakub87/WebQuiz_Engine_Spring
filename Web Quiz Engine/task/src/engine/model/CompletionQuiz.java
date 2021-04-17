package engine.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class CompletionQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime completedAt;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_author")
    private User author;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_quiz")
    private Quiz quiz;

    public CompletionQuiz(LocalDateTime completedAt, User author, Quiz quiz) {
        this.completedAt = completedAt;
        this.author = author;
        this.quiz = quiz;
    }

    public CompletionQuiz() {
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
}
