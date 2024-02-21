package peaksoft.repo.impls;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entities.UserInfo;
import peaksoft.repo.UserInfoRepo;

@Repository
@RequiredArgsConstructor
@Transactional
public class UserInfoRepositoryImpl implements UserInfoRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public UserInfo findUserInfoByUserId(Long userId) {
        try {
            return entityManager.createQuery(
                            "SELECT ui FROM UserInfo ui WHERE ui.user.id = :userId", UserInfo.class)
                    .setParameter("userId", userId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void update(Long userInfoId, UserInfo userInfo) {
        UserInfo existingUserInfo = findUserInfoByUserId(userInfoId);
        if (existingUserInfo != null) {
            existingUserInfo.setBiography(userInfo.getBiography());
            existingUserInfo.setGender(userInfo.getGender());
            existingUserInfo.setFullName(userInfo.getFullName());
            existingUserInfo.setImage(userInfo.getImage());
            entityManager.merge(existingUserInfo);
        }
    }


    @Override
    public void changeImage(Long userInfoId, String image) {
        UserInfo userInfo = findUserInfoByUserId(userInfoId);
        if (userInfo != null) {
            userInfo.setImage(image);
            entityManager.merge(userInfo);
        }
    }


    @Override
    public void deleteImage(Long userInfoId) {
        UserInfo userInfo = findUserInfoByUserId(userInfoId);
        if (userInfo != null) {
            userInfo.setImage(null);
            entityManager.merge(userInfo);
        }
    }

}
