package peaksoft.repo;

import peaksoft.entities.Follower;
import peaksoft.entities.User;

import java.util.List;

public interface FollowerRepo {
    List<User> search(String usernameOrFullName);
    void subscribe(User subscribeUser, User subscriptUser);
    List<User> getAllSubscribersByUserId(Long userId);
    List<User> getAllSubscriptionsByUserId(Long userId);
    Follower getFollowerByUserId(Long userId);
    boolean isUserSubscribed(User subscribeUser, User userBeingSubscribedTo);
}
