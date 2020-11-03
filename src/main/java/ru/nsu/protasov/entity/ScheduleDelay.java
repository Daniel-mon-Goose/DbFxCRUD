package ru.nsu.protasov.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ScheduleDelays")
public class ScheduleDelay extends Identifiable implements Serializable {
    @MapsId("id")
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private WorkSchedule scheduleRow;
    private Date delay;

    public WorkSchedule getScheduleRow() {
        return scheduleRow;
    }

    public void setScheduleRow(WorkSchedule scheduleRow) {
        this.scheduleRow = scheduleRow;
    }

    public Date getDelay() {
        return delay;
    }

    public void setDelay(Date delay) {
        this.delay = delay;
    }
}
