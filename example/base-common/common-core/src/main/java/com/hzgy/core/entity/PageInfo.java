package com.hzgy.core.entity;

import java.io.Serializable;
import java.util.List;

public class PageInfo<T>  implements Serializable{


	private static final long serialVersionUID = 1810927012194946826L;

	/**
	 * 每页记录数
	 */
	private Integer size;
	
	/**
	 * 当前页
	 */
	private Integer page;
	
	/**
	 * 总页数
	 */
	private Integer totalPage;
	
	/**
	 * 总记录数
	 */
	private Long records;
	
	/**
	 * 列表数据
	 */
	private List<T> list;

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Long getRecords() {
		return records;
	}

	public void setRecords(Long records) {
		this.records = records;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
