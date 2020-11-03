package ru.nsu.protasov.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "OutlayExceedances")
public class OutlayExceedance extends Identifiable implements Serializable {
    @MapsId("id")
    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Outlay outlayRow;

    private int exceedanceCount;

    public Outlay getOutlayRow() {
        return outlayRow;
    }

    public void setOutlayRow(Outlay outlayRow) {
        this.outlayRow = outlayRow;
    }

    public int getExceedanceCount() {
        return exceedanceCount;
    }

    public void setExceedanceCount(int exceedanceCount) {
        this.exceedanceCount = exceedanceCount;
    }
}
