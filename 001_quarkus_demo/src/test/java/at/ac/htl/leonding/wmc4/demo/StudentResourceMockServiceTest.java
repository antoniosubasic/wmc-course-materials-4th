package at.ac.htl.leonding.wmc4.demo;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@QuarkusTest
class StudentResourceMockServiceTest {
    @InjectMock
    DatabaseService ds;

    @BeforeEach
    void reset() {
        Mockito.when(ds.getStudents()).thenReturn(getDefaultList());

        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Mockito.when(
                ds.getStudent(any(Long.class)))
                .thenReturn(
                        Student.builder()
                                .id(1L)
                                .fullName("Gerald Unterrainer")
                                .email("gerald" + "@unterrainer.info")
                                .birthDate(LocalDateTime.parse("1975-05-02 00:00", f))
                                .build());
    }

    private List<Student> getDefaultList() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return List.of(Student.builder().id(1L).fullName("Gerald Unterrainer").email("gerald" +
                "@unterrainer.info")
                .birthDate(LocalDateTime.parse("1975-05-02 00:00", f)).build(),
                Student.builder().id(2L).fullName("Kasperl Unterrainer").email("kasperl@unterrainer.info")
                        .birthDate(LocalDateTime.parse("1995-10-23 00:00", f)).build());
    }

    @Test
    void getAll_returnsTwoSeeded() {
        given()
                .when()
                .get("/students")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("size()", is(2))
                .body("[0].id", notNullValue());
    }

    @Test
    void getById_returnsFirstSeeded() {
        given()
                .when()
                .get("/students/1")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("id", is(1))
                .body("fullName", is("Gerald Unterrainer"))
                .body("email", is("gerald@unterrainer.info"));
    }
}
