package org.arta.vopros.domain;

public class Discipline {
    private String name;
    private Discipline parentDiscipline;

    public Discipline(String name) {
        this.name = name;
        parentDiscipline = null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParentDiscipline(Discipline parentDiscipline) {
        this.parentDiscipline = parentDiscipline;
    }
}
