package peaksoft.service.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.Like;
import peaksoft.repo.LikeRepo;
import peaksoft.service.LikeService;
@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepo likeRepo;
    @Override
    public void save(Like like) {
        likeRepo.save(like);
    }
    @Override
    public Like getLikeByPostIdAndUserId(Long userId, Long postId) {
        return likeRepo.getLikeByPostIdAndUserId(userId, postId);
    }
    @Override
    public Like getLikeByCommentIdAndUserId(Long userId, Long commentId) {
        return likeRepo.getLikeByCommentIdAndUserId(userId, commentId);
    }

    @Override
    public void changeIsLikeByUserIdAndCommentId(Long userId, Long commentId) {
        likeRepo.changeIsLikeByUserIdAndCommentId(userId, commentId);
    }

    @Override
    public void changeIsLikeByUserIdAndPostId(Long userId, Long postId) {
        likeRepo.changeIsLikeByUserIdAndPostId(userId, postId);
    }
}
