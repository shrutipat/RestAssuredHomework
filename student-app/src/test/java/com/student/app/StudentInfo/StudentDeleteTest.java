package com.student.app.StudentInfo;
import com.student.app.TestBase.TestBase;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StudentDeleteTest extends TestBase {

    @Test
    public void deleteStudentByID(){
        given()
                .when()
                .delete("/11")
                .then()
                .log().all()
                .statusCode(204);
    }

    @Test
    public void verifyDeletion(){
        given()
                .when()
                .get("/11")
                .then()
                .log().all()
                .statusCode(404);
    }
}
