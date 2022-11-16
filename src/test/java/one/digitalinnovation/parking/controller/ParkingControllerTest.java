package one.digitalinnovation.parking.controller;




import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import one.digitalinnovation.parking.controller.dto.ParkingCreateDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerTest {
	
	@LocalServerPort 
	private int randomPort;	

	@BeforeEach
	public void setUpTest() {
		RestAssured.port = randomPort;
	}
	
	@Test
	void whenFindAllThenCheckResult () {
		
		var createDTO = new ParkingCreateDTO();
		createDTO.setColor("AMARELO");
		createDTO.setLicense("BSR-2023");
		createDTO.setModel("FORD-MUSTANG");
		createDTO.setState("PA");
		
		RestAssured.given()
					.when()
					.get("/parking")
					.then()
					.statusCode(HttpStatus.OK.value());
					
	}	
	
	@Test	
	void whenCreateThenCheckIsCreated() {
		var createDTO = new ParkingCreateDTO();
		createDTO.setColor("AMARELO");
		createDTO.setLicense("BSR-2023");
		createDTO.setModel("FORD-MUSTANG");
		createDTO.setState("PA");
		
		
		RestAssured.given()
		.when()
		.contentType(MediaType.APPLICATION_JSON_VALUE)
		.body(createDTO)
		.post("/parking")
		.then()
		.statusCode(HttpStatus.CREATED.value())
		.body("license", Matchers.equalTo("BSR-2023"))
		.body("color", Matchers.equalTo("AMARELO"));	
		
	}
}
