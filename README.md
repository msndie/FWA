# FWA
This project is part of the Java branch in holygraph of School21.

<details>
<summary>Screenshots</summary>
<details>
<summary>Index</summary>
![Image](/screenshots/index.png?raw=true)
</details>
<details>
<summary>SignUp</summary>
![Image](/screenshots/signUp.png?raw=true)
</details>
<details>
<summary>SignIn</summary>
![Image](/screenshots/signIn.png?raw=true)
</details>
<details>
<summary>Default Profile</summary>
![Image](/screenshots/default_profile_page.png?raw=true)
</details>
<details>
<summary>Profile with avatar</summary>
![Image](/screenshots/profile_page_with_avatar.png?raw=true)
</details>
</details>

# Now you may ask what is the point?
Well, the point is to learn how to work with Java servlets, HTML, CSS, JSP, Jdbc, Spring and Web apps in general.
This is a single page website with registation, authorization and possibility to load avatars.

# What you need to do to launch it?
You need to create an empty database in postgres and write your credentials in ```application.properties``` file and change path to folder where images will be stored.
You dont need tomcat to run this, but you need maven.

# Command to run
mvn clean package org.codehaus.cargo:cargo-maven3-plugin:run

# Docker
You can run it with docker-compose.

```
cd docker
docker-compose up --build
```

# localhost:8080/fwa is entrypoint for a website
