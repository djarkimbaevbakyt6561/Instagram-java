package peaksoft.repo.impls;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Comment;
import peaksoft.entities.Like;
import peaksoft.repo.CommentRepo;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class CommentRepositoryImpl implements CommentRepo {
    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    public void save(Comment comment) {
        try {
            entityManager.persist(comment);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    @Override
    public List<Comment> findAllByPostId(Long postId) {
        try {
            String queryString = "SELECT c FROM Comment c WHERE c.post.id = :postId";
            return entityManager.createQuery(queryString, Comment.class)
                    .setParameter("postId", postId)
                    .getResultList();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteById(Long commentId) {
        try {
            Comment comment = entityManager.find(Comment.class, commentId);
            entityManager.remove(comment);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
