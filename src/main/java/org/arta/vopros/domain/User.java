package org.arta.vopros.domain;

import java.sql.Date;
import java.util.Objects;

public class User {
    private Long id;
    private String nickname;
    private String name;
    private String lastname;
    private Date dateOfBirth;
    private String profilePhoto;
    private Integer reputation;

    public User() {
    }

    public User(Long id, String nickname, String name, String lastname, Date dateOfBirth, String profilePhoto, Integer reputation) {
        this.id = id;
        this.nickname = nickname;
        this.name = name;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.profilePhoto = profilePhoto;
        this.reputation = reputation;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void increaseReputation(Integer count) {
        this.reputation += count;
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }


    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public Integer getReputation() {
        return reputation;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", profilePhoto='" + profilePhoto + '\'' +
                ", reputation=" + reputation +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(nickname, user.nickname) && Objects.equals(name, user.name) && Objects.equals(lastname, user.lastname) && Objects.equals(dateOfBirth, user.dateOfBirth) && Objects.equals(profilePhoto, user.profilePhoto) && Objects.equals(reputation, user.reputation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nickname, name, lastname, dateOfBirth, profilePhoto, reputation);
    }
}
