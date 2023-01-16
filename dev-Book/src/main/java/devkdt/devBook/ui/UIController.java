package devkdt.devBook.ui;

import devkdt.devBook.book.application.BookService;
import devkdt.devBook.book.dto.BookResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class UIController {

    private final BookService bookService;

    public UIController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/newBook")
    String insert() {
        return "insert";
    }

    @GetMapping("/login")
    String loginForm() {
        return "loginForm";
    }

    @GetMapping("/newMember")
    String joinForm() {
        return "joinForm";
    }

    @GetMapping("/books/{id}")
    String detail(@PathVariable("id") long id) {
        //bookService.findBookById(id); //있나없나 확인하기!

        log.info("##### findCheck완료!");

        return "hello";
    }

}
