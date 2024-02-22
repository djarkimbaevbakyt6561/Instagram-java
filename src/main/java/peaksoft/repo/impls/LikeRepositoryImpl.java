package peaksoft.repo.impls;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Like;
import peaksoft.repo.LikeRepo;

@Repository
@RequiredArgsConstructor
@Transactional
public class LikeRepositoryImpl implements LikeRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void save(Like like) {
        try {
            entityManager.persist(like);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Like getLikeByPostIdAndUserId(Long userId, Long postId) {
        try {
            return entityManager.createQuery("SELECT l FROM Like l WHERE l.post.id = :postId AND l.user.id = :userId", Like.class)
                    .setParameter("postId", postId)
                    .setParameter("userId", userId)
                    .getSingleResult();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Like getLikeByCommentIdAndUserId(Long userId, Long commentId) {
        try {
            return entityManager.createQuery("SELECT l FROM Like l WHERE l.comment.id = :commentId AND l.user.id = :userId", Like.class)
                    .setParameter("commentId", commentId)
                    .setParameter("userId", userId)
                    .getSingleResult();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void changeIsLikeByUserIdAndCommentId(Long userId, Long commentId) {
        try {
            Like like = getLikeByCommentIdAndUserId(userId, commentId);
            like.setLike(!like.isLike());
            entityManager.merge(like);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void changeIsLikeByUserIdAndPostId(Long userId, Long postId) {
        try {
            Like like = getLikeByPostIdAndUserId(userId, postId);
            like.setLike(!like.isLike());
            entityManager.merge(like);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
