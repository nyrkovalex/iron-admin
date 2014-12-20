package org.github.nyrkovalex.ironadmin.example.users;

@SuppressWarnings("UnusedDeclaration")
public class User {
  private final String email;
  private final String firstName;
  private final String lastName;
  private final String password;

  public User(String email, String firstName, String lastName, String password) {
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getPassword() {
    return password;
  }
}
