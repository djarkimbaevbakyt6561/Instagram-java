package peaksoft.repo;

import peaksoft.entities.UserInfo;

public interface UserInfoRepo {
    UserInfo findUserInfoByUserId(Long userId);
    void update(Long userInfoId, UserInfo userInfo);
    void changeImage(Long userInfoId, String image);
    void deleteImage(Long userInfoId);
}
