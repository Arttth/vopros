package org.arta.vopros.domain;

import java.util.Objects;

public class Solution {
    private Long id;
    private String solution;
    private String solutionFile;
    private Question question;
    public Solution() {
    }

    public Solution(Long id, String solution, String solutionFile, Question question) {
        this.id = id;
        this.solution = solution;
        this.solutionFile = solutionFile;
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getSolutionFile() {
        return solutionFile;
    }

    public void setSolutionFile(String solutionFile) {
        this.solutionFile = solutionFile;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Solution solution1 = (Solution) o;
        return Objects.equals(id, solution1.id) && Objects.equals(solution, solution1.solution) && Objects.equals(solutionFile, solution1.solutionFile) && Objects.equals(question, solution1.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, solution, solutionFile, question);
    }

    @Override
    public String toString() {
        return "Solution{" +
                "id=" + id +
                ", solution='" + solution + '\'' +
                ", solutionFile='" + solutionFile + '\'' +
                ", question=" + question +
                '}';
    }
}
