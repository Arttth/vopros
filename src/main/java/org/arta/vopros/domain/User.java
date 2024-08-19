package org.arta.vopros.domain;

import java.awt.*;
import java.util.Date;

public class User {
    private String nickname;
    private String name;
    private String lastname;
    private String education;
    private Date dateOfBirth;
    private Image profilePhoto;
    private Integer reputation;

    public User() {
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

    public void setEducation(String education) {
        this.education = education;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setProfilePhoto(Image profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void increaseReputation(Integer count) {
        this.reputation += count;
    }
}
