/**
 * 
 */
package com.czy.bean;
import java.util.List;

/**
 * @author Sheldon
 *
 */
public class PageBean<T> {
	private int pc;// current page
	private int ps;// records of current page
	private int all;// all records
	private String url;
	private List<T> beanList;// gather together all data aboved
	/**
	 * acquir max the numbers of pages
	 * tp is all the numbers of pages
	 * @return if there is a remainder, pages = tp+=1
	 */
	
	public int getTp() {
		int tp = all/ps;
		return all%ps == 0 ? tp : tp+1;
	}
	/**
	 * @return the pc
	 */
	public int getPc() {
		return pc;
	}
	/**
	 * @param pc2 the pc to set
	 */
	public void setPc(int pc) {
		this.pc = pc;
	}
	/**
	 * @return the ps
	 */
	public int getPs() {
		return ps;
	}
	/**
	 * @param resultNumberPerPage the ps to set
	 */
	public void setPs(int resultNumberPerPage) {
		this.ps = resultNumberPerPage;
	}
	/**
	 * @return the all
	 */
	public int getAll() {
		return all;
	}
	/**
	 * @param all the all to set
	 */
	public void setAll(int all) {
		this.all = all;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the beanList
	 */
	public List<T> getBeanList() {
		return beanList;
	}
	/**
	 * @param beanList the beanList to set
	 */
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	
}
