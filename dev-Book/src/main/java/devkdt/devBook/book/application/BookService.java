package devkdt.devBook.book.application;

import devkdt.devBook.book.domain.Book;
import devkdt.devBook.book.domain.BookRepository;
import devkdt.devBook.book.dto.BookAddRequest;
import devkdt.devBook.book.dto.BookDetailResponse;
import devkdt.devBook.book.dto.BookForPage;
import devkdt.devBook.book.dto.BookOnePage;
import devkdt.devBook.book.entity.*;
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
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundByIdBookException(bookId));

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NotFoundByIdMemberException(memberId));

        applyEvaluation(book, evaluationRequest);
        Evaluation evaluation = new Evaluation();

        evaluation.addEvaluation(book, member);
        evaluationRepository.save(evaluation);
    }

    public BookDetailResponse detail(Long bookId) {
        Book foundBook = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundByIdBookException(bookId));
        return new BookDetailResponse(foundBook);
    }

    private void applyEvaluation(Book book, EvaluationRequest evaluationRequest) {
        evaluationRequest.addEvaluation(book);
    }

    @Transactional
    public BookDetailResponse addBook(BookAddRequest addbookRequest) {
        Book saveBook = bookRepository.save(addbookRequest.toBook());
        return new BookDetailResponse(saveBook);
    }

    public Book findBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new NotFoundByIdBookException(bookId));
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
                .map(book -> new BookForPage(book.getId(), book.getTitle()))
                .collect(Collectors.toList());
        int bookCount = bookForPages.size();
        int allPageCount = books.getTotalPages();

        return new BookOnePage(bookCount, allPageCount, bookForPages);
    }
}
