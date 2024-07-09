Feature: Google API place ID creation

Scenario: To create place id with valid input details
Given User establish connection with server
And User provides all create place id request details
When User sends request to server
Then User should get the success response with status code 200
And User should be able to fetch the response