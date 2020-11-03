package ru.nsu.protasov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "WorkSchedule")
public class WorkSchedule extends Identifiable {
    public WorkSchedule() {}

    public WorkSchedule(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private Date startDate;
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "brigadeObjectWorkId", referencedColumnName = "id")
    private BrigadeObjectWork brigadeWork;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(name = "WorkScheduleMachinery")
    private List<Machinery> machinery;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public BrigadeObjectWork getBrigadeWork() {
        return brigadeWork;
    }

    public void setBrigadeWork(BrigadeObjectWork brigadeWork) {
        this.brigadeWork = brigadeWork;
    }

    public List<Machinery> getMachinery() {
        return machinery;
    }

    public void setMachinery(List<Machinery> machinery) {
        this.machinery = machinery;
    }
}
