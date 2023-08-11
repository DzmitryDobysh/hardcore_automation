Feature: Hardcore Test

  Background: Open google cloud calculator page
    Given I open google cloud main page
    When I perform a search for "Google Cloud Platform Pricing Calculator" page
    Then Click on cloud calculator page link

  Scenario Outline: Check that the total cost in the email is the same as calculated manually
    When I enter all info with following data:
      | Instances   | OS   | Instance Class   | Series   | Machine Type   | GPUs   | GPU Type   | SSD   | Location   | Usage   |
      | <Instances> | <OS> | <Instance Class> | <Series> | <Machine Type> | <GPUs> | <GPU Type> | <SSD> | <Location> | <Usage> |
    Then All entered info is present and added to estimation

    When I created temp email and send estimation to it
    Then I check the total cost in the email against the calculated total cost

    Examples:
      | Instances | OS   | Instance Class | Series | Machine Type                           | GPUs | GPU Type          | SSD | Location     | Usage |
      | 4         | free | regular        | n1     | CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8 | 1    | NVIDIA_TESLA_V100 | 2   | europe-west3 | 1     |