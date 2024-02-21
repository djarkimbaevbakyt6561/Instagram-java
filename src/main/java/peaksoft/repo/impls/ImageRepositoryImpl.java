package peaksoft.repo.impls;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Image;
import peaksoft.repo.ImageRepo;

@Repository
@RequiredArgsConstructor
@Transactional
public class ImageRepositoryImpl implements ImageRepo {
    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    public void save(Image image) {
        try {
            entityManager.persist(image);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
