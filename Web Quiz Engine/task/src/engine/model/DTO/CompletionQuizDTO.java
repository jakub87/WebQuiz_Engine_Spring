package engine.model.DTO;

import java.time.LocalDateTime;

public class CompletionQuizDTO {
    private Long id;
    private LocalDateTime completedAt;

    public CompletionQuizDTO(Long id, LocalDateTime completedAt) {
        this.id = id;
        this.completedAt = completedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }
}

