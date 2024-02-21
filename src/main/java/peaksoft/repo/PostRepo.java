package peaksoft.repo;

import peaksoft.entities.Post;

import java.util.List;

public interface PostRepo {
    void save(Post post);

    List<Post> getAllPostsByUserId(Long userId);
    Post getPostById(Long postId);

    List<Post> getAllPostsOfSubsAndOwnByUserId(Long userId);

    void update(Post post);

    void delete(Post post);
}
