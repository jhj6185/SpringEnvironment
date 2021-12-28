package org.conan.domain;

import java.util.Date;

import lombok.Data;

@Data
public class BoardVO {//field만 선언해줬을 뿐인데 자동으로 getter setter 생성된듯;;
	//왜냐하면 @Data를 써주면 그렇게됨 개신기
	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regDate;
	private Date updateDate;
}
