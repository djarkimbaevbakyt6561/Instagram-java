package peaksoft.service.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.UserInfo;
import peaksoft.repo.UserInfoRepo;
import peaksoft.service.UserInfoService;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implements UserInfoService {
    private final UserInfoRepo userInfoRepo;

    @Override
    public UserInfo findUserInfoByUserId(Long userId) {
        return userInfoRepo.findUserInfoByUserId(userId);
    }

    @Override
    public void update(Long userInfoId, UserInfo userInfo) {
        userInfoRepo.update(userInfoId, userInfo);
    }

}
