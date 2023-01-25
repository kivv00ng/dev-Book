package devkdt.devBook.book.presentation;

import devkdt.devBook.book.domain.Book;
import devkdt.devBook.book.dto.*;
import devkdt.devBook.book.exception.ImageIOException;
import devkdt.devBook.book.exception.ImageNotFoundException;
import devkdt.devBook.book.application.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;

  @PostMapping(value = "/api/newBook", consumes = {MediaType.APPLICATION_JSON_VALUE,
      MediaType.MULTIPART_FORM_DATA_VALUE})
  public ResponseEntity<BookResponse> createBook(@RequestPart BookAddRequest bookAddRequest,
      @RequestPart("bookImage") MultipartFile bookImage) {
    BookResponse bookResponse = bookService.addBook(bookAddRequest);

    try {
      bookImage.transferTo(
          new File("/users/kimkiwoong/file/" + bookResponse.getBookId() + ".png"));
    } catch (IOException e) {
      throw new ImageIOException(bookResponse.getBookId());
    }
    //log.info("bookResponse:" + bookResponse);
    return ResponseEntity.created(URI.create("/api/books/" + bookResponse.getBookId()))
        .body(bookResponse);
  }

  @PutMapping("/api/books")
  public ResponseEntity<BookResponse> updateBook(@RequestBody BookUpdateRequest bookUpdateRequest) {
    BookResponse bookResponse = bookService.updateBook(bookUpdateRequest);
    return ResponseEntity.ok(bookResponse);
  }

  @GetMapping("/api/books/{id}")
  public ResponseEntity<BookResponse> findBook(@PathVariable("id") long bookId) {
    BookResponse bookResponse = bookService.findBookById(bookId);
    return ResponseEntity.ok(bookResponse);
  }

  @GetMapping("/images/{fileName}")
  public Resource downloadImage(@PathVariable("fileName") String fileName) {
    try {
      return new UrlResource("file:/users/kimkiwoong/file/" + fileName);
    } catch (MalformedURLException e) {
      throw new ImageNotFoundException(fileName);
    }
  }

  //  @GetMapping("/page/{pageNumber}")
//  public BookOnePage getPage(@PathVariable("pageNumber") int pageNumber) {
//    return bookService.findOnePageBook(pageNumber);
//  }
  @GetMapping("/page")
  public BookOnePage getPage(
      @RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber) {
    return bookService.findOnePageBook(pageNumber);
  }

  @GetMapping("/api/books/all")
  public ResponseEntity<List<Book>> findAll() {
    List<Book> books = bookService.findAllBook();
    return ResponseEntity.ok(books);
  }

  @DeleteMapping("/api/books/{id}")
  public ResponseEntity<Void> deleteBook(@PathVariable("id") long id) {
    bookService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
