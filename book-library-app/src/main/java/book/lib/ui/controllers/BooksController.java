package book.lib.ui.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import book.lib.services.impl.BookServiceImpl;
import book.lib.shared.dto.BookDto;
import book.lib.ui.model.Responce.BookResponceModel;

@RestController
@RequestMapping("/books")
public class BooksController {
	
	@Autowired
	BookServiceImpl bookServiceimpl;
	
	@GetMapping
	public List<BookResponceModel> getBooks() {
		List<BookResponceModel> listResponceModels = new ArrayList<>();
		List<BookDto> listDto = bookServiceimpl.getAllBooks();	
		
		Iterator<BookResponceModel> responsIterator = listResponceModels.iterator();
		Iterator<BookDto> dtoIterator = listDto.iterator();
		
		while(dtoIterator.hasNext()) {
			BeanUtils.copyProperties(dtoIterator, responsIterator);
		}
		
		
		return 	listResponceModels;
	}
	
	public ResponseEntity<BookResponceModel> createBook(@Valid @RequestBody BookDetailsRequestModel bookDetails){
		
		return null;
	}
	
	

}
