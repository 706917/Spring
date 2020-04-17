package book.lib.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import book.lib.entity.BookEntity;
import book.lib.repositories.BooksRepository;
import book.lib.services.BookService;
import book.lib.shared.dto.BookDto;


public class BookServiceImpl implements BookService{
	
	@Autowired
	BooksRepository booksRepository;
	
	public List<BookDto> getAllBooks(){
		List<BookDto> listBookDto= new ArrayList<BookDto>();
		List<BookEntity> listEntity = booksRepository.findAll();
		
		Iterator<BookEntity> entityIterator = listEntity.iterator();
		Iterator<BookDto> dtoIterator = listBookDto.iterator();
		
		while(entityIterator.hasNext()) {
			BeanUtils.copyProperties(entityIterator, dtoIterator);
		}		
		
		return listBookDto;
	}
	
	

	@Override
	public BookDto createBook(BookDto newBook) {

		if (booksRepository.findByIsbn(newBook.getIsbn()) != null) throw new RuntimeException("This book already exist in the library");
		
		BookEntity book = new BookEntity();		
		BeanUtils.copyProperties(newBook, book);
		
		BookDto returnValue = new BookDto();
		BeanUtils.copyProperties(book, returnValue);
		
		return returnValue;
		}

	
	@Override
	public BookDto getBookById(String bookId) {
		
		if(booksRepository.findById(bookId) == null) throw new RuntimeException("Book does not exist");
		
		BookEntity book = booksRepository.findById(bookId);
		BookDto returnValue = new BookDto();
		BeanUtils.copyProperties(book, returnValue);	
		
		return returnValue;
	}

	@Override
	public BookDto getBookByName(String bookName) {
		if(booksRepository.findByName(bookName) == null) throw new RuntimeException("Book does not exist");
		
		BookEntity book = booksRepository.findByName(bookName);
		BookDto returnValue = new BookDto();
		BeanUtils.copyProperties(book, returnValue);	
		
		return returnValue;
	}

	@Override
	public BookDto getBookByAuthor(String authorName) {
		if(booksRepository.findByAuthor(authorName) == null) throw new RuntimeException("Book does not exist");
		
		BookEntity book = booksRepository.findByAuthor(authorName);
		BookDto returnValue = new BookDto();
		BeanUtils.copyProperties(book, returnValue);	
		
		return returnValue;
	}

	@Override
	public BookDto getBookByIsbn(String isbn) {
		if(booksRepository.findByIsbn(isbn) == null) throw new RuntimeException("Book does not exist");
	
		BookEntity book = booksRepository.findByIsbn(isbn);
		BookDto returnValue = new BookDto();
		BeanUtils.copyProperties(book, returnValue);	
		
		return returnValue;
		}
}

