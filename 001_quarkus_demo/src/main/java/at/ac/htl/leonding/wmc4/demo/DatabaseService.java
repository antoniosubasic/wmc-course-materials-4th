package at.ac.htl.leonding.wmc4.demo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@ApplicationScoped
public class DatabaseService {
    @Setter
    private Long nextId = 1L;
    private final Map<Long, Student> students = new HashMap<>();

    public void reset() {
        students.clear();
        nextId = 1L;
    }

    public Long inc() {
        return nextId++;
    }

    public Student getStudent(long id) {
        return students.get(id);
    }

    public Collection<Student> getStudents() {
        return students.values();
    }

    public void addStudent(Student s) {
        students.put(s.getId(), s);
    }

    public void delStudent(long id) {
        students.remove(id);
    }

    public boolean containsStudent(long id) {
        return students.containsKey(id);
    }

    void datasourceReset() {
        reset();

        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Student s = Student.builder().id(inc()).fullName("Gerald Unterrainer").email("gerald@unterrainer.info")
                .birthDate(LocalDateTime.parse("1975-05-02 00:00", f)).build();
        students().put(s.getId(), s);
        s = Student.builder().id(inc()).fullName("Kasperl Unterrainer").email("kasperl@unterrainer.info")
                .birthDate(LocalDateTime.parse("1995-10-23 00:00", f)).build();
        students().put(s.getId(), s);
    }
}
