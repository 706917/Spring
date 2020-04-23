package book.lib.shared.dto;

import java.io.Serializable;

public class BookDto implements Serializable{
	
	private static final long serialVersionUID = 619636425097132555L;
	
	//****************Data Fields************
	
	private Long id; // database id - primary key	
	private String bookId; // public id meant to return to the view	
	private String bookName;		
	private String authorName;		
	private String isbn;
	
	
	
	//*******Methods*********
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String book_id) {
		this.bookId = book_id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String book_name) {
		this.bookName = book_name;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String author_name) {
		this.authorName = author_name;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	
	
	

}
