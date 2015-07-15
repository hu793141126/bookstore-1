package cn.mengmei.domain;

import java.util.List;

public class Page {
	
	private List list;  //保存该页的书
	private int totalRecords;  //总纪录数
	private int pageSize = 3;  //每页纪录条数
	private int pageCount;  //总页数
	private int pageNum;   //当前页码
	private int beginIndex;   //当页纪录开始数
	private int pageStartNum;  //当页显示的开始页码，假如每次显示5个页码，当前页码前面-2
	private int pageEndNum;    //当页显示的结束页码，假如每次显示5个页码，当前页码后面+2
	private String url;    //处理分页的servlet
	
	public Page(int totalRecords, int pageNum){
		this.totalRecords = totalRecords;
		this.pageNum = pageNum;
		

		if(totalRecords%this.pageSize==0){
			this.pageCount = this.totalRecords/this.pageSize;
		}else{
			this.pageCount = this.totalRecords/this.pageSize + 1;
		}
		

		this.beginIndex = (this.pageNum-1) * this.pageSize;
		

		if(this.pageCount <= 5){
			this.pageStartNum = 1;
			this.pageEndNum = this.pageCount;
		}else{
			this.pageStartNum = this.pageNum - 2;
			this.pageEndNum = this.pageNum + 2;
			if(this.pageStartNum < 1){
				this.pageStartNum = 1;
				this.pageEndNum = 5;
			}
			if(this.pageEndNum > this.pageCount){
				this.pageEndNum = this.pageCount;
				this.pageStartNum = this.pageCount - 4;
			}
		}
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public int getPageStartNum() {
		return pageStartNum;
	}

	public void setPageStartNum(int pageStartNum) {
		this.pageStartNum = pageStartNum;
	}

	public int getPageEndNum() {
		return pageEndNum;
	}

	public void setPageEndNum(int pageEndNum) {
		this.pageEndNum = pageEndNum;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
