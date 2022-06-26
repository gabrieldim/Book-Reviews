# Book Insight
---------------------------------------------------------------------------------------------------------------------------------------------------------
### ICT Project Management Project
---------------------------------------------------------------------------------------------------------------------------------------------------------
#### Faculty of Computer Science and Engineering at "Ss. Cyril and Methodius" University - Skopje
---------------------------------------------------------------------------------------------------------------------------------------------------------
<b>'Book Insight'</b> is a <b>web application</b> that provides a place where authors and reviewers are able to leave their insightful opinion on a vast literary collection. Our goal is to help authors improve their style of writing according to the critisism, help reviewers share their opinion and experience, as well as provide easier access to useful knowledge about the books and a safe community for readers.
<br/>
The following tools are used for implementation: <b>Spring Boot</b> as the back-end, <b>React JS</b> as the front-end and <b>Flask</b> as the framework used to expose the endpoints for integrating the processed review sentiment analysis data with the back-end.
<br/>

---------------------------------------------------------------------------------------------------------------------------------------------------------

##### The endpoints that the Spring back-end consists of are the following: <br/>
- Authors
  - GET   /api/author - getAllAuthors
  - GET   /api/author/{id} - getById (id - the id of the author)
  - POST  /api/author/register - registerAuthor (AuthorRequest object as request body)
  - POST  /api/author/login - loginAuthor (AuthorRequest object as request body)
  - GET   /api/author/page - getAllAuthorsPageable (Pageable object as request parameter wrapper)
  - GET   /api/author/book/{bookId} - getAllAuthorsByBook (bookId - the id of the book)
  - POST  /api/author/{id}/book/{bookId} - addBookToAuthor (id - the id of the author, bookId - the id of the book)
- Reviewers
  - GET   /api/reviewer - getAllReviewers
  - GET   /api/reviewer/{id} - getById (id - the id of the reviewer)
  - POST  /api/reviewer/register - registerReviewer (ReviewerRequest object as request body)
  - POST  /api/reviewer/login - loginReviewer (ReviewerRequest object as request body)
  - GET   /api/reviewer/page - getAllReviewersPageable (Pageable object as request parameter wrapper)
  - GET   /api/reviewer/review/{reviewId} - getAllReviewersByReview (bookId - the id of the book)
- Books
  - GET   /api/book - getAllBooks
  - GET   /api/book/{id} - getById (id - the id of the book)
  - POST  /api/book/ - createBook (BookRequest object as request body)
  - PUT   /api/book/{id} - updateBook (id - the id of the book, BookRequest object as request body)
  - GET   /api/book/page - getAllBooksPageable (Pageable object as request parameter wrapper)
  - GET   /api/book/review/{reviewId} - getAllBooksByReview (reviewId - the id of the review)
- Reviews
  - GET   /api/review - getAllReviews
  - GET   /api/review/{id} - getById (id - the id of the review)
  - POST  /api/review/ - createReview (ReviewRequest object as request body)
  - PUT   /api/review/{id} - updateReview (id - the id of the review, ReviewRequest object as request body)
  - GET   /api/review/page - getAllReviewsPageable (Pageable object as request parameter wrapper)
  - GET   /api/review/book/{bookId} - getAllReviewsByBook (bookId - the id of the book)
- Libraries
  - GET   /api/library - getAllLibraries
  - GET   /api/library/{id} - getById (id - the id of the library)
  - POST  /api/library/ - createLibrary (LibraryRequest object as request body)
  - GET   /api/library/page - getAllLibrariesPageable (Pageable object as request parameter wrapper)
  - GET   /api/library/book/{bookId} - getAllLibrariesByBook (bookId - the id of the book)
  - POST  /api/library/{id}/book/{bookId} - addBookToLibrary (id - the id of the library, bookId - the id of the book)

-----------------------

##### The endponits that the Flask sentiment analysis back-end engine consists of are the following: <br/>
  - POST  /v1/api/model - model_prediction (requests 'reviewDescription' as a string)
