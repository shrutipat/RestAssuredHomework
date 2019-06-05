package com.student.app.StudentInfo;
import com.student.app.StudentPojo;
import com.student.app.TestBase.TestBase;
import org.junit.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.given;

public class StudentPatchTest extends TestBase {

    @Test
    public void updateStudentEmailByPatch() {
        StudentPojo studentPojo = new StudentPojo();
        studentPojo.setEmail("Rajkumar.pakizaa@gmail.com");

        given()
                .header("Content-Type", "application/json")
                .when()
                .body(studentPojo)
                .patch("/1")
                .then().statusCode(200);
    }

    @Test
    public void verifyThatStudentEmailIsUpdated() {

        String p1 = "findAll{it.firstName=='";
        String p2 = "'}.get(3)";

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
