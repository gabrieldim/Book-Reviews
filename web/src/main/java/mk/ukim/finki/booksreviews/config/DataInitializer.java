//package mk.ukim.finki.booksreviews.config;
//
//import lombok.AllArgsConstructor;
//import mk.ukim.finki.booksreviews.model.entity.Author;
//import mk.ukim.finki.booksreviews.model.entity.Book;
//import mk.ukim.finki.booksreviews.model.entity.Library;
//import mk.ukim.finki.booksreviews.model.entity.Reviewer;
//import mk.ukim.finki.booksreviews.model.request.*;
//import mk.ukim.finki.booksreviews.service.*;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//
//import javax.annotation.PostConstruct;
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//@Component
//@AllArgsConstructor
//public class DataInitializer {
//
//    private final AuthorService authorService;
//    private final BookService bookService;
//    private final LibraryService libraryService;
//    private final ReviewerService reviewerService;
//    private final ReviewService reviewService;
//
//    @PostConstruct
//    public void initData() {
//        if (CollectionUtils.isEmpty(authorService.findAll()) &&
//                CollectionUtils.isEmpty(bookService.findAll()) &&
//                CollectionUtils.isEmpty(libraryService.findAll()) &&
//                CollectionUtils.isEmpty(reviewerService.findAll()) &&
//                CollectionUtils.isEmpty(reviewService.findAll())) {
//
//            Optional<Author> author1 = authorService.registerAuthor(new AuthorRequest("Nikola", "Stanojkovski",
//                    "stanojkovski@gmail.com", "nikolaS@123",
//                    31, LocalDateTime.of(1999, 6, 5, 8, 30),
//                    StringUtils.EMPTY, "Nicksta"));
//            Optional<Author> author2 = authorService.registerAuthor(new AuthorRequest("Gabriel", "Dimitrievski",
//                    "dimitrievski@gmail.com", "gabrielS@123",
//                    31, LocalDateTime.of(1998, 9, 1, 2, 20),
//                    "This is a short Gabriel biographry", "Gabsta"));
//
//            Optional<Library> library1 = libraryService.createLibrary(new LibraryRequest("Library 1", "Library Location 1"));
//            Optional<Library> library2 = libraryService.createLibrary(new LibraryRequest("Library 2", "Library Location 2"));
//            Optional<Library> library3 = libraryService.createLibrary(new LibraryRequest("Library 3", "Library Location 3"));
//
//            Optional<Book> book1 = bookService.createBook(new BookRequest("Book 1", "Book 1 Description",
//                    "Romance", "https://www.nytimes.com/2022/04/03/books/jenny-tinghui-zhang-four-treasures-of-the-sky.html",
//                    "Four treasures", false, author1.map(Author::getId).orElse(null), null));
//            Optional<Book> book2 = bookService.createBook(new BookRequest("Book 2", "Book 2 Description",
//                    "Romance", "https://www.nytimes.com/2022/04/03/books/jenny-tinghui-zhang-four-treasures-of-the-sky.html",
//                    "Five treasures", true, author1.map(Author::getId).orElse(null), library1.map(Library::getId).orElse(null)));
//            Optional<Book> book3 = bookService.createBook(new BookRequest("Book 3", "Book 3 Description",
//                    "Romance", "https://www.nytimes.com/2022/04/03/books/jenny-tinghui-zhang-four-treasures-of-the-sky.html",
//                    "Six treasures", true, author1.map(Author::getId).orElse(null), library1.map(Library::getId).orElse(null)));
//            Optional<Book> book4 = bookService.createBook(new BookRequest("Book 4", "Book 4 Description",
//                    "Thriller", "https://www.nytimes.com/2022/04/21/books/12-new-books-we-recommend-this-week.html",
//                    "Seven treasures", true, author2.map(Author::getId).orElse(null), library2.map(Library::getId).orElse(null)));
//            Optional<Book> book5 = bookService.createBook(new BookRequest("Book 5", "Book 5 Description",
//                    "Thriller", "https://www.nytimes.com/2022/04/21/books/12-new-books-we-recommend-this-week.html",
//                    "Eight treasures", true, author2.map(Author::getId).orElse(null), library3.map(Library::getId).orElse(null)));
//
//            Optional<Reviewer> reviewer1 = reviewerService.registerReviewer(new ReviewerRequest("Jovana", "Kocevska", "kocevska@gmail.com", "jovanaA@123",
//                    "I love my life", "Jovana bio description"));
//
//            Optional<Reviewer> reviewer2 = reviewerService.registerReviewer(new ReviewerRequest("Merxan", "Bajrami", "bajrami@gmail.com", "merxanN@123",
//                    "I love my life even more", "Merxan bio description"));
//
//            reviewService.createReview(new ReviewRequest("Review Title 1", "Review Description 1", 4L, book1.map(Book::getId).orElse(null), reviewer1.map(Reviewer::getId).orElse(null)));
//            reviewService.createReview(new ReviewRequest("Review Title 2", "Review Description 3", 5L, book1.map(Book::getId).orElse(null), reviewer2.map(Reviewer::getId).orElse(null)));
//            reviewService.createReview(new ReviewRequest("Review Title 3", "Review Description 3", 2L, book2.map(Book::getId).orElse(null), reviewer2.map(Reviewer::getId).orElse(null)));
//            reviewService.createReview(new ReviewRequest("Review Title Shouldn't insert 1", "Review Description 3", 7L, book2.map(Book::getId).orElse(null), reviewer2.map(Reviewer::getId).orElse(null)));
//            reviewService.createReview(new ReviewRequest("Review Title Shouldn't insert 2", "Review Description 3", 0L, book2.map(Book::getId).orElse(null), reviewer2.map(Reviewer::getId).orElse(null)));
//            reviewService.createReview(new ReviewRequest("Review Title 5", "Review Description 5", 4L, book3.map(Book::getId).orElse(null), reviewer2.map(Reviewer::getId).orElse(null)));
//            reviewService.createReview(new ReviewRequest("Review Title 6", "Review Description 6", 5L, book3.map(Book::getId).orElse(null), reviewer2.map(Reviewer::getId).orElse(null)));
//            reviewService.createReview(new ReviewRequest("Review Title 7", "Review Description 7", 4L, book2.map(Book::getId).orElse(null), reviewer1.map(Reviewer::getId).orElse(null)));
//            reviewService.createReview(new ReviewRequest("Review Title 8", "Review Description 8", 5L, book4.map(Book::getId).orElse(null), reviewer1.map(Reviewer::getId).orElse(null)));
//            reviewService.createReview(new ReviewRequest("Review Title 9", "Review Description 9", 2L, book4.map(Book::getId).orElse(null), reviewer1.map(Reviewer::getId).orElse(null)));
//            reviewService.createReview(new ReviewRequest("Review Title 10", "Review Description 10", 3L, book5.map(Book::getId).orElse(null), reviewer2.map(Reviewer::getId).orElse(null)));
//            reviewService.createReview(new ReviewRequest("Review Title 11", "Review Description 11", 4L, book5.map(Book::getId).orElse(null), reviewer2.map(Reviewer::getId).orElse(null)));
//        }
//    }
//}
