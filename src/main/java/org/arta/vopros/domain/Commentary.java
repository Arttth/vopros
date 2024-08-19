package org.arta.vopros.domain;

public class Commentary {
    private String text;
    private Integer reputation;
    private User author;

    public Commentary() {
    }

    public void increaseReputation(Integer count) {
        this.reputation += count;
    }

}
