package engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String text;
    private String [] options;
    private int [] answer;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fk_author")
    private User author;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.REMOVE)
    private List<CompletionQuiz> completionQuizList;

    public Quiz() {
    }

    public Quiz(String title, String text, String[] options, int[] answer, User author) {
        completionQuizList = new ArrayList<>();
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
        this.author = author;
    }

    @JsonIgnore
    public User getAuthor() {
        return author;
    }

    @JsonProperty
    public void setAuthor(User author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @JsonIgnore
    public int[] getAnswer() {
        if (answer == null) {
            answer = new int[0];
        }
        return answer;
    }

    @JsonProperty
    public void setAnswer(int[] answer) {
        this.answer = answer;
    }
}
