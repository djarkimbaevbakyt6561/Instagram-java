package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@SequenceGenerator(
        name = "base_gen",
        sequenceName = "comment_seq",
        allocationSize = 1
)
public class Comment extends BaseEntity{
    private String comment;
    private LocalDate createdAt;
    @ManyToOne
    private User user;
    @ManyToOne
    private Post post;
    @OneToMany(mappedBy = "comment")
    private List<Like> likes;
}
