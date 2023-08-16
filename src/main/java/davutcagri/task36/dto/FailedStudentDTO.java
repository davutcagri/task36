package davutcagri.task36.dto;

import lombok.Data;

import java.util.List;

@Data
public class FailedStudentDTO {

    private List<StudentDTO> studentDTOS;

    public FailedStudentDTO(List<StudentDTO> studentDTOS) {
        this.studentDTOS = studentDTOS;
    }

}
