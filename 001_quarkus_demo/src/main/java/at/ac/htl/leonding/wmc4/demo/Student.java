package at.ac.htl.leonding.wmc4.demo;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class Student {
    private Long id;
    private String fullName;
    private String email;
    private LocalDateTime birthDate;
}
