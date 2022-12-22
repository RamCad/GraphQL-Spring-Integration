package com.rc.graphqlwithspringboot.controller;

import com.rc.graphqlwithspringboot.entity.Book;
import com.rc.graphqlwithspringboot.entity.Page;
import com.rc.graphqlwithspringboot.model.BookFilter;
import com.rc.graphqlwithspringboot.model.BookIdFilter;
import com.rc.graphqlwithspringboot.repository.BookRepository;
import com.rc.graphqlwithspringboot.repository.PageRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class QueryController {

  private final BookRepository bookRepository;
  private final PageRepository pageRepository;

  @QueryMapping
  public List<Book> allBooks() {
    return bookRepository.findAll();
  }

  @QueryMapping
  public Book bookByTitle(@Argument("filter") BookFilter filter) {
    return bookRepository.findByTitle(filter.getTitle());
  }

  @QueryMapping
  public Book bookById(@Argument("filter") BookIdFilter filter) {
    Optional<Book> book = bookRepository.findById(Long.valueOf(filter.getId()));
    return book.isPresent() ? book.get() : Book.builder().build();
  }

  @SchemaMapping(typeName = "Book", field = "page")
  public List<Page> getPage(Book book) {
    List<Page> pages = pageRepository.findAllByBookId(book.id());
    return pages;
  }
}
