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


Feature: Product Order Process
 
  @DesktopTest
   Scenario Outline: Verify Checkout Page functionality in Desktop version
    Given User is on the Shopping Homepage
    When  User search for "<Product>" in the search box
    And   Selects the Tute Luft product from result list
    Then  User should be navigated to respective Product page
    When  User clicks on the in den Warenkorb button
    Then  Pop up should be displayed along with message
    When  User clicks on the Warenkorb link
    Then  User should be navigated to cart page
    When  User Clicks on zur Kasse gehen link
    Then  User should be navigated to checkout page
    When  User Enters "<Username>" and "<Password>" without selecting the radio button
    Then  User should be able to see error message
    When  User selects the radio button
    Then  User see the same "<Zwischensumme>","<Gesamtsumme>" details on bestellung page
    When  User clicks on  the Firma checkbox
    Then  User should be able to see the appearance of the textbox Firma
    When  User unchecks the Firma checkbox
    Then  User should be able to see the disappearance of the textbox Firma
    When  clicks on jetzt kaufen button
    Then  User should be able to see error "<message>"
    When  User enters "<Vorname>","<Nachname>","<PLZ>","<Ort>","<Strasse>","<Nummer>" and "<TelephoneNummer>" details
    And   clicks on jetzt kaufen button
    Then  User should be navigated to payment page

    Examples:
    |Product       |Username               |Password|Zwischensumme|Gesamtsumme|message                                |Vorname|Nachname|PLZ  |Ort   |Strasse     |Nummer|TelephoneNummer|
    |Eine Tüte Luft|roopinimn.mn1@gmail.com|Test@123|0,99 €       |0,99 €     |Bitte wählen Sie eine Rechnungsadresse.|Roopini|MN      |80796|Munich|Erich-Kästner-Str.|2    |015217671797|