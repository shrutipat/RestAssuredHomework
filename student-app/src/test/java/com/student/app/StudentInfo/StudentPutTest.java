package com.student.app.StudentInfo;
import com.student.app.StudentPojo;
import com.student.app.TestBase.TestBase;
import io.restassured.http.ContentType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StudentPutTest extends TestBase {

    @Test
    public void updateStudentById() {
        StudentPojo studentPojo = new StudentPojo();
        List<String> courses = new ArrayList<>();
        courses.add("Singing");
        courses.add("Dancing");
        courses.add("Drama");

        studentPojo.setFirstName("Raj");
        studentPojo.setLastName("Kumar");
        studentPojo.setEmail("Rajkumar.pakiza@gmail.com");
        studentPojo.setProgramme("Bollywood Actress");
        studentPojo.setCourses(courses);

        given()
                .contentType(ContentType.JSON)
                .when()
                .body(studentPojo)
                .put("/" + 102)
                .then().log().all().statusCode(200);
    }

    @Test
    public void verifyThatStudentIsUpdated() {

        String p1 = "findAll{it.firstName=='";
        String p2 = "'}.get(0)";

        HashMap<String, Object> value = given()
                .when()
                .get("/list")
                .then()
                .statusCode(200)
                .extract()
                .path(p1 + "Raj" + p2);

        System.out.println("The value is: " + value);
    }
}

