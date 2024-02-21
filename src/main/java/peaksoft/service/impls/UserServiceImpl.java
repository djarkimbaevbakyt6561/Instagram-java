package peaksoft.service.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.User;
import peaksoft.repo.UserRepo;
import peaksoft.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    @Override
    public User findUserById(Long userId) {
        return userRepo.findUserById(userId);
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public User findByEmailOrUserName(String emailOrUsername) {
        return userRepo.findByEmailOrUserName(emailOrUsername);
    }

    @Override
    public boolean isUsernameOrEmailUnique(String username, String email) {
        return userRepo.isUsernameOrEmailUnique(username, email);
    }

    @Override
    public void update(User user) {
        userRepo.update(user);
    }

    @Override
    public void delete(User user) {
        userRepo.delete(user);
    }
}
