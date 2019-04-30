TODO:
https://www.javainuse.com/spring/boot_security_jdbc_authentication

https://www.baeldung.com/spring-security-authentication-with-a-database

### Project Description ###
The users who have logged in the system is allowed to view only pages within their scope and role. 
If they access the protected pages located beyond their role, they will be denied. 

ORACLE dependency: Adding the oracle jar externally.
<dependency>
		<groupId>com.oracle</groupId>
		<artifactId>ojdbc7</artifactId>
		<version>12.1.0.2</version>
</dependency>


This application has some functions (pages) of which:

    /userInfo

This is a page for viewing user's information. This page requires an user to log in and have the role such as ROLE_ADMIN or ROLE_USER.

    /admin

This is a page for administrator. It requires users to log in, and only the people with ROLE_ADMIN role has access.

   /. /welcome, /login, /logout, /403

All other pages of the application don't require users to log in.

ISSUE-1:
java.lang.illegalargumentexception there is no passwordencoder mapped for the id null spring boot

Solution: Check encoder in WebSecurityConfig

		
		
		
		