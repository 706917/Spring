package book.lib.services;

import java.util.List;

import book.lib.shared.dto.BookDto;


public interface BookService {
	
	List <BookDto> getAllBooks();
	
	BookDto createBook(BookDto newBook);
	
	BookDto getBookById(String bookId);
	
	BookDto getBookByName(String bookName);
	
	BookDto getBookByAuthor(String authorName);
	
	BookDto getBookByIsbn(String isbn);
	

}
