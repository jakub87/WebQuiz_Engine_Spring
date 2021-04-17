package engine.service;

import engine.model.Answer;

import engine.model.CompletionQuiz;
import engine.model.DTO.AnswerDTO;
import engine.model.DTO.CompletionQuizDTO;
import engine.model.DTO.QuizDTO;
import engine.model.Quiz;
import engine.model.User;

import engine.repository.CompletionQuizRepository;
import engine.repository.QuizRepository;
import engine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@Service
public class QuizService {

    private QuizRepository quizRepository;
    private UserRepository userRepository;
    private CompletionQuizRepository completionQuizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository, UserRepository userRepository, CompletionQuizRepository completionQuizRepository) {
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
        this.completionQuizRepository = completionQuizRepository;
    }

    public Quiz addQuiz(QuizDTO quizDTO, String loggedUserEmailAddress) {
        User author = userRepository.findByEmail(loggedUserEmailAddress);
        Quiz quiz = new Quiz(quizDTO.getTitle(), quizDTO.getText(), quizDTO.getOptions(), quizDTO.getAnswer(), author);
        quizRepository.save(quiz);
        return quiz;
    }

    public Page<Quiz> getQuizList(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 10);
        return quizRepository.findAll(pageable);
    }

    public Optional<Quiz> getQuizById(long quizId) {
        return quizRepository.findById(quizId);
    }

    public Page<CompletionQuizDTO> getCompletedQuizzes(int pageNumber, String loggedUserEmailAddress ){
        User user = userRepository.findByEmail(loggedUserEmailAddress);
        Pageable pageable = PageRequest.of(pageNumber, 10, Sort.by("completedAt").descending());
        return completionQuizRepository.getCompletionQuizzes(user, pageable);
    }

    public Answer resolveQuiz(long quizId, AnswerDTO answerDTO, String loggedUserEmailAddress) {
        int[] answerUser = answerDTO.getAnswer();
        int[] answerQuiz = quizRepository.findById(quizId).get().getAnswer();
        if (Arrays.equals(answerQuiz, answerUser)) {
            User user = userRepository.findByEmail(loggedUserEmailAddress);
            quizRepository.findById(quizId).ifPresent( quiz -> completionQuizRepository.save(new CompletionQuiz(LocalDateTime.now(), user, quiz)));
            return new Answer(true, "Congratulations, you're right!");
        } else {
            return new Answer(false, "Wrong answer! Please, try again.");
        }
    }

    public String deleteQuiz(long quizId, String loggedUserEmailAddress) {
         Quiz quiz = quizRepository.findById(quizId).get();
         if (loggedUserEmailAddress.equals(quiz.getAuthor().getEmail())) {
                quizRepository.delete(quiz);
                return "success";
         } else {
                return "notAuthorized";
         }
    }
}
