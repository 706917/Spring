package book.lib.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import book.lib.entity.BookEntity;


@Repository
public interface BooksRepository extends JpaRepository<BookEntity, Long>{
	
	BookEntity findByIsbn(String isbn);
	BookEntity findByBookId(String bookId);
	BookEntity findByBookName(String bookName);
	BookEntity findByAuthorName(String authorName);
	
	

}
