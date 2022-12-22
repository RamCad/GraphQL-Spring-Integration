package com.rc.graphqlwithspringboot.model;

import lombok.Data;

@Data
public class PageRequest {
  private Integer number;
  private String content;
  private String chapter;
  private Integer bookId;
}
