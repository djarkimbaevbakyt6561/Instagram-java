package peaksoft.service.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.Comment;
import peaksoft.repo.CommentRepo;
import peaksoft.service.CommentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepo commentRepo;
    @Override
    public void save(Comment comment) {
        commentRepo.save(comment);
    }

    @Override
    public List<Comment> findAllByPostId(Long postId) {
        return commentRepo.findAllByPostId(postId);
    }

    @Override
    public void deleteById(Long commentId) {
        commentRepo.deleteById(commentId);
    }
}
