package book.lib.ui.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import book.lib.services.BookService;
import book.lib.shared.dto.BookDto;
import book.lib.ui.model.Responce.BookResponceModel;
import book.lib.ui.model.request.BookRequestModel;

@RestController
@RequestMapping("/books")
public class BooksController {
	
	@Autowired
	BookService bookService;
	
	@GetMapping
	public List<BookResponceModel> getBooks() {
		List<BookResponceModel> listResponceModels = new ArrayList<>();
		List<BookDto> listDto = bookService.getAllBooks();	
		
		Iterator<BookResponceModel> responsIterator = listResponceModels.iterator();
		Iterator<BookDto> dtoIterator = listDto.iterator();
		
		while(dtoIterator.hasNext()) {
			BeanUtils.copyProperties(dtoIterator.next(), responsIterator.next());
		}		
		
		return 	listResponceModels;
	}
	
	
	
	@PostMapping()
	public BookResponceModel createBook(@Valid @RequestBody BookRequestModel bookDetails){
		
		BookResponceModel returnValue = new BookResponceModel();
		
		BookDto bookDto = new BookDto();
		
		BeanUtils.copyProperties(bookDetails, bookDto);
		
		BookDto createdBook = bookService.createBook(bookDto);
		
		
		BeanUtils.copyProperties(createdBook, returnValue);		
		
		return returnValue;
	}
	
	

}
