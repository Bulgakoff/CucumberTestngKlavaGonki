Feature: Bot for website klavagonki
Scenario: : Positive test valiation login
  Given Initialize test with chrome
  When Open site "https://klavogonki.ru/go?type=normal"
  And Click on game link in landing page for start game
  And Wait for start game
  And Enter highlight word in loop
  And We note that there are more than 1500 characters per minute

  Then close driver



















