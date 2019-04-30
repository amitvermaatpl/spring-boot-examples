package com.ibm.security.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.ibm.security.model.AppUser;

/*
 * The  AppUserMapper class is used for mapping the columns in the  
 * APP_USER table with the fields in the  AppUser class (Based on the query statement). 
 * 
 */
public class AppUserMapper implements RowMapper<AppUser> {
	 
    public static final String BASE_SQL //
            = "Select u.User_Id, u.User_Name, u.Encryted_Password From App_User u ";
 
    @Override
    public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
 
        Long userId = rs.getLong("User_Id");
        String userName = rs.getString("User_Name");
        String encrytedPassword = rs.getString("Encryted_Password");
 
        return new AppUser(userId, userName, encrytedPassword);
    }
 
}