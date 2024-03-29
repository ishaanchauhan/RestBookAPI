package com.bcs.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.bcs.model.Book;
import com.bcs.service.BookService;
import com.bcsutilities.RestErrorType;
import com.bcsutilities.RestSuccesType;

@RestController
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	BookService bookService; 
	
	// Method to Create a Book

		@RequestMapping(value = "/book/", method = RequestMethod.POST)
		public ResponseEntity<?> createBook(@Valid @RequestBody Book book,UriComponentsBuilder ucBuilder) {
			logger.info("Creating Book : {}", book);

			if (bookService.isBookExist((book))) {
				logger.error("Unable to create. A Book with name {} already exist",
						book.getBookName());
				return new ResponseEntity<>(new RestErrorType(
						"Unable to create. A Book with name " + book.getBookName()
								+ " already exist."), HttpStatus.CONFLICT);
			}
			bookService.saveBook(book);

			return new ResponseEntity<>(new RestSuccesType("Book has been created with name " + book.getBookName()
							+ " ."), HttpStatus.CREATED);
		}

		// Method to find all books
	
	@RequestMapping(value = "/book/", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> listAllBook() {
		List<Book> books = bookService.findAllBooks();
		if (books.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}

	// Method to Retrieve Single Book

	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getBook(@Valid @PathVariable("id") long id) {
		logger.info("Fetching Book with id {}", id);
		Book book = bookService.findBookById(id);
		if (book == null) {
			logger.error("Book with id {} not found.", id);
			return new ResponseEntity<>(new RestErrorType("Book with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}

	
	// Method to Update a Book 

	@RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateBook(@Valid @PathVariable("id") long id,
			@RequestBody Book book) {
		logger.info("Updating Book with id {}", id);

		Book currentBook = bookService.findBookById(id);

		if (currentBook == null) {
			logger.error("Unable to update. Book with id {} not found.", id);
			return new ResponseEntity<>(new RestErrorType(
					"Unable to upate. Book with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentBook.setBookName(book.getBookName());
		currentBook.setAuthor(book.getAuthor());
		currentBook.setPrice(book.getPrice());

		bookService.updateBook(currentBook);
		return new ResponseEntity<Book>(currentBook, HttpStatus.OK);
	}

	// Method to Delete a Book

	@RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteBook(@Valid @PathVariable("id") long id) {
		logger.info("Fetching & Deleting Book with id {}", id);

		Book book = bookService.findBookById(id);
		if (book == null) {
			logger.error("Unable to delete. Book with id {} not found.", id);
			return new ResponseEntity<>(new RestErrorType(
					"Unable to delete. Book with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		bookService.deleteBookById(id);
		return new ResponseEntity<>(new RestSuccesType("Book id "+id+" Deleted."), HttpStatus.OK);
	}

	// method to delete all books

	@RequestMapping(value = "/book/", method = RequestMethod.DELETE)
	public ResponseEntity<Book> deleteAllBook() {
		logger.info("Deleting All Books");

		bookService.deleteAllBook();
		return new ResponseEntity(new RestSuccesType("All Book Deleted."), HttpStatus.OK);
	}
	
	// Methid to filter data on the basis of different arguments
	
	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> getBook(@Valid @RequestParam(required=false,name="limit") String limit,@RequestParam(required=false,name="author") String author, @RequestParam(required=false,name="price") String price, @RequestParam(required=false,name="bookName")  String bookName) {
		List<Book> books = bookService.findBooks(limit,author,price);
		if (books.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}
}