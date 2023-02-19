package devkdt.devBook.ui;

import devkdt.devBook.book.application.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class UIController {

  private final BookService bookService;

  public UIController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/")
  public String home(
      @RequestParam(value = "pageNumber", required = true, defaultValue = "0") int pageNumber) {
    return "user/home";
  }

  @GetMapping("/login")
  String loginForm() {
    return "user/loginForm";
  }

  @GetMapping("/newMember")
  String joinForm() {
    return "user/joinForm";
  }

  @GetMapping("/books/{id}")
  String detail(@PathVariable("id") long id) {
    //bookService.findBookById(id); //있나없나 확인하기!

    log.info("##### findCheck완료!");

    return "user/detailBook";
  }

  //////admin

  @GetMapping("/admin")
  public String homeVAdmin(
      @RequestParam(value = "pageNumber", required = true, defaultValue = "0") int pageNumber) {
    return "admin/home";
  }

  @GetMapping("/admin/newBook")
  String insertVAdmin() {
    return "admin/insert";
  }

  @GetMapping("/admin/login")
  String loginFormVAdmin() {
    return "admin/loginForm";
  }

  @GetMapping("/admin/books/{id}")
  String detailVAdmin(@PathVariable("id") long id) {
    return "admin/detailBook";
  }

  @GetMapping("/admin/joinManage")
  String detailVAdmin() {
    return "admin/joinManagement";
  }
  
}
