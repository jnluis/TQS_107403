@book_search
Feature: Book Search

  Scenario: Search books by publication year
    Given I have the following books in the library
      | title                                | author       | published  |
      | The Adventures of Captain Underpants | Dav Pilkey   | 2005-09-01 |
      | Diary of a Wimpy Kid                 | Jeff Kinney  | 2007-04-01 |
      | The Brilliant World of Tom Gates     | Liz Pichon   | 2011-05-05  |
    When the customer searches for books published between 2001-01-01 and 2007-07-07
    Then 2 books should have been found
      | title                                | author       | published  |
      | The Adventures of Captain Underpants | Dav Pilkey   | 2005-09-01 |
      | Diary of a Wimpy Kid                 | Jeff Kinney  | 2007-04-01 |

  Scenario: Search books by Title
    Given I have the following books in the library
      | title                                | author       | published  |
      | The Adventures of Captain Underpants | Dav Pilkey   | 2005-09-01 |
      | Diary of a Wimpy Kid                 | Jeff Kinney  | 2007-04-01 |
      | The Brilliant World of Tom Gates     | Liz Pichon   | 2011-05-05  |
    When the customer searches for the book with the title 'Diary of a Wimpy Kid'
    Then 1 books should have been found
      | title                                | author       | published  |
      | Diary of a Wimpy Kid                 | Jeff Kinney  | 2007-04-01 |
      
   Scenario: Search books by Author
    Given I have the following books in the library
      | title                                | author       | published  |
      | The Adventures of Captain Underpants | Dav Pilkey   | 2005-09-01 |
      | Diary of a Wimpy Kid                 | Jeff Kinney  | 2007-04-01 |
      | The Brilliant World of Tom Gates     | Liz Pichon   | 2011-05-05  |
    When the customer searches for the book with the author 'Liz Pichon'
    Then 1 books should have been found
      | title                                | author       | published  |
      | The Brilliant World of Tom Gates     | Liz Pichon   | 2011-05-05  |
      
   Scenario: No books found
    Given I have the following books in the library
      | title                                | author       | published  |
      | The Adventures of Captain Underpants | Dav Pilkey   | 2005-09-01 |
      | Diary of a Wimpy Kid                 | Jeff Kinney  | 2007-04-01 |
      | The Brilliant World of Tom Gates     | Liz Pichon   | 2011-05-05  |
    When the customer searches for the book with the author 'Ruizinho de Penacova'
    Then 0 books should have been found
      | title                                | author       | published  |
