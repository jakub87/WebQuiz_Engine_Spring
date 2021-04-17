package engine.controler;

import engine.model.Answer;
import engine.model.DTO.AnswerDTO;
import engine.model.DTO.CompletionQuizDTO;
import engine.model.DTO.QuizDTO;
import engine.model.DTO.UserDTO;
import engine.model.Quiz;
import engine.service.QuizService;
import engine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.NoSuchElementException;

@RestController
public class QuizControler {
    private QuizService quizService;
    private UserService userService;

    @Autowired
    public QuizControler(QuizService quizService, UserService userService) {
        this.quizService = quizService;
        this.userService = userService;
    }

    @PostMapping(value = "/api/quizzes", consumes = "application/json")
    public Quiz addQuiz(@Valid @RequestBody QuizDTO quizDTO, Principal principal) {
        return quizService.addQuiz(quizDTO, principal.getName());
    }

    @GetMapping("/api/quizzes")
    public Page<Quiz> getQuizzes(@RequestParam(defaultValue = "0", name = "page") int pageNumber) {
        return quizService.getQuizList(pageNumber);
    }

    @GetMapping("/api/quizzes/{id}")
    public Quiz getQuizById(@PathVariable ("id") long quizId) {
        return quizService.getQuizById(quizId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found"));
    }

    @PostMapping("/api/quizzes/{id}/solve")
    public Answer resolveQuiz(
            @PathVariable ("id") Long quizId,
            @RequestBody AnswerDTO answerDTO,
            Principal principal
            ) {
        try {
            return quizService.resolveQuiz(quizId, answerDTO,principal.getName());
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/quizzes/completed")
    public Page<CompletionQuizDTO> getCompletionQuizzes(@RequestParam(defaultValue = "0", name = "page") int pageNumber,
                                                        Principal principal) {
         return quizService.getCompletedQuizzes(pageNumber, principal.getName());
    }

    @PostMapping("/api/register")
    public void registerUser(@Valid @RequestBody UserDTO userDTO) {
        if (userService.registerUser(userDTO)){
            throw new ResponseStatusException(HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/quizzes/{id}")
    public void deleteQuiz(@PathVariable ("id") Long quizId, Principal principal) {
        try {
            String result = quizService.deleteQuiz(quizId, principal.getName());
            if (result.equals("success")) {
                throw new ResponseStatusException(HttpStatus.NO_CONTENT);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        } catch (NoSuchElementException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
