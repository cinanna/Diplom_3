package org.example.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.create.UserDataToCreate;
import org.example.login.requests.UserDataToLogin;
import org.example.login.response.LoginDataInResponse;
import static io.restassured.RestAssured.given;

public class Api {
    private String email;
    private String name;
    private String password;

    public Api(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
    public void setURL(){
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
    }

    public void createUser() {
        UserDataToCreate userDataToCreate = new UserDataToCreate(email, password, name);
                   given()
                        .header("Content-type", "application/json")
                        .body(userDataToCreate)
                        .when()
                        .post("api/auth/register");
    }
    public Response loginUser(UserDataToLogin data) {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .body(data)
                        .when()
                        .post("api/auth/login");
        return response;
    }

    public void deleteUser(String token) {
        given()
                .auth().oauth2(token.replaceFirst("Bearer ",""))
                .when()
                .delete("api/auth/user");
    }

    public void removeUser() {
        UserDataToLogin userDataToLogin = new UserDataToLogin(email, password);
        Response response = loginUser(userDataToLogin);
        LoginDataInResponse userData = response.as(LoginDataInResponse.class);
        deleteUser(userData.getAccessToken());
    }
}
