package com.czy.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.czy.bean.Employee;
import com.czy.bean.Goods;
import com.czy.bean.User;
import com.czy.dao.EmployeeDao;
import com.czy.dao.UserDao;
import com.mysql.jdbc.Statement;

public class EmployeeDaoImpl implements EmployeeDao {

	private Connection conn = null;

	private PreparedStatement pstmt = null;

	public EmployeeDaoImpl(Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Employee> getMetaServiceInstance() throws Exception {
		List<Employee> dataList = new ArrayList<Employee>();
		Employee metadada;
		String sql ="select table_name,COLUMN_NAME, DATA_TYPE \r\n" + 
				"from information_schema.COLUMNS\r\n" + 
				"where table_name=ANY\r\n" + 
				"(SELECT table_name\r\n" + 
				"       FROM information_schema.tables\r\n" + 
				"       WHERE table_schema = 'shopping'\r\n" + 
				"       ORDER BY table_name\r\n" + 
				")order BY table_name;\r\n";
		Statement st = null;
		ResultSet rs = null;
		
		st = (Statement) conn.createStatement();
		rs = st.executeQuery(sql);
		while(rs.next())
			{
			 metadada = new Employee();
			 metadada.setCalumname(rs.getString("COLUMN_NAME"));
			 metadada.setTablename(rs.getString("table_name"));
			 metadada.setType(rs.getString("DATA_TYPE"));
			 dataList.add(metadada);
			}
		return dataList;
	
	
	}
	
}	


	
