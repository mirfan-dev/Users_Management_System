# JWT Authentication using Spring Boot 
# Step To Be Followed
<b>Step 1: Set Up Spring Boot Project</b><br>
    <p><li>Ensure you have a Spring Boot project set up with dependencies for Spring Security, JWT, Spring Data JPA, and any other required components. You can add the following dependencies to your pom.xml.</li></p>
<p>2.Secure the Rest API by adding security dependecy</p>
<p>3.Use the properties file to create custom username and password for authentication</p>
<p>4.Create the SpringSecurityConfig class to define the bean like PasswordEncoder, UserDetailsService and AuthenticationManager</p>
<p>5.Create JwtAuthenticationEntryPoint Class Which implements AuthenticationEntryPoint interface and override method commence</p>
<p>6.Create JwtHelper Class which is used to perform action like validateToken and generateToken etc</p>
<p>7.Create JWTAuthenticationFilter class which is used for the filter purpose</p>
<p>8.Create SecurityFilterConfig class to define request processing logic</p>
<p>9.Create JwtRequest and JwtResponse class</p>
<p>10.Create JwtAuthenticationController to return the JwtResponse if everything works fine</p>

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
