package davutcagri.task36.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "lessons")
public class Lesson {

    @Id
    private String id;
    private String name;
    private String teacher;
    private List<String> studentIds = new ArrayList<>();
    private List<String> noteIds = new ArrayList<>();

}
