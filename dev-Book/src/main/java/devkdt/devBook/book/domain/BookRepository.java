package devkdt.devBook.book.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {

  @Query(value = "select b from Book b")
  Page<Book> findBookPage(Pageable pageable);

}
