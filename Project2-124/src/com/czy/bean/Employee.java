package com.czy.bean;

public class Employee {




	private String tablename;

	private String columnname;

	private String type;

	
	public Employee() {

	}
	public Employee(String tablename, String column, String type) {
		this.tablename = tablename;
		this.columnname = column;
		this.type = type;
	}

	/**
	 * @return the tablename
	 */
	public String getTablename() {
		return tablename;
	}


	/**
	 * @param tablename the tablename to set
	 */
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}


	/**
	 * @return the calumname
	 */
	public String getCalumname() {
		return columnname;
	}


	/**
	 * @param calumname the calumname to set
	 */
	public void setCalumname(String calumname) {
		this.columnname = calumname;
	}


	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}


	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	
	
	
	

}
