package peaksoft.service.impls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.entities.Post;
import peaksoft.repo.PostRepo;
import peaksoft.service.PostService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepo postRepo;

    @Override
    public void save(Post post) {
        postRepo.save(post);
    }

    @Override
    public List<Post> getAllPostsByUserId(Long userId) {
        return postRepo.getAllPostsByUserId(userId);
    }

    @Override
    public List<Post> getAllPostsOfSubsAndOwnByUserId(Long userId) {
        return postRepo.getAllPostsOfSubsAndOwnByUserId(userId);
    }

    @Override
    public Post getPostById(Long postId) {
        return postRepo.getPostById(postId);
    }

    @Override
    public void update(Post post) {
        postRepo.update(post);
    }

    @Override
    public void delete(Post post) {
        postRepo.delete(post);
    }
}
