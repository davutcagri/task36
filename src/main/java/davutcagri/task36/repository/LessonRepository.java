package davutcagri.task36.repository;

import davutcagri.task36.model.Lesson;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends MongoRepository<Lesson, String> {
}
