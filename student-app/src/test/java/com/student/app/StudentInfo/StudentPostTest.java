package com.student.app.StudentInfo;
import com.student.app.StudentPojo;
import com.student.app.TestBase.TestBase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class StudentPostTest extends TestBase {

    @Test
    public void createStudent() {
        StudentPojo studentPojo = new StudentPojo();
        List<String> courses = new ArrayList<>();
        courses.add("Singing");
        courses.add("Dancing");
        courses.add("Drama");

        studentPojo.setFirstName("Minakumari");
        studentPojo.setLastName("Pakiza");
        studentPojo.setEmail("minakumari.pakizaa@gmail.com");
        studentPojo.setProgramme("Bollywood Actress");
        studentPojo.setCourses(courses);

        given().header("Content-Type", "application/json")
                .when()
                .body(studentPojo)
                .post().then().statusCode(201);
    }

    @Test
    public void verifyThatStudentIsCreated() {
        String p1 = "findAll{it.firstName=='";
        String p2 = "'}.get(0)";

        HashMap<String, Object> value = given()
                .when()
                .get("/list")
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + "Minakumari" + p2);
        System.out.println("The value is: " + value);
    }


    @Test
    public void verifyThatStudentIsAddedWithNullLastName() {

        StudentPojo studentPojo = new StudentPojo();
        List<String> courses = new ArrayList<>();
        courses.add("Singing");
        courses.add("Dancing");
        courses.add("Drama");

        studentPojo.setFirstName("Minakumari");
        studentPojo.setLastName("");
        studentPojo.setEmail("mina@gmail.com");
        studentPojo.setProgramme("Bollywood Actress");
        studentPojo.setCourses(courses);

        String expectedText = "{\"msg\":\"Student added\"}";

        given().header("Content-Type", "application/json")
                .when()
                .body(studentPojo)
                .post()
                .then().statusCode(201)
                .and()
                .body(equalTo(expectedText));
    }

    @Test
    public void verifyStudentFirstNameIsNUll() {

        StudentPojo studentPojo = new StudentPojo();
        List<String> courses = new ArrayList<>();
        courses.add("Singing");
        courses.add("Dancing");
        courses.add("Drama");

        studentPojo.setFirstName("");
        studentPojo.setLastName("Pakiza");
        studentPojo.setEmail("minakumari.pakiza@gmail.comm");
        studentPojo.setProgramme("Bollywood Actress");
        studentPojo.setCourses(courses);

        String expectedText = "{\"error\":\"Please correct the following errors\",\"fieldErrors\":{\"firstName\":\"may not be empty\"}}";

        given().header("Content-Type", "application/json")
                .when()
                .body(studentPojo)
                .post()
                .then()
                .statusCode(500)
                .and()
                .assertThat()
                .body(equalTo(expectedText));
    }
}
