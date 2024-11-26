# JWT Authentication using Spring Boot 
# Step To Be Followed
<p>1.Create Basic Rest API that will return the list of Details like Employee,Student or any other.</p>
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
<p>1. For rest api</p>
      <p><dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-web</artifactId>
     </dependency></p>

