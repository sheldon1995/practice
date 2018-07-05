package com.czy.service;

import java.sql.SQLException;
import java.util.List;

import com.czy.bean.AlreadyBuy;
import com.czy.bean.Employee;
import com.czy.bean.Goods;
import com.czy.dao.AlreadyBuyDao;
import com.czy.dao.EmployeeDao;
import com.czy.db.AlreadyBuyDaoImpl;
import com.czy.db.DBConnection;
import com.czy.db.EmployeeDaoImpl;

public class EmployeeService implements EmployeeDao {

	private DBConnection dbconn = null;

	private EmployeeDao dao = null;

	public EmployeeService() throws SQLException {
		this.dbconn = new DBConnection();
		this.dao =  new EmployeeDaoImpl(this.dbconn.getConnection());
	}


	@Override
	public List<Employee> getMetaServiceInstance() throws Exception {
		// TODO Auto-generated method stub

			return this.dao.getMetaServiceInstance();
	}

	


}
