package peaksoft.repo;

import peaksoft.entities.Comment;

import java.util.List;

public interface CommentRepo {
    void save(Comment comment);
    List<Comment> findAllByPostId(Long postId);
    void deleteById(Long commentId);
}
