package engine.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;
    private String password;
    private boolean status;

    @OneToMany(mappedBy = "author")
    private List<Quiz> quizList;

    @OneToMany(mappedBy = "author")
    private List<CompletionQuiz> completionQuizList;

    public User() {
    }

    public User(String email, String password) {
        quizList = new ArrayList<>();
        completionQuizList = new ArrayList<>();
        this.email = email;
        this.password = password;
        this.status = true;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }
}
