package engine.repository;

import engine.model.CompletionQuiz;

import engine.model.DTO.CompletionQuizDTO;
import engine.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompletionQuizRepository extends JpaRepository<CompletionQuiz, Long> {
    @Query(value = "select new engine.model.DTO.CompletionQuizDTO(c.quiz.id, c.completedAt) from CompletionQuiz c where c.author = ?1")
    Page<CompletionQuizDTO> getCompletionQuizzes(User user, Pageable pageable);
}
