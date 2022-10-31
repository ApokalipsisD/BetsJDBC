package com.bets.model;

import java.math.BigDecimal;
import java.util.Objects;

public class User extends Entity<Integer> {
    private String login;
    private String password;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private BigDecimal balance;
    private Role role;

    public User() {
    }

    public User(String login, String password, String name, String surname, Integer age, String email, BigDecimal balance, Integer roleId) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.balance = balance;
        this.role = Role.getById(roleId);
    }

    public User(Integer id, String login, String password, String name, String surname, Integer age, String email, BigDecimal balance, Integer roleId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.balance = balance;
        this.role = Role.getById(roleId);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return login.equals(user.login) && password.equals(user.password) && Objects.equals(name, user.name) && Objects.equals(surname, user.surname) && Objects.equals(age, user.age) && Objects.equals(email, user.email) && balance.equals(user.balance) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, name, surname, age, email, balance, role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                ", role=" + role +
                '}';
    }
}
