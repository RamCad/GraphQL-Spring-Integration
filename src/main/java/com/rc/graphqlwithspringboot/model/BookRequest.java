package com.rc.graphqlwithspringboot.model;

import lombok.Data;

@Data
public class BookRequest {
  private String title;
  private String author;
  private String isbn;
}
