package peaksoft.service.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.Image;
import peaksoft.repo.ImageRepo;
import peaksoft.service.ImageService;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepo imageRepo;

    @Override
    public void save(Image image) {
        imageRepo.save(image);
    }
}
