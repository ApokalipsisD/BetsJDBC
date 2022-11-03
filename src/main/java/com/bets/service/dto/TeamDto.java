package com.bets.service.dto;

import java.util.Objects;

public class TeamDto extends AbstractDto<Integer> {
    private String name;

    public TeamDto() {
    }

    public TeamDto(String name) {
        this.name = name;
    }

    public TeamDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeamDto teamDto = (TeamDto) o;
        return name.equals(teamDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "TeamDto{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
