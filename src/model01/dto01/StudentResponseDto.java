package model01.dto01;

import lombok.*;
import java.time.LocalDate;

@Data // Generates getters, setters, toString, equals, and hashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDto {
    private Long id;
    private String fullname;
    private String gender;
    private LocalDate dateOfBirth;
}