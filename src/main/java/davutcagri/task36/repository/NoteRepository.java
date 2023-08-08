package davutcagri.task36.repository;

import davutcagri.task36.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {

    Note findNoteById(String id);

}
