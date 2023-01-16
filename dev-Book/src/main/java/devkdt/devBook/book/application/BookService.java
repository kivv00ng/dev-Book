package devkdt.devBook.book.application;

import devkdt.devBook.book.domain.Book;
import devkdt.devBook.book.domain.BookRepository;
import devkdt.devBook.book.dto.*;
import devkdt.devBook.evaluation.domain.Evaluation;
import devkdt.devBook.evaluation.domain.EvaluationRepository;
import devkdt.devBook.evaluation.dto.EvaluationRequest;
import devkdt.devBook.member.exception.NotFoundByIdMemberException;
import devkdt.devBook.book.exception.NotFoundByIdBookException;
import devkdt.devBook.member.domain.Member;
import devkdt.devBook.member.domain.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;
  private final EvaluationRepository evaluationRepository;
  private final MemberRepository memberRepository;

//    @Transactional
//    public void evaluateV0(Long bookId, EvaluationRequest evaluationRequest) {
//        book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("customException만들기.."));
//        addEvaluation(book, evaluationRequest);
//        Evaluation evaluation = new Evaluation();
//        evaluation.addEvaluation(book);
//        evaluationRepository.save(evaluation);
//    }

  @Transactional
  public void evaluate(Long bookId, Long memberId, EvaluationRequest evaluationRequest) {
    Book book = bookRepository.findById(bookId)
        .orElseThrow(() -> new NotFoundByIdBookException(bookId));

    Member member = memberRepository.findById(memberId)
        .orElseThrow(() -> new NotFoundByIdMemberException(memberId));

    applyEvaluation(book, evaluationRequest);
    Evaluation evaluation = new Evaluation();

    evaluation.addEvaluation(book, member);
    evaluationRepository.save(evaluation);
  }

  public BookResponse detail(Long bookId) {
    Book foundBook = bookRepository.findById(bookId)
        .orElseThrow(() -> new NotFoundByIdBookException(bookId));
    return new BookResponse(foundBook);
  }

  private void applyEvaluation(Book book, EvaluationRequest evaluationRequest) {
    evaluationRequest.addEvaluation(book);
  }

  @Transactional
  public BookResponse addBook(BookAddRequest bookAddRequest) {
    Book saveBook = bookRepository.save(bookAddRequest.toBook());
    return new BookResponse(saveBook);
  }

  public BookResponse findBookById(Long bookId) {
    Book book = bookRepository.findById(bookId)
        .orElseThrow(() -> new NotFoundByIdBookException(bookId));
    return new BookResponse(book);
  }

  public void deleteBookById(Long bookId) {
    bookRepository.deleteById(bookId);
  }

  public List<Book> findAllBook() {
    return bookRepository.findAll();
  }

  public BookOnePage findOnePageBook(int pageNumber) {
    PageRequest pageRequest = PageRequest.of(pageNumber, 10);

    Page<Book> books = bookRepository.findBookPage(pageRequest);

    List<BookForPage> bookForPages = books.stream()
        .map(book -> new BookForPage(book.getBookId(), book.getTitle()))
        .collect(Collectors.toList());
    int bookCount = bookForPages.size();
    int allPageCount = books.getTotalPages();

    return new BookOnePage(bookCount, allPageCount, bookForPages);
  }
}
