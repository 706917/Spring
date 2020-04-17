package book.lib.services;

import book.lib.shared.dto.BookDto;


public interface BookService {
	
	BookDto createBook(BookDto newBook);
	
	BookDto getBookById(String bookId);
	
	BookDto getBookByName(String bookName);
	
	BookDto getBookByAuthor(String authorName);
	
	BookDto getBookByIsbn(String isbn);
	

}
