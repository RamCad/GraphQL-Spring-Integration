package com.rc.graphqlwithspringboot.repository;

import com.rc.graphqlwithspringboot.entity.Page;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<Page, Long> {

  List<Page> findAllByBookId(Long bookId);
}
