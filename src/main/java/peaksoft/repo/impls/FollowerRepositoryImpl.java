package peaksoft.repo.impls;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Follower;
import peaksoft.entities.User;
import peaksoft.repo.FollowerRepo;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class FollowerRepositoryImpl implements FollowerRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<User> search(String usernameOrFullName) {
        String queryString = "SELECT u FROM User u JOIN FETCH u.follower WHERE u.username LIKE :search OR u.userInfo.fullName LIKE :search";
        return entityManager
                .createQuery(queryString, User.class)
                .setParameter("search", "%" + usernameOrFullName + "%")
                .getResultList();
    }

    @Override
    public void subscribe(User subscribeUser, User userBeingSubscribedTo) {
        try {
            Follower userBeingSubscribedToFollower = getFollowerByUserId(userBeingSubscribedTo.getId());
            Follower subscribeUserFollower = getFollowerByUserId(subscribeUser.getId());
            boolean isSubscribed = isUserSubscribed(subscribeUser, userBeingSubscribedTo);
            if(isSubscribed) {
                userBeingSubscribedToFollower.getSubscribers().remove(subscribeUser);
                subscribeUserFollower.getSubscriptions().remove(userBeingSubscribedTo);
            } else {
                userBeingSubscribedToFollower.getSubscribers().add(subscribeUser);
                subscribeUserFollower.getSubscriptions().add(userBeingSubscribedTo);
            }
            entityManager.merge(userBeingSubscribedTo);
            entityManager.merge(subscribeUser);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    @Override
    public boolean isUserSubscribed(User subscribeUser, User userBeingSubscribedTo) {
        Follower userBeingSubscribedToFollower = getFollowerByUserId(userBeingSubscribedTo.getId());
        return userBeingSubscribedToFollower.getSubscribers().contains(subscribeUser);
    }

    @Override
    public List<User> getAllSubscribersByUserId(Long userId) {
        String queryString = "SELECT f.subscribers FROM Follower f JOIN f.user u WHERE u.id = :userId";
        return entityManager.createQuery(queryString, User.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<User> getAllSubscriptionsByUserId(Long userId) {
        String queryString = "SELECT f.subscriptions FROM Follower f JOIN f.user u WHERE u.id = :userId";
        return entityManager.createQuery(queryString, User.class)
                .setParameter("userId", userId)
                .getResultList();
    }


    @Override
    public Follower getFollowerByUserId(Long userId) {
        try {
            return entityManager.createQuery(
                            "SELECT f FROM Follower f WHERE f.user.id = :userId", Follower.class)
                    .setParameter("userId", userId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
