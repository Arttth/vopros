package org.arta.vopros.domain;

public class Solution {
    private String title;
    private String solutionExplPart;
    private Integer reputation;

    public Solution() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSolutionExplPart(String solutionExplPart) {
        this.solutionExplPart = solutionExplPart;
    }

    public void increaseReputation(Integer count) {
        this.reputation += count;
    }
}
