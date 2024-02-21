package peaksoft.service.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.Follower;
import peaksoft.entities.User;
import peaksoft.repo.FollowerRepo;
import peaksoft.service.FollowerService;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FollowerServiceImpl implements FollowerService {
    private final FollowerRepo followerRepo;
    @Override
    public List<User> search(String usernameOrFullName) {
        return followerRepo.search(usernameOrFullName);
    }

    @Override
    public void subscribe(User subscribeUser, User userBeingSubscribedTo) {
        followerRepo.subscribe(subscribeUser, userBeingSubscribedTo);
    }

    @Override
    public List<User> getAllSubscribersByUserId(Long userId) {
        return followerRepo.getAllSubscribersByUserId(userId);
    }

    @Override
    public List<User> getAllSubscriptionsByUserId(Long userId) {
        return followerRepo.getAllSubscriptionsByUserId(userId);
    }

    @Override
    public Follower getFollowerByUserId(Long userId) {
        return followerRepo.getFollowerByUserId(userId);
    }

    @Override
    public boolean isUserSubscribed(User subscribeUser, User userBeingSubscribedTo) {
        return followerRepo.isUserSubscribed(subscribeUser,userBeingSubscribedTo);
    }

}
