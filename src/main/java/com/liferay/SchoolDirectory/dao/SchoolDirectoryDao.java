package com.liferay.SchoolDirectory.dao;

import com.liferay.SchoolDirectory.objects.District;
import com.liferay.SchoolDirectory.objects.EducationalServiceDistrict;
import com.liferay.SchoolDirectory.objects.School;

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
	
	//////////////////////////////////
	//ESD Database calls
	/////////////////////////////////
	
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
				ResultSet rs = stmt.executeQuery( "SELECT * FROM EducationalServicedistricts");
				
				//looping through the result set
				while(rs.next()) {
					EducationalServiceDistrict esd = new EducationalServiceDistrict(rs);
					esds.add(esd);
				}
				
		        rs.close();
		        stmt.close();
		        conn.close();
		    } catch(Exception e) {
		    	System.out.println("ERROR: " + this.getClass().getName() + " - Issue getting all EducationalServiceDistricts");
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
				String sql = "SELECT * FROM EducationalServicedistricts WHERE idEducationalServiceDistricts = ?";
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
			  String sql = " insert into EducationalServicedistricts (code, name, addressline1, addressline2, state, zipCode, email, phone, administratorname)"
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
			  String sql = "UPDATE EducationalServicedistricts SET code=?, name=?, addressline1=?, addressline2=?, state=?, zipCode=?, email=?, phone=?, administratorname=? WHERE idEducationalServiceDistricts=?";
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
	
	public int deleteEducationalServiceDistrict(String primaryKey) {
		startConnection();
		if(conn!=null) {
			String sql = "DELETE FROM EducationalServiceDistricts WHERE ideducationalservicedistricts=?";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, Integer.parseInt(primaryKey));
				return ps.executeUpdate();
			}catch(Exception e) {
				System.out.println("ERROR: " + this.getClass().getName() + " - Issue with deleting esd with primary key of " + primaryKey);
				e.printStackTrace();
			}
		} else {
			System.out.println("ERROR: " + this.getClass().getName() + " - connection is null, cannot delete eds with primary key of " + primaryKey);
		}
		return 0;
		
	}
	
	////////////////////////////////
	//District Database calls
	///////////////////////////////
	/**
	 * Get all the Districts in the database.
	 * @return JSONObject of the table
	 */
	public ArrayList<District> getAllDistricts() {
		ArrayList<District> districts = new ArrayList<District>();
		startConnection();
		if(conn != null) {
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery( "SELECT * FROM districts");
				
				//looping through the result set
				while(rs.next()) {
					District district = new District(rs);
					districts.add(district);
				}
				
		        rs.close();
		        stmt.close();
		        conn.close();
		    } catch(Exception e) {
		    	System.out.println("ERROR: " + this.getClass().getName() + " - Issue getting all Districts");
		    	e.printStackTrace();
		    }
		} else {
			System.out.println("ERROR: " + this.getClass().getName() + " - connection is null, cannot get district information out of the resultset");
		}
		
		return districts;
	}
	
	/**
	 * Get single district based off id/primarkey
	 * @param Id
	 * @return
	 */
	public District getDistrictById(String Id) {
		District district = new District();
		
		startConnection();
		if(conn != null) {
			try {
				String sql = "SELECT * FROM districts WHERE iddistricts = ?";
				  PreparedStatement ps = conn.prepareStatement(sql);
				  ps.setString(1, Id);
				  ResultSet rs = ps.executeQuery();
				  
					//looping through the result set
					while(rs.next()) {
						district = new District(rs);
					}
				  
				  ps.close();
				  conn.close();
			  } catch(Exception e) {
				  System.out.println("ERROR: " + this.getClass().getName() + " - Issue with getting single for district by the Id of " + Id);
				  e.printStackTrace();
			  }
		} else {
			System.out.println("ERROR: " + this.getClass().getName() + " - connection is null, cannot get district information out of the resultset");
		}
		
		return district;
	}
	
	/**
	 * Insert a District Record into the table
	 */
	public void insertDistrict(District district) {
		startConnection();
		if(conn!= null) {
			  String sql = " insert into districts (esdcode, esdname, code, name, addressline1, addressline2, state, zipcode, email, phone, administratorname)"
					    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			  try {
				  PreparedStatement ps = conn.prepareStatement(sql);
				  ps.setString(1, district.getEsdCode());
				  ps.setString(2, district.getEsdName());
				  ps.setString(3, district.getCode());
				  ps.setString(4, district.getName());
				  ps.setString(5, district.getAddressLine1());
				  ps.setString(6, district.getAddressLine2());
				  ps.setString(7, district.getState());
				  ps.setString(8, district.getZipCode());
				  ps.setString(9, district.getEmail());
				  ps.setString(10, district.getPhone());
				  ps.setString(11, district.getAdministratorName());
				  
				  ps.execute();
				  
				  ps.close();
				  conn.close();
			  } catch(Exception e) {
				  System.out.println("ERROR: " + this.getClass().getName() + " - Issue with calling insert for district");
				  e.printStackTrace();
			  }
		} else {
			System.out.println("ERROR: " + this.getClass().getName() + " - connection is null, cannot insert district information into table");
		}
	}
	
	/**
	 * Update a district in the database by id
	 * @param district
	 */
	public void updateDistrict(District district) {
		
		startConnection();
		if(conn!= null) {
				//code, name, addressline1, addressline2, state, zip, email, phone, administratorname
			  String sql = "UPDATE districts SET esdcode=?, esdname=?, code=?, name=?, addressline1=?, addressline2=?, state=?, zipcode=?, email=?, phone=?, administratorname=? WHERE iddistricts=?";
			  try {
				  PreparedStatement ps = conn.prepareStatement(sql);
				  ps.setString(1, district.getEsdCode());
				  ps.setString(2, district.getEsdName());
				  ps.setString(3, district.getCode());
				  ps.setString(4, district.getName());
				  ps.setString(5, district.getAddressLine1());
				  ps.setString(6, district.getAddressLine2());
				  ps.setString(7, district.getState());
				  ps.setString(8, district.getZipCode());
				  ps.setString(9, district.getEmail());
				  ps.setString(10, district.getPhone());
				  ps.setString(11, district.getAdministratorName());
				  
				  ps.setInt(12, Integer.parseInt(district.getPrimaryKey()));
				  
				  ps.executeUpdate();
				  
				  ps.close();
				  conn.close();
			  } catch(Exception e) {
				  System.out.println("ERROR: " + this.getClass().getName() + " - Issue with calling updating for district");
				  e.printStackTrace();
			  }
		} else {
			System.out.println("ERROR: " + this.getClass().getName() + " - connection is null, cannot insert eds information into table");
		}
	}
	
	public int deleteDistrict(String primaryKey) {
		startConnection();
		if(conn!=null) {
			String sql = "DELETE FROM districts WHERE iddistricts=?";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, Integer.parseInt(primaryKey));
				return ps.executeUpdate();
			}catch(Exception e) {
				System.out.println("ERROR: " + this.getClass().getName() + " - Issue with deleting district with primary key of " + primaryKey);
				e.printStackTrace();
			}
		} else {
			System.out.println("ERROR: " + this.getClass().getName() + " - connection is null, cannot delete district with primary key of " + primaryKey);
		}
		return 0;
		
	}
	
	///////////////////////////////
	//School Database calls
	//////////////////////////////
	
	/**
	 * Get all the Schools in the database.
	 * @return JSONObject of the table
	 */
	public ArrayList<School> getAllSchools() {
		ArrayList<School> schools = new ArrayList<School>();
		startConnection();
		if(conn != null) {
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery( "SELECT * FROM schools");
				
				//looping through the result set
				while(rs.next()) {
					School school = new School(rs);
					schools.add(school);
				}
				
		        rs.close();
		        stmt.close();
		        conn.close();
		    } catch(Exception e) {
		    	System.out.println("ERROR: " + this.getClass().getName() + " - Issue getting all Schools");
		    	e.printStackTrace();
		    }
		} else {
			System.out.println("ERROR: " + this.getClass().getName() + " - connection is null, cannot get school information out of the resultset");
		}
		
		return schools;
	}
	
	/**
	 * Get single school based off id/primarkey
	 * @param Id
	 * @return
	 */
	public School getSchoolById(String Id) {
		School school = new School();
		
		startConnection();
		if(conn != null) {
			try {
				String sql = "SELECT * FROM schools WHERE idschools = ?";
				  PreparedStatement ps = conn.prepareStatement(sql);
				  ps.setString(1, Id);
				  ResultSet rs = ps.executeQuery();
				  
					//looping through the result set
					while(rs.next()) {
						school = new School(rs);
					}
				  
				  ps.close();
				  conn.close();
			  } catch(Exception e) {
				  System.out.println("ERROR: " + this.getClass().getName() + " - Issue with getting single for school by the Id of " + Id);
				  e.printStackTrace();
			  }
		} else {
			System.out.println("ERROR: " + this.getClass().getName() + " - connection is null, cannot get school information out of the resultset");
		}
		
		return school;
	}
	
	/**
	 * Insert a School Record into the table
	 */
	public void insertSchool(School school) {
		startConnection();
		if(conn!= null) {
			  String sql = " insert into schools (esdcode, esdname, code, name, addressline1, addressline2, state, zipcode, email, phone, "
			  		+ "leacode, leaname, lowestgrade, highestgrade, principalname, orgcategorylist, aypcode, gradecategory, city)"
					    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			  try {
				  PreparedStatement ps = conn.prepareStatement(sql);
				  ps.setString(1, school.getEsdCode());
				  ps.setString(2, school.getEsdName());
				  ps.setString(3, school.getCode());
				  ps.setString(4, school.getName());
				  ps.setString(5, school.getAddressLine1());
				  ps.setString(6, school.getAddressLine2());
				  ps.setString(7, school.getState());
				  ps.setString(8, school.getZipCode());
				  ps.setString(9, school.getEmail());
				  ps.setString(10, school.getPhone());
				  ps.setString(11, school.getLeaCode());
				  ps.setString(12, school.getLeaName());
				  ps.setString(13, school.getLowestGrade());
				  ps.setString(14, school.getHighestGrade());
				  ps.setString(15, school.getPrincipalName());
				  ps.setString(16, school.getOrgCategoryList());
				  ps.setString(17, school.getAypCode());
				  ps.setString(18, school.getGradeCategory());
				  ps.setString(19, school.getCity());
				  
				  ps.execute();
				  
				  ps.close();
				  conn.close();
			  } catch(Exception e) {
				  System.out.println("ERROR: " + this.getClass().getName() + " - Issue with calling insert for school");
				  e.printStackTrace();
			  }
		} else {
			System.out.println("ERROR: " + this.getClass().getName() + " - connection is null, cannot insert school information into table");
		}
	}
	
	/**
	 * Update a school in the database by id
	 * @param school
	 */
	public void updateSchool(School school) {
		
		startConnection();
		if(conn!= null) {
				//esdcode, esdname, code, name, addressline1, addressline2, state, zip, email, phone, leacode, leaname, lowestgrade, highestgrade, principalname, orgcategorylist, aypcode, gradecategory 
			  String sql = "UPDATE schools SET esdcode=?, esdname=?, code=?, name=?, addressline1=?, addressline2=?, state=?, zipcode=?, email=?, phone=?, "
			  		+ "leacode=?, leaname=?, lowestgrade=?, highestgrade=?, principalname=?, orgcategorylist=?, aypcode=?, gradecategory=?, city=? "
			  		+ "WHERE idschools=?";
			  try {
				  PreparedStatement ps = conn.prepareStatement(sql);
				  ps.setString(1, school.getEsdCode());
				  ps.setString(2, school.getEsdName());
				  ps.setString(3, school.getCode());
				  ps.setString(4, school.getName());
				  ps.setString(5, school.getAddressLine1());
				  ps.setString(6, school.getAddressLine2());
				  ps.setString(7, school.getState());
				  ps.setString(8, school.getZipCode());
				  ps.setString(9, school.getEmail());
				  ps.setString(10, school.getPhone());
				  ps.setString(11, school.getLeaCode());
				  ps.setString(12, school.getLeaName());
				  ps.setString(13, school.getLowestGrade());
				  ps.setString(14, school.getHighestGrade());
				  ps.setString(15, school.getPrincipalName());
				  ps.setString(16, school.getOrgCategoryList());
				  ps.setString(17, school.getAypCode());
				  ps.setString(18, school.getGradeCategory());
				  ps.setString(19, school.getCity());
				  
				  ps.setInt(20, Integer.parseInt(school.getPrimaryKey()));
				  
				  ps.executeUpdate();
				  
				  ps.close();
				  conn.close();
			  } catch(Exception e) {
				  System.out.println("ERROR: " + this.getClass().getName() + " - Issue with calling updating for school");
				  e.printStackTrace();
			  }
		} else {
			System.out.println("ERROR: " + this.getClass().getName() + " - connection is null, cannot insert school information into table");
		}
	}
	
	public int deleteSchool(String primaryKey) {
		startConnection();
		if(conn!=null) {
			String sql = "DELETE FROM schools WHERE idschools=?";
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, Integer.parseInt(primaryKey));
				return ps.executeUpdate();
			}catch(Exception e) {
				System.out.println("ERROR: " + this.getClass().getName() + " - Issue with deleting school with primary key of " + primaryKey);
				e.printStackTrace();
			}
		} else {
			System.out.println("ERROR: " + this.getClass().getName() + " - connection is null, cannot delete school with primary key of " + primaryKey);
		}
		return 0;
		
	}
}
