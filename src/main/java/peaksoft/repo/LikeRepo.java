package peaksoft.repo;

import peaksoft.entities.Like;

public interface LikeRepo {
    void save(Like like);
    Like getLikeByPostIdAndUserId(Long userId, Long postId);
    Like getLikeByCommentIdAndUserId(Long userId, Long commentId);
    void changeIsLikeByUserIdAndCommentId(Long userId, Long commentId);
    void changeIsLikeByUserIdAndPostId(Long userId, Long postId);
}
