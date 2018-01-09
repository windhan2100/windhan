package com.hanchao.web.controller;

import java.util.List;

/**
 * 该类用于分页
 * @author han
 *	2011-12-12
 */
public class Page<T> {
	//从数据库中查询的总的记录数
	private long totalCount;
	//每页显示的条数
	private int pageSize;
	//总的页数（这一属性可以舍去，而只要其get方法就可以了。）
	private long totalPages;
	//当前的页数
	private long currPage;
	//起始行数（这一属性也可以舍去，而只要其get方法就可以了）
	private long startIndex;
	//用一个集合来装每页要显示的条数
	private List<T> PageList;
	
	/**
	 * 重载的构造方法，主要是为了传值
	 * @param totalCount 总的记录数
	 * @param currPage 当前的页数
	 */
	public Page(long totalCount,long currPage) {
		setTotalCount(totalCount);
		setCurrPage(currPage);
	}
	/**
	 * 重载的构造方法，主要是为了传值
	 * @param totalCount 总的记录数
	 * @param currPage 当前的页数
	 * @param PageSize 每页要显示的条数
	 */
	public Page(long totalCount,long currPage,int pageSize) {
		setTotalCount(totalCount);
		setPageSize(pageSize);
		setCurrPage(currPage);
	}
	
	//*************下面是set,get方法*************

	/**
	 * 获取总的页数
	 */
	public long getTotalPages() {
		long num = getTotalCount() / getPageSize();
		if(getTotalCount() % getPageSize() != 0) {
			num++;
		}
		//你大爷的！！搞死大爷了！！注意|！！
		if(num == 0) {
			num = 1;
		}
		System.out.println(num);
		return num;
	}
	/**
	 * 获得起始行数
	 * @return 起始行数
	 */
	public long getStartIndex() {
		return (getCurrPage() -1) * getPageSize();
	}
	/**
	 * 是否有下一页
	 * false表示没有下一页；
	 * true表示有下一页。
	 */
	public boolean isNext() {
		if(getCurrPage() == getTotalPages()){
			return false;
		}
		return true;
	}
	/**
	 * 是否有上一页
	 * false表示没有上一页
	 * true表示有一页
	 */
	public boolean isPrev() {
		if(getCurrPage() == 1) {
			return false;
		}
		return true;
	}
	/**
	 * 是否是第一页
	 * true时表示是第一页
	 */
	public boolean isFirst() {
		if(getCurrPage() == 1) {
			return true;
		}
		return false;
	}
	/**
	 * 是否是最后一页
	 * false时表示是最后一页
	 */
	public boolean isLast() {
		if(getCurrPage() == getTotalPages()) {
			return true;
		}
		return false;
	}
	/**
	 * 获得当前的页数
	 * @return 当前的页数
	 */
	public long getCurrPage() {
		return currPage;
	}
	public void setCurrPage(long currPage) {
		//因为有些用户会从URL中直接改变页数，所以我们做如下判断
		if(currPage < 1) {
			this.currPage = 1;
		} else if(currPage > getTotalPages()) {
			this.currPage = getTotalPages();
		} else {
			this.currPage = currPage;
		}
	}
	
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getPageList() {
		return PageList;
	}
	public void setPageList(List<T> pageList) {
		PageList = pageList;
	}
}