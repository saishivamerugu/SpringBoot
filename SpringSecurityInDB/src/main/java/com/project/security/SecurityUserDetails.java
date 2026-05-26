package com.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.entity.Person;
import com.project.repository.PersonRepository;

@Service
public class SecurityUserDetails implements UserDetailsService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Person person = personRepository.findByUserName(username);

        if(person == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return User
                .builder()
                .username(person.getUserName())
                .password(person.getPassword())
                .roles("USER")
                .build();
    }
} 
 






















/*
 Okay buddy — let’s make it SUPER simple 😄

Forget Spring Security for 2 minutes.

---

# Imagine This Situation

You built your own class:

```java
class Person {
    String userName;
    String password;
}
```

This is YOUR class.

Now imagine Spring Security is another person/system.

It says:

> “If you want me to login users, give me user data in MY format.”

Its format is:

```java
UserDetails
```

NOT:

```java
Person
```

---

# Example From Real Life

Imagine:

You have data written in Telugu.

But another system understands only English.

So what must you do?

```text
Telugu → English
```

Similarly:

```text
Person → UserDetails
```

---

# Your Database Object

You fetch:

```java
Person person = personRepository.findByUserName(username);
```

Now `person` contains:

```text
username = sai
password = abc123
```

BUT Spring Security says:

> “I don't know what Person is 😭”

It only understands:

```java
UserDetails
```

---

# So You Convert It

This line:

```java
return User.builder()
        .username(person.getUserName())
        .password(person.getPassword())
        .roles("USER")
        .build();
```

means:

```text
Take data from Person
and create UserDetails object
```

---

# VERY IMPORTANT

This:

```java
User.builder()
```

creates a Spring Security user object.

NOT your entity.

This object already implements:

```java
UserDetails
```

---

# What Spring Does Next

After receiving `UserDetails`, Spring internally does:

```java
userDetails.getUsername()
```

and

```java
userDetails.getPassword()
```

to check login.

---

# Why Can't Spring Use Person Directly?

Because your `Person` class does NOT have:

```java
getAuthorities()
isEnabled()
isAccountNonExpired()
```

and other security methods.

But `UserDetails` has them.

---

# Small Visualization

## Your Object

```text
Person
--------
userName
password
```

Spring cannot use directly ❌

---

# Convert Into

```text
UserDetails
--------
username
password
roles
authorities
account status
```

Spring understands this ✅

---

# Entire Flow

```text
Database
   ↓
Person object
   ↓
Convert to UserDetails
   ↓
Spring Security uses it
```

---

# One-Line Answer

You return `UserDetails` because:

```text
Spring Security only understands UserDetails objects for authentication.
```

Your `Person` object must be converted into that format.

 */
