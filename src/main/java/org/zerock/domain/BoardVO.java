package org.zerock.domain;

import lombok.Data;

import java.util.Date;

@Data
public class BoardVO {

  private Long rn;
  private Long bno;
  private String title;
  private String content;
  private String writer;
//  @DateTimeFormat(pattern = "yyyyMMdd HH:mm:ss")
  private Date regdate;
//  @DateTimeFormat(pattern = "yyyyMMdd HH:mm:ss")
  private Date updateDate;
}
