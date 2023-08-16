package davutcagri.task36.dto;

import lombok.Data;

import java.util.List;

@Data
public class SucceedStudentDTO {

    private List<StudentDTO> studentDTOS;

    public SucceedStudentDTO(List<StudentDTO> studentDTOS) {
        this.studentDTOS = studentDTOS;
    }

}
