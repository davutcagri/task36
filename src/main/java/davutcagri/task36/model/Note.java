package davutcagri.task36.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notes")
public class Note {

    @Id
    private String noteId;
    private Double midTermNote;
    private Double finalNote;
    private Double averageNote;
    private String studentId;
    private String lessonId;

}
