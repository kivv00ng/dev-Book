package devkdt.devBook.book.presentation;

import devkdt.devBook.book.dto.BookAddRequest;
import devkdt.devBook.book.dto.BookDetailResponse;
import devkdt.devBook.book.dto.BookOnePage;
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

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping(value = "/api/newBook", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<BookDetailResponse> createBook(@RequestPart BookAddRequest bookAddRequest, @RequestPart MultipartFile multipartFile) {
        BookDetailResponse bookResponse = bookService.addBook(bookAddRequest);

        try {
            multipartFile.transferTo(new File("/users/kimkiwoong/file/" + bookResponse.getId() + ".png"));
        } catch (IOException e) {
            throw new ImageIOException(bookResponse.getId());
        }
        //log.info("bookResponse:" + bookResponse);
        return ResponseEntity.created(URI.create("/api/books/" + bookResponse.getId())).body(bookResponse);
    }

    @GetMapping("/images/{fileName}")
    public Resource downloadImage(@PathVariable("fileName") String fileName) {
        try {
            return new UrlResource("file:/users/kimkiwoong/file/" + fileName);
        } catch (MalformedURLException e) {
            throw new ImageNotFoundException(fileName);
        }
    }

    @GetMapping("/page/{pageNumber}")
    public BookOnePage getPage(@PathVariable("pageNumber") int pageNumber) {
        return bookService.findOnePageBook(pageNumber);
    }

    // list가 들어간 dto 확인test용 매핑
//    @GetMapping("/test/listDto")
//    public BookOnePage onePage() {
//        List<PostForPage> posts = Arrays.asList(new PostForPage(1L, "titl1"), new PostForPage(2L, "titl2"));
//
//        BookOnePage postOnePage = new BookOnePage(2, 1, posts);
//        return postOnePage;
//    }

}
