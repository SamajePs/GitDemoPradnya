package stepDefinitions;

import Pojo.Location;
import Pojo.addMap;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResource;
import resources.Utils;
import resources.testDataBuild;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

import static org.junit.Assert.assertEquals;

public class StepDefination extends Utils {
    ResponseSpecification responseSpec;
    RequestSpecification res;
   Response responseString;
   static String place_id;
    JsonPath js;

    testDataBuild t=new testDataBuild();
    @Given("Add Place Payload")
    @Given("Add Place Payload with {string} {string} {string}")
    public void add_place_payload_with(String name, String language, String address) throws IOException{

        responseSpec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
         res= given().spec(requestSpecification())
                .body(t.addPlacePayload(name,language,address));
    }
    @When("user calls {string} with {string} HTTP request")
    public void user_calls_with_http_request(String resource, String method) {
        APIResource resourceAPI=APIResource.valueOf(resource);
       // resourceAPI.getResource();
        responseSpec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
     //   responseString= (Response)res.when().post( resourceAPI.getResource())
        //        .then().spec(responseSpec).extract().response();
        if(method.equalsIgnoreCase("POST"))
            responseString =res.when().post(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("GET"))
            responseString =res.when().get(resourceAPI.getResource());
        else if(method.equalsIgnoreCase("PUT"))
            responseString =res.when().put(resourceAPI.getResource());
        else if(method.equalsIgnoreCase("DELETE"))
            responseString =res.when().put(resourceAPI.getResource());

    }


    @Then("the response in API call will success with {int} status code")
    public void the_response_in_api_call_will_success_with_status_code(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
       assertEquals(responseString.getStatusCode(),200);

    }
    @Then("{string} in response body {string}")
    public void in_response_body(String keyValue, String Expectedvalue) {
        assertEquals(getJsonPath(responseString,keyValue),Expectedvalue);
    }

    @Then("verify place_Id created maps to {string} using {string}")
    public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
      place_id=getJsonPath(responseString,"place_id");
        res=given().spec(requestSpecification()).queryParam("place_id",place_id);
      user_calls_with_http_request(resource,"GET");
    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {
        res=given().spec(req).body(t.deletePlacePayload(place_id));
       // res =given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
    }

    @Given("Update Place Payload with {string}")
    public void update_place_payload_with(String address) throws IOException {
    //    responseSpec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
       res=given().spec(req).body(t.updatePlacePayload(place_id,address)).queryParam("place_id",place_id);

    }


}
