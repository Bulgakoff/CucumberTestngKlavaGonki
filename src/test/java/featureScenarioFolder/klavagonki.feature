Feature: Bot for website klavagonki
Scenario: : Positive test valiation login
  Given Initialize test with chrome
  When Open site "https://klavogonki.ru/go?type=normal"
  And Click on game link in landing page for start game
  And Wait for start game
  And Enter highlight word in loop
  And We note that there are more than 1500 characters per minute

  Then close driver



















#    Given Initialize test with chrome
#    And  Navigate to "https://qaclickacademy.com" site
#    And Click on Login link in home page to land on Secure sign in page
#    When User enters the <username> & <password> and logs in
#    Then Verify that user is successfully logged in
#    And close browser

