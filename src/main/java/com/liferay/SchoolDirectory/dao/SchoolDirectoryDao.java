package com.liferay.SchoolDirectory.dao;

import com.liferay.SchoolDirectory.objects.EducationalServiceDistrict;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SchoolDirectoryDao {
	private Connection conn = null;
	private String username = "root";
	private String password = "root";
	private String connectionString = "jdbc:mysql://localhost:3306/schooldirectory";
	
	/**
	 * Use this to start the connection with the local database
	 */
	private void startConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver") ;
			conn = DriverManager.getConnection(connectionString, username, password);
		} catch (Exception e) {
			System.out.println("ERROR: " + this.getClass().getName() + " - Error starting connection with DB");
			e.printStackTrace();
		}
	}
	
	/**
	 * Get all the ESDs in the database.
	 * @return JSONObject of the table
	 */
	public ArrayList<EducationalServiceDistrict> getAllEducationalServiceDistricts() {
		ArrayList<EducationalServiceDistrict> esds = new ArrayList<EducationalServiceDistrict>();
		startConnection();
		if(conn != null) {
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery( "SELECT * FROM educationservicedistricts");
				
				//looping through the result set
				while(rs.next()) {
					EducationalServiceDistrict esd = new EducationalServiceDistrict(rs);
					esds.add(esd);
				}
				
		        rs.close();
		        stmt.close();
		        conn.close();
		    } catch(Exception e) {
		    	System.out.println("ERROR: " + this.getClass().getName() + " - Issue getting all EducationServiceDistricts");
		    	e.printStackTrace();
		    }
		} else {
			System.out.println("ERROR: " + this.getClass().getName() + " - connection is null, cannot get eds information out of the resultset");
		}
		
		return esds;
	}
	
	public EducationalServiceDistrict getEducationalServiceDistrictById(String Id) {
		EducationalServiceDistrict esd = new EducationalServiceDistrict();
		
		startConnection();
		if(conn != null) {
			try {
				String sql = "SELECT * FROM educationservicedistricts WHERE idEducationServiceDistricts = ?";
				  PreparedStatement ps = conn.prepareStatement(sql);
				  ps.setString(1, Id);
				  ResultSet rs = ps.executeQuery();
				  
					//looping through the result set
					while(rs.next()) {
						esd = new EducationalServiceDistrict(rs);
					}
				  
				  ps.close();
				  conn.close();
			  } catch(Exception e) {
				  System.out.println("ERROR: " + this.getClass().getName() + " - Issue with getting single for esd by the Id of " + Id);
				  e.printStackTrace();
			  }
		} else {
			System.out.println("ERROR: " + this.getClass().getName() + " - connection is null, cannot get eds information out of the resultset");
		}
		
		return esd;
	}
	
	/**
	 * Insert a EducationalServieDistricts Record into the table
	 */
	public void insertEducationalServiceDistrict(EducationalServiceDistrict eds) {
		startConnection();
		if(conn!= null) {
			  String sql = " insert into educationservicedistricts (code, name, addressline1, addressline2, state, zip, email, phone, administratorname)"
					    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			  try {
				  PreparedStatement ps = conn.prepareStatement(sql);
				  ps.setString(1, eds.getCode());
				  ps.setString(2, eds.getName());
				  ps.setString(3, eds.getAddressLine1());
				  ps.setString(4, eds.getAddressLine2());
				  ps.setString(5, eds.getState());
				  ps.setString(6, eds.getZipCode());
				  ps.setString(7, eds.getEmail());
				  ps.setString(8, eds.getPhone());
				  ps.setString(9, eds.getAdministratorName());
				  
				  ps.execute();
				  
				  ps.close();
				  conn.close();
			  } catch(Exception e) {
				  System.out.println("ERROR: " + this.getClass().getName() + " - Issue with calling insert for esd");
				  e.printStackTrace();
			  }
		} else {
			System.out.println("ERROR: " + this.getClass().getName() + " - connection is null, cannot insert eds information into table");
		}
	}
	
	public void updateEducationalServiceDistrict(EducationalServiceDistrict esd) {
		
		startConnection();
		if(conn!= null) {
				//code, name, addressline1, addressline2, state, zip, email, phone, administratorname
			  String sql = "UPDATE educationservicedistricts SET code=?, name=?, addressline1=?, addressline2=?, state=?, zip=?, email=?, phone=?, administratorname=? WHERE idEducationServiceDistricts=?";
			  try {
				  PreparedStatement ps = conn.prepareStatement(sql);
				  ps.setString(1, esd.getCode());
				  ps.setString(2, esd.getName());
				  ps.setString(3, esd.getAddressLine1());
				  ps.setString(4, esd.getAddressLine2());
				  ps.setString(5, esd.getState());
				  ps.setString(6, esd.getZipCode());
				  ps.setString(7, esd.getEmail());
				  ps.setString(8, esd.getPhone());
				  ps.setString(9, esd.getAdministratorName());
				  
				  ps.setInt(10, Integer.parseInt(esd.getPrimaryKey()));
				  
				  ps.executeUpdate();
				  
				  ps.close();
				  conn.close();
			  } catch(Exception e) {
				  System.out.println("ERROR: " + this.getClass().getName() + " - Issue with calling updating for esd");
				  e.printStackTrace();
			  }
		} else {
			System.out.println("ERROR: " + this.getClass().getName() + " - connection is null, cannot insert eds information into table");
		}
	}	
}
