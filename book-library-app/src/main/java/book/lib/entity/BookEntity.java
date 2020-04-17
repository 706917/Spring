package book.lib.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="books")
public class BookEntity implements Serializable{
	
	private static final long serialVersionUID = -7638403789216901516L; // static final serialVersionUID field
	
	//**********Data Fields*********	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; // database id - primary key
	
	@Column(nullable=false, length=50)
	private String bookId; // public id meant to return to the view
	
	@Column(nullable=false, length=100)
	private String bookName;
	
	@Column(nullable=false, length=50)
	private String authorName;
	
	@Column(nullable=false, length=20)
	private String isbn;
	
	//************Constructors***********
	
	
	
	//**************Methods*************
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getBook_id() {
		return bookId;
	}

	public void setBook_id(String book_id) {
		this.bookId = book_id;
	}

	public String getBook_name() {
		return bookName;
	}

	public void setBook_name(String book_name) {
		this.bookName = book_name;
	}

	public String getAuthor_name() {
		return authorName;
	}

	public void setAuthor_name(String author_name) {
		this.authorName = author_name;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	
	
	
	
	
	
	

}
