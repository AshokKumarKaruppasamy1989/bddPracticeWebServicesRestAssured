package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import resuable.CreatePlaceID;

public class LoginStepDefinition {

	CreatePlaceID create = new CreatePlaceID();

	@Given("User establish connection with server")
	public void user_establish_connection_with_server() {
		create.establishConnection();
	}

	@And("User provides all create place id request details")
	public void user_provides_all_create_place_id_request_details() {
		create.placeIDCreate();
	}

	@When("User sends request to server")
	public void user_sends_request_to_server() {
		create.createRequestPost();
	}

	@Then("User should get the success response with status code {int}")
	public void user_should_get_the_success_response_with_status_code(Integer int1) {
		create.postResponseSuccessValidation();
	}

	@And("User should be able to fetch the response")
	public void user_should_be_able_to_fetch_the_response() {
		create.createRequestGet();
		create.getResponseValidation();
	}

}
