#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template

Feature: Adding Product to Basket

 @DesktopTest
  Scenario Outline: Verify Warenkorb Page functionality in Desktop version
    Given User is on check24 website
    When  User clicks on Shopping Link from Homepage
    Then  User should be navigated to shopping home page
    When  User search for "<Product>" in the search box
    And   Selects the Tute Luft product from result list
    Then  User should be navigated to respective Product page
    When  User clicks on the in den Warenkorb button
    Then  Pop up should be displayed along with message
    When  User clicks on the Warenkorb link
    Then  User should be navigated to cart page
    And   User sees the "<Price>" and "<totalprice>" details
    When  User changes the "<quantity>" 
    Then  User should be able to see the updated "<Zwischensumme>","<Gesamtsumme>" and "<Punkte>" details
    When  User clicks on the right slider arrow button
    Then  User should verify that the left arrow button is enabled
    When  User clicks on the link Löschen
    Then  User should be able to see the "<message>" 
    
    Examples:
    |Product       |Price  |Menge|totalprice|quantity|Zwischensumme|Gesamtsumme|Punkte|message|
    |Eine Tüte Luft|0,99 € |1    |0,99 €       |2       |1,98 €       |1,98 €     |2     |Ihr Warenkorb ist zurzeit leer|
    
     