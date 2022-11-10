package com.bets.service.dto;

import com.bets.dao.model.Role;

import java.math.BigDecimal;
import java.util.Objects;

public class UserDto extends AbstractDto<Integer>{
    private String login;
    private String password;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private BigDecimal balance;
    private Role role;

    public UserDto() {
    }
    public UserDto(String login, String password, BigDecimal balance, Integer roleId) {
        this.login = login;
        this.password = password;
        this.balance = balance;
        this.role = Role.getById(roleId);
    }

    public UserDto(String login, String password, String name, String surname, Integer age, String email, BigDecimal balance, Integer roleId) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.email = email;
        this.balance = balance;
        this.role = Role.getById(roleId);
    }

    public UserDto(Integer id, String login, String password, String name, String surname, Integer age, String email, BigDecimal balance, Integer roleId) {
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
        UserDto userDto = (UserDto) o;
        return login.equals(userDto.login) && password.equals(userDto.password) && Objects.equals(name, userDto.name) && Objects.equals(surname, userDto.surname) && Objects.equals(age, userDto.age) && Objects.equals(email, userDto.email) && balance.equals(userDto.balance) && role == userDto.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, name, surname, age, email, balance, role);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                ", role=" + role +
                ", id=" + id +
                '}';
    }
}
