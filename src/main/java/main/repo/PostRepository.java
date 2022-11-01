package main.repo;

import main.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
