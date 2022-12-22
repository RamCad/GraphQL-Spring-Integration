package com.rc.graphqlwithspringboot.repository;

import com.rc.graphqlwithspringboot.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  Book findByTitle(String title);
}
