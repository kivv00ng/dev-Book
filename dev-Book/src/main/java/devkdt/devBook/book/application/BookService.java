package devkdt.devBook.book.application;

import devkdt.devBook.book.domain.Book;
import devkdt.devBook.book.domain.BookRepository;
import devkdt.devBook.book.dto.BookAddRequest;
import devkdt.devBook.book.dto.BookForPage;
import devkdt.devBook.book.dto.BookOnePage;
import devkdt.devBook.book.dto.BookResponse;
import devkdt.devBook.book.dto.BookUpdateRequest;
import devkdt.devBook.book.exception.NotFoundByIdBookException;
import devkdt.devBook.evaluation.domain.Evaluation;
import devkdt.devBook.evaluation.domain.EvaluationRepository;
import devkdt.devBook.evaluation.dto.EvaluationRequest;
import devkdt.devBook.evaluation.exception.DuplicationException;
import devkdt.devBook.member.domain.Member;
import devkdt.devBook.member.domain.MemberRepository;
import devkdt.devBook.member.exception.NotFoundByIdMemberException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class BookService {

  private final BookRepository bookRepository;
  private final EvaluationRepository evaluationRepository;
  private final MemberRepository memberRepository;

  @Transactional
  public void evaluate(Long bookId, Long memberId, EvaluationRequest evaluationRequest) {
    Book foundBook = bookRepository.selectForUpdate(bookId)
        .orElseThrow(() -> new NotFoundByIdBookException(bookId));

    Member foundMember = memberRepository.findById(memberId)
        .orElseThrow(() -> new NotFoundByIdMemberException(memberId));

    if (evaluationRepository.existsEvaluationByBookAndMember(foundBook, foundMember)) {
      throw new DuplicationException(memberId, bookId);
    }

    applyEvaluation(foundBook, evaluationRequest);
    Evaluation evaluation = new Evaluation();

    evaluation.addEvaluation(foundBook, foundMember);
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
    Book foundBook = bookRepository.findById(bookId)
        .orElseThrow(() -> new NotFoundByIdBookException(bookId));
    return new BookResponse(foundBook);
  }

  public List<Book> findAllBook() {
    return bookRepository.findAll();
  }

  public BookOnePage findOnePageBook(int pageNumber) {
    PageRequest pageRequest = PageRequest.of(pageNumber, 10);

    Page<Book> books = bookRepository.findBookPage(pageRequest);

    List<BookForPage> bookForPages = books.stream()
        .map(book -> new BookForPage(book.getId(), book.getTitle()))
        .collect(Collectors.toList());
    int bookCount = bookForPages.size();
    int allPageCount = books.getTotalPages();

    return new BookOnePage(bookCount, allPageCount, bookForPages);
  }

  @Transactional
  public BookResponse updateBook(BookUpdateRequest bookUpdateRequest) {
    Book foundBook = bookRepository.findById(bookUpdateRequest.getBookId())
        .orElseThrow(() -> new NotFoundByIdBookException(bookUpdateRequest.getBookId()));

    foundBook.changeBook(bookUpdateRequest.toBook());

    return new BookResponse(foundBook);
  }

  @Transactional
  public void delete(long bookId) {
    bookRepository.deleteById(bookId);
  }
}
