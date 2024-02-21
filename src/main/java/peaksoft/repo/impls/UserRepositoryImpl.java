package peaksoft.repo.impls;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Follower;
import peaksoft.entities.User;
import peaksoft.entities.UserInfo;
import peaksoft.repo.UserRepo;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserRepositoryImpl implements UserRepo {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public User findUserById(Long userId) {
        try {
            return entityManager
                    .createQuery("SELECT u FROM User u WHERE u.id = :userId", User.class)
                    .setParameter("userId", userId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(User user) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUser(user);
        entityManager.persist(userInfo);

        Follower follower = new Follower();
        follower.setUser(user);
        follower.setSubscribers(new ArrayList<>());
        follower.setSubscribers(new ArrayList<>());
        entityManager.persist(follower);

        entityManager.persist(user);
    }


    @Override
    public User findByEmailOrUserName(String emailOrUsername) {
        try {
            return entityManager.createQuery(
                            "SELECT u FROM User u WHERE u.email = :emailOrUsername OR u.username = :emailOrUsername", User.class)
                    .setParameter("emailOrUsername", emailOrUsername)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean isUsernameOrEmailUnique(String username, String email) {
        try {
            List<User> resultList = entityManager.createQuery(
                            "SELECT u FROM User u WHERE u.username = :username OR u.email = :email",
                            User.class)
                    .setParameter("username", username)
                    .setParameter("email", email)
                    .getResultList();
            return resultList.isEmpty();
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public void update(User user) {
        try {
            entityManager.merge(user);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(User user) {
        try {
            entityManager.remove(user);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
