package com.student.app.StudentInfo;

import com.student.app.TestBase.TestBase;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StudentGetTest extends TestBase {

   /* @BeforeClass
    public static void inIt(){
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "/student";
    }*/

    @Test
    public void getAllStudentInformation_ResponseExample() {
        /**
         * given()
         * set cookies,add auth,adding parameters,setting header info
         * .when()
         * GET,POST,PUT,DELETE..etc
         * .then()
         *  Validate status code,extract response, extract headers,cookies,extract the response body
         *
         */
        Response response = given()
                .when()
                .get("/list");
        System.out.println(response.body().prettyPrint());
        response.then().statusCode(200);
        //Validate the status code
        /*given()
                .when()
                .get("/list")
                .then()
                .statusCode(200);*/
    }

    @Test
    public void getStudentInformation_ValidateResponseExample() {
        ValidatableResponse response1 = given()
                .when()
                .get("/list")
                .then().log().all().statusCode(200);
    }

    @Test
    public void getStudentInfo() {
        Response response = given()
                .when()
                .get("/1");
        System.out.println(response.body().prettyPrint());

        //validate that requested student info is fetched
        given()
                .when()
                .get("/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void getStudentsFromFA() {

      /*  //method 1
        Response response = given()
                .when()
                .get("/list?programme=Financial Analysis");
        System.out.println(response.prettyPeek());
*/
        //method 2
        Response response2 = given()
                .param("programme", "Financial Analysis")
                .when()
                .get("/list");
        System.out.println(response2.prettyPeek());
    }

    @Test
    public void getStudentsFromFA_WithLimitTwo() {
        Response response = given()
                .when()
                .get("/list?programme=Financial Analysis&limit=2");
        System.out.println(response.prettyPeek());

        Response response2 = given()
                .param("programme", "Financial Analysis")
                .param("limit", 2)
                .when()
                .get("/list");
        System.out.println(response2.prettyPeek());
    }
}