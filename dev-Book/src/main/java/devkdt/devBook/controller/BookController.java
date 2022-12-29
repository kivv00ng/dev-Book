package devkdt.devBook.controller;

import devkdt.devBook.dto.BookAddRequest;
import devkdt.devBook.dto.BookDetailResponse;
import devkdt.devBook.dto.PostForPage;
import devkdt.devBook.dto.PostOnePage;
import devkdt.devBook.service.BookService;
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
import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping(value = "/api/newBook", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<BookDetailResponse> createBook(@RequestPart BookAddRequest bookAddRequest, @RequestPart MultipartFile multipartFile) {
        BookDetailResponse bookResponse = bookService.addPost(bookAddRequest);

        try {
            multipartFile.transferTo(new File("/users/kimkiwoong/file/" + bookResponse.getId() + ".png"));
        } catch (IOException e) {
            throw new RuntimeException("이미지 저장시 커스텀에러 만들자..");
        }

        log.info("bookResponse:" + bookResponse);
        return ResponseEntity.created(URI.create("/api/books/" + bookResponse.getId())).body(bookResponse);
    }

    @GetMapping("/images/{fileName}")
    public Resource downloadImage(@PathVariable("fileName") String fileName) {
        try {
            return new UrlResource("file:/users/kimkiwoong/file/" + fileName);
        } catch (MalformedURLException e) {
            throw new RuntimeException("기웅아 런타임 에러 만들어라 -이미지 파일 찾는 에러");
        }
    }

    @GetMapping("/test/listDto")
    public PostOnePage onePage() {
        List<PostForPage> posts = Arrays.asList(new PostForPage(1L, "titl1"), new PostForPage(2L, "titl2"));


        PostOnePage postOnePage = new PostOnePage(2, 1, posts);
        return postOnePage;
    }

    @GetMapping("/page/{pageNumber}")
    public PostOnePage getPage(@PathVariable("pageNumber") int pageNumber) {
        return bookService.findOnePagePost(pageNumber);
    }
    
}
