package com.musula.drones.integrationtest;

import com.musula.drones.DronesApplication;
import com.musula.drones.common.enums.State;
import com.musula.drones.domain.drone.dto.DroneDto;
import com.musula.drones.domain.drone.enums.DroneModel;
import com.musula.drones.domain.medication.dto.MedicationDto;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = DronesApplication.class)
@TestPropertySource(locations = "classpath:application-integration.properties")
public class DroneRestControllerIntegrationTest {

  @LocalServerPort private Integer port;

  @BeforeEach
  void setup() {
    RestAssured.baseURI = String.format("http://localhost:%s", port);
  }

  /**
   * Given a request, when I make a get request to the /api/v1/drones endpoint, then I should get a
   * 200 response
   */
  @Test
  @Order(1)
  void findAll() {
    given().when().get("/api/v1/drones").then().log().all().assertThat().statusCode(200);
  }

  /** We are testing the saveDrone() function of the DroneController class */
  @Test
  @Order(2)
  void saveDrone() {
    DroneDto droneDto = new DroneDto();
    droneDto.setSerialNumber("001-Drone-01");
    droneDto.setModel(DroneModel.LIGHT_WEIGHT);
    given()
        .contentType(ContentType.JSON)
        .body(droneDto)
        .when()
        .post("/api/v1/drones")
        .then()
        .log()
        .all()
        .assertThat()
        .statusCode(HttpStatus.CREATED.value());
  }

  /**
   * Given a serial number, when I make a GET request to the /api/v1/drones/serial-numbers endpoint,
   * then I should get a 200 response
   */
  @Test
  @Order(3)
  void findDroneBySerialNumber() {
    given()
        .when()
        .queryParam("serialNumber", "T18SN1234567890")
        .get("/api/v1/drones/serial-numbers")
        .then()
        .log()
        .all()
        .assertThat()
        .statusCode(HttpStatus.OK.value());
  }

  /** We are testing the saveMedication() function of the DroneController class */
  @Test
  @Order(4)
  void saveMedication() {
    MedicationDto medicationDto = new MedicationDto();
    medicationDto.setDroneSerialNumber("001-Drone-01");
    medicationDto.setCode("001");
    medicationDto.setWeight(500);
    medicationDto.setName("Vaccines");
    medicationDto.setImageURL(
        "https://d2jx2rerrg6sh3.cloudfront.net/images/news/ImageForNews_734493_16717867507843586.jpg");

    given()
        .contentType(ContentType.JSON)
        .body(medicationDto)
        .when()
        .post("/api/v1/drones/medications")
        .then()
        .log()
        .all()
        .assertThat()
        .statusCode(HttpStatus.CREATED.value());
  }

  /**
   * Given a state, when a GET request is made to the endpoint /api/v1/drones/states, then the
   * response should be a 200 OK
   */
  @Test
  @Order(5)
  void findDroneByState() {
    given()
        .when()
        .queryParam("state", State.LOADED)
        .get("/api/v1/drones/states")
        .then()
        .log()
        .all()
        .assertThat()
        .statusCode(HttpStatus.OK.value());
  }

  /**
   * This function tests the endpoint `/api/v1/drones/medications` with the query parameters
   * `medicationState` and `serialNumber` and asserts that the response status code is `200`
   */
  @Test
  @Order(6)
  void findMedicationByDroneSerialNumberAndMedicationState() {
    given()
        .when()
        .queryParam("medicationState", State.DELIVERED)
        .queryParam("serialNumber", "TLW00281234567")
        .get("/api/v1/drones/medications")
        .then()
        .log()
        .all()
        .assertThat()
        .statusCode(HttpStatus.OK.value());
  }
}
