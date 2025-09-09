package com.example.board.post;

import com.example.board.post.dto.PostRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository repo;
    public PostService(PostRepository repo) { this.repo = repo; }

    public List<Post> list() { return repo.findAll(); }
    public Post get(Long id) { return repo.findById(id).orElseThrow(() -> new IllegalArgumentException("not found")); }

    @Transactional
    public Long create(PostRequest req) {
        Post p = new Post();
        p.setTitle(req.getTitle());
        p.setAuthor(req.getAuthor());
        p.setContent(req.getContent());
        return repo.save(p).getId();
    }

    @Transactional
    public void update(Long id, PostRequest req) {
        Post p = get(id);
        p.setTitle(req.getTitle());
        p.setAuthor(req.getAuthor());
        p.setContent(req.getContent());
    }

    @Transactional
    public void delete(Long id) { repo.deleteById(id); }
}
