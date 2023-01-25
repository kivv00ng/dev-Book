package devkdt.devBook.book.domain;

import devkdt.devBook.evaluation.domain.Evaluation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;

@Entity
public class Book {

  @Id
  @GeneratedValue
  @Column(name = "book_id")
  private Long bookId;

  @OneToMany(mappedBy = "book")
  private List<Evaluation> evaluations = new ArrayList<>();

  private String title;
  private String summary;
  private int price;

  private int devCourse;
  private int junior;
  private int middle;

  protected Book() {
  }

  public Book(String title, String summary, int price) {
    this.title = title;
    this.summary = summary;
    this.price = price;
    this.devCourse = 0;
    this.junior = 0;
    this.middle = 0;
  }

  public Book(String title, String summary, int price, int devCourse, int junior, int middle) {
    this.title = title;
    this.summary = summary;
    this.price = price;
    this.devCourse = devCourse;
    this.junior = junior;
    this.middle = middle;
  }

  public void changeBook(Book book) {
    this.title = book.getTitle();
    this.summary = book.getSummary();
    this.price = book.getPrice();
    this.devCourse = book.getDevCourse();
    this.junior = book.getJunior();
    this.middle = book.getMiddle();
  }

  public Long getBookId() {
    return bookId;
  }

  public List<Evaluation> getEvaluations() {
    return evaluations;
  }

  public String getTitle() {
    return title;
  }

  public String getSummary() {
    return summary;
  }

  public int getPrice() {
    return price;
  }

  public int getDevCourse() {
    return devCourse;
  }

  public int getJunior() {
    return junior;
  }

  public int getMiddle() {
    return middle;
  }


  public void addDevCourse() {
    this.devCourse++;
  }

  public void addJunior() {
    this.junior++;
  }

  public void addMiddle() {
    this.middle++;
  }
}
