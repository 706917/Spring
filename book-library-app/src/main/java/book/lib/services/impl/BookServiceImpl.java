package book.lib.services.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import book.lib.entity.BookEntity;
import book.lib.repositories.BooksRepository;
import book.lib.services.BookService;
import book.lib.shared.Utils;
import book.lib.shared.dto.BookDto;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	BooksRepository booksRepository;
	
	@Autowired
	Utils utils;
	
	public List<BookDto> getAllBooks(){
		List<BookDto> listBookDto= new ArrayList<BookDto>();
		List<BookEntity> listEntity = booksRepository.findAll();
		
		Iterator<BookEntity> entityIterator = listEntity.iterator();
		Iterator<BookDto> dtoIterator = listBookDto.iterator();
		
		while(entityIterator.hasNext()) {
			BeanUtils.copyProperties(entityIterator.next(), dtoIterator.next());
		}		
		
		return listBookDto;
	}
	
	

	@Override
	public BookDto createBook(BookDto book) {

		if (booksRepository.findByIsbn(book.getIsbn()) != null) throw new RuntimeException("This book already exist in the library");
		
		BookEntity bookEntity = new BookEntity();		
		BeanUtils.copyProperties(book, bookEntity);
		
		// Since we are not getting userId from the view in this process - we will generate it ourselves and assign its value to the entity attribute.
		String publicBookId = utils.generateBookId();
		bookEntity.setBookId(publicBookId);
		//bookEntity.setAuthorName(book.getAuthorName()); - for checking purposes - check the methods names
		
		BookEntity createdBook = booksRepository.save(bookEntity);
		
		BookDto returnValue = new BookDto();		
		BeanUtils.copyProperties(createdBook, returnValue);
		
		return returnValue;
		}

	
	@Override
	public BookDto getBookById(String bookId) {
		
		if(booksRepository.findByBookId(bookId) == null) throw new RuntimeException("Book does not exist");
		
		BookEntity book = booksRepository.findByBookId(bookId);
		BookDto returnValue = new BookDto();
		BeanUtils.copyProperties(book, returnValue);	
		
		return returnValue;
	}

	@Override
	public BookDto getBookByName(String bookName) {
		if(booksRepository.findByBookName(bookName) == null) throw new RuntimeException("Book does not exist");
		
		BookEntity book = booksRepository.findByBookName(bookName);
		BookDto returnValue = new BookDto();
		BeanUtils.copyProperties(book, returnValue);	
		
		return returnValue;
	}

	@Override
	public BookDto getBookByAuthor(String authorName) {
		if(booksRepository.findByAuthorName(authorName) == null) throw new RuntimeException("Book does not exist");
		
		BookEntity book = booksRepository.findByAuthorName(authorName);
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

