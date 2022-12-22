package com.rc.graphqlwithspringboot.controller;

import com.rc.graphqlwithspringboot.entity.Book;
import com.rc.graphqlwithspringboot.entity.Page;
import com.rc.graphqlwithspringboot.model.BookRequest;
import com.rc.graphqlwithspringboot.model.PageRequest;
import com.rc.graphqlwithspringboot.repository.BookRepository;
import com.rc.graphqlwithspringboot.repository.PageRepository;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class MutationController {

  private final BookRepository bookRepository;
  private final PageRepository pageRepository;

  @MutationMapping
  public Book createBook(@Argument("input") BookRequest bookRequest) {
    Book book = Book.builder()
        .title(bookRequest.getTitle())
        .author(bookRequest.getAuthor())
        .isbn(bookRequest.getIsbn())
        .build();
    return bookRepository.save(book);
  }

  @MutationMapping
  public Page createPage(@Argument("input") PageRequest pageRequest) {
    Optional<Book> book = bookRepository.findById(Long.valueOf(pageRequest.getBookId()));
    if (book.isPresent()) {
      Page page = Page.builder()
          .number(pageRequest.getNumber())
          .content(pageRequest.getContent())
          .chapter(pageRequest.getChapter())
          .book(book.get())
          .build();
      return pageRepository.save(page);
    }
    return null;
  }
}
