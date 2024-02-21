package peaksoft.repo.impls;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Post;
import peaksoft.entities.User;
import peaksoft.repo.PostRepo;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
@Transactional
public class PostRepositoryImpl implements PostRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void save(Post post) {
        try {
            entityManager.persist(post);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Post> getAllPostsByUserId(Long userId) {
        try {
            String queryString = "SELECT p FROM Post p WHERE p.user.id = :userId ORDER BY p.createdAt DESC";
            return entityManager
                    .createQuery(queryString, Post.class)
                    .setParameter("userId", userId)
                    .getResultList();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }


    @Override
    public Post getPostById(Long postId) {
        try {
            String queryString = "SELECT p FROM Post p WHERE p.id = :postId";
            return entityManager
                    .createQuery(queryString, Post.class)
                    .setParameter("postId", postId)
                    .getSingleResult();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Post> getAllPostsOfSubsAndOwnByUserId(Long userId) {
        try {
            String queryString = "SELECT f.subscriptions FROM Follower f WHERE f.user.id = :userId";
            List<User> subscribedUsers = entityManager
                    .createQuery(queryString, User.class)
                    .setParameter("userId", userId)
                    .getResultList();

            List<Post> ownPosts = getAllPostsByUserId(userId);

            List<Post> postsOfSubscribedUsers = subscribedUsers.stream()
                    .flatMap(user -> getAllPostsByUserId(user.getId()).stream())
                    .collect(Collectors.toList());

            ownPosts.addAll(postsOfSubscribedUsers);

            return ownPosts;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }


    @Override
    public void update(Post post) {
        try {
            entityManager.merge(post);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Post post) {
        try {
            entityManager.remove(post);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
