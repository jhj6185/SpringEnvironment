package org.conan.domain;

import lombok.ToString;

@ToString
public class Criteria {
	private int pageNum; //page번호
	private int amount; //한 페이지에 출력되는 데이터 수
	private String type;
	private String keyword;
	
	public Criteria() { this(1,10); } //그냥 해준건가...?
	
	public Criteria(int pageNum, int amount) { //넣어준 인자로 다시 setting인듯
		this.pageNum = pageNum; //mySQL에서 limit을 고려함
		this.amount = amount;
	}
	
	public void setPageNum(int pageNum) {
		if(pageNum<=0) {
			this.pageNum=1;
			return;
		}
		this.pageNum=pageNum;
	}
	public int getPageNum() {
		return this.pageNum;
	}
	public int getAmount() {
		return this.amount;
	}
	public void setAmount(int amount) {
		this.amount=amount;
	}
	public int getPageStart() {
		//limit 구문에서 시작 위치 지정 -> mySql에서 가져올 index번호의 시작점을 지정해주는것 
		return (this.pageNum-1)*this.amount;
	}
	
	public String[] getTypeArr() {
		return type == null ? new String[] {} : type.split("");
		// 검색 조건을 배열로 처리
	}

	public void setKeyword(String keyword) {
		// TODO Auto-generated method stub
		this.keyword=keyword;
	}

	public void setType(String type) {
		// TODO Auto-generated method stub
		this.type=type;
	}
	
	
}
