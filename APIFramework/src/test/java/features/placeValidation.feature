Feature:Validating place APIs
@AddPlaceAPI @Regression
  Scenario Outline:Verify if place is being successfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "POST" HTTP request
    Then the response in API call will success with 200 status code
    And "status" in response body "OK"
    And verify place_Id created maps to "<name>" using "getPlaceAPI"

    Examples:

      | name  | language | address            |
      | Apana | English  | World class center |
      | Bafana |French    |Kul Ecolech         |

@updatePlaceAPI @Regression
  Scenario Outline:  Verify if place is being successfully updated using UpdatePlaceAPI
    Given Update Place Payload with "<address>"
    When user calls "updatePlaceAPI" with "PUT" HTTP request
    Then the response in API call will success with 200 status code
    And "msg" in response body "Address successfully updated"

    Examples:

              | address               |
              | World Updated address |

@deletePlaceAPI @Regression
    Scenario:Verify if Delete Place functionality is working
      Given DeletePlace Payload
      When user calls "deletePlaceAPI" with "POST" HTTP request
      Then the response in API call will success with 200 status code
      And "status" in response body "OK"


