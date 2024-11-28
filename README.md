# Role-Based Access Control (RBAC) using Spring Boot 
# Step To Be Followed
<b>Step 1: Set Up Spring Boot Project</b><br>
    <p><li>Ensure you have a Spring Boot project set up with dependencies for Spring Security, JWT, Lombok, and any other required components. You can add the following dependencies to your pom.xml.</li></p>
<b>Step 2: Define the Entity OurUsers</b><br>
<p><li>The OurUsers class represents the users in the system, storing details like email, name, password, city, and role. It also implements UserDetails for integration with Spring Security.</li></p>
<b>Step 3: Create the UsersRepo Repository</b><br>
<p><li>First, ensure that you have a UsersRepo repository interface that interacts with your database. This repository will handle the retrieval of user details.</li></p>
<b>Step 4: Create DTO Class (ReqRes)</b><br>
<p><li>This Data Transfer Object (DTO) is used for handling requests and responses, including validation annotations.</li></p>
<b>Step 5: Create JWT Utility (JWTUtils)</b><br>
<p><li>The JWT Utility class is responsible for generating, validating, and extracting information from JSON Web Tokens (JWTs). These tokens are used for securing your Spring Boot APIs and authenticating users.</li></p>
<b>Step 6: Create the OurUserDetailsService Class</b><br>
<p><li>This service will implement UserDetailsService to load user details by email:</li></p>
<b>Step 7: Set Up Security Configuration (SecurityConfig)</b><br>
<p><li>Configure Spring Security to handle authentication, role-based access, JWT authentication, and stateless session management:</li></p>
<b>Step 8: Create the JWTAuthFilter Class</b><br>
<p><li>This filter extracts JWT tokens from the request, validates them, and sets the authentication context:</li></p>
<b>Step 9: Implement OurUserServiceImpl Class</b><br>
<p><li>This class will implement the business logic for managing users, such as registering users and fetching user details. It will interact with the UsersRepo repository.</li></p>
<b>Step 10: Implement Controller Class (UserController)</b><br>
<p><li>Now, you can implement a controller class to expose the user management APIs (for registration, login, updating, and deleting users):</li></p>
<b>Step 1: Define CorsConfig Class<b><br>
<p><li>This class ensures that your Spring Boot application can accept requests from different origins (e.g., frontend applications running on different servers or ports). The configuration defines which HTTP methods (GET, POST, PUT, DELETE) and which origins are allowed to interact with your API.</li></p>

# Important Dependency to be used
<p>1. For REST API</p>
    <pre>
     <code>
            &lt;dependency&gt;
                &lt;groupId&gt;org.springframework.boot&lt;/groupId&gt;
                &lt;artifactId&gt;spring-boot-starter-web&lt;/artifactId&gt;
            &lt;/dependency&gt;
   </code>            
 </pre>

 <p>2. For Getter and Setter</p>
    <pre>
     <code>
            &lt;dependency&gt;
                &lt;groupId&gt;org.projectlombok&lt;/groupId&gt;
                &lt;artifactId&gt;lombok&lt;/artifactId&gt;
                &lt;optional&gt;true&lt;/optional&gt;
            &lt;/dependency&gt;
   </code>            
 </pre>

 
<p>2. For Security</p>
    <pre>
     <code>
            &lt;dependency&gt;
                &lt;groupId&gt;org.springframework.boot&lt;/groupId&gt;
                &lt;artifactId&gt;spring-boot-starter-security;/artifactId&gt;
                &lt;optional&gt;true&lt;/optional&gt;
            &lt;/dependency&gt;
   </code>            
 </pre>


 <p>2. For JWT</p>

<pre>
    <code>
      &lt;!---  https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-api  ---&gt;
        &lt;dependency&gt;
            &lt;groupId&gt;io.jsonwebtoken&lt;/groupId&gt;
            &lt;artifactId&gt;jjwt-api&lt;/artifactId&gt;
            &lt;version&gt;0.12.5&lt;/version&gt;
        &lt;/dependency&gt;
   



      &lt;!--- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-impl ---&gt;
        &lt;dependency&gt;
            &lt;groupId&gt;io.jsonwebtoken&lt;/groupId&gt;
            &lt;artifactId&gt;jjwt-impl&lt;/artifactId&gt;
            &lt;version&gt;0.12.5&lt;/version&gt;
            &lt;scope&gt;runtime&lt;/scope&gt;
        &lt;/dependency&gt;
   


       &lt;!--- https://mvnrepository.com/artifact/io.jsonwebtoken/jjwt-jackson ---&gt;
        &lt;dependency&gt;
            &lt;groupId&gt;io.jsonwebtoken&lt;/groupId&gt;
            &lt;artifactId&gt;jjwt-jackson&lt;/artifactId&gt;
            &lt;version&gt;0.12.5&lt;/version&gt;
            &lt;scope&gt;runtime&lt;/scope&gt;
        &lt;/dependency&gt;
    </code>
</pre>

# JWT Authentication Work Flow



 
![Jwt image](https://github.com/user-attachments/assets/f57f6cff-19dd-4bb2-81d1-008993a32c06)

https://jwt.io/#debugger

# Register

![WhatsApp Image 2024-11-28 at 4 07 41 PM](https://github.com/user-attachments/assets/4478ace4-4931-431a-bc93-b0d9d93890f0)

<pre>
    <code>
{
    "statusCode": 200,
    "message": "User Saved Successfully",
    "ourUsers": {
        "id": 1,
        "email": "mirfan@gmail.com",
        "name": "Md Irfan",
        "password": "$2a$10$CUMXOMLnxkAfPkzrh7RABukhO![WhatsApp Image 2024-11-28 at 4 17 10 PM](https://github.com/user-attachments/assets/377f25e8-225d-4d04-96d5-7d57c6703d25)
BeIXsbuNGswB2zyzayooae4JNqdu",
        "city": "Siwan",
        "role": "ADMIN",
        "enabled": true,
        "username": "mirfan@gmail.com",
        "authorities": [
            {
                "authority": "ADMIN"
            }
        ],
        "accountNonLocked": true,
        "credentialsNonExpired": true,
        "accountNonExpired": true
    }
}
    </code>
</pre>

# Login
![WhatsApp Image 2024-11-28 at 4 17 10 PM](https://github.com/user-attachments/assets/152ceb9e-96c8-4de3-b1a0-75e0fe893c7b)

<pre>
    <code>
        {
    "statusCode": 200,
    "message": "Successfully Logged In",
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaXJmYW5AZ21haWwuY29tIiwiaWF0IjoxNzMyNzkwNTA2LCJleHAiOjE3MzI4NzY5MDZ9.2zb5LtYjFHWUlTDpFJCypgubmloyyR5CER9VvzH--XI",
    "refreshToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtaXJmYW5AZ21haWwuY29tIiwiaWF0IjoxNzMyNzkwNTA2LCJleHAiOjE3MzI4NzY5MDZ9.2zb5LtYjFHWUlTDpFJCypgubmloyyR5CER9VvzH--XI",
    "expirationTime": "24Hrs",
    "role": "ADMIN"
}
    </code>
</pre>
