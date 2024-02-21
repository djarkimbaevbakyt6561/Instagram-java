package peaksoft.service;

import peaksoft.entities.UserInfo;

public interface UserInfoService {
    UserInfo findUserInfoByUserId(Long userId);
    void update(Long userInfoId, UserInfo userInfo);
}
