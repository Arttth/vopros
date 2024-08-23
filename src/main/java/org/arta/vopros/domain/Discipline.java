package org.arta.vopros.domain;

import java.util.Objects;

public class Discipline {
    private Long id;
    private String name;
    private Discipline parentDiscipline;

    public Discipline() {}

    public Discipline(Long id, String name, Discipline parentDiscipline) {
        this.id = id;
        this.name = name;
        this.parentDiscipline = parentDiscipline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Discipline getParentDiscipline() {
        return parentDiscipline;
    }

    public void setParentDiscipline(Discipline parentDiscipline) {
        this.parentDiscipline = parentDiscipline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discipline that = (Discipline) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(parentDiscipline, that.parentDiscipline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, parentDiscipline);
    }

    @Override
    public String toString() {
        return "Discipline{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentDiscipline=" + parentDiscipline +
                '}';
    }
}
