package com.report.customreport.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A JmdSales.
 */
@Entity
@Table(name = "jmd_sales")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class JmdSales implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "prmid")
    private Long prmid;

    @Column(name = "unis_order")
    private Long unisOrder;

    @Column(name = "units_delivered")
    private Long unitsDelivered;

    @Column(name = "unis_activated")
    private Long unisActivated;

    @Column(name = "jmd_month")
    private Long jmdMonth;

    @Column(name = "jmd_year")
    private Long jmdYear;

    @Column(name = "jmddate")
    private Long jmddate;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_on")
    private LocalDate createdOn;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "updated_on")
    private LocalDate updatedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "salesData", "retailers", "jmdos" }, allowSetters = true)
    private JmdUser retailer;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public JmdSales id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrmid() {
        return this.prmid;
    }

    public JmdSales prmid(Long prmid) {
        this.setPrmid(prmid);
        return this;
    }

    public void setPrmid(Long prmid) {
        this.prmid = prmid;
    }

    public Long getUnisOrder() {
        return this.unisOrder;
    }

    public JmdSales unisOrder(Long unisOrder) {
        this.setUnisOrder(unisOrder);
        return this;
    }

    public void setUnisOrder(Long unisOrder) {
        this.unisOrder = unisOrder;
    }

    public Long getUnitsDelivered() {
        return this.unitsDelivered;
    }

    public JmdSales unitsDelivered(Long unitsDelivered) {
        this.setUnitsDelivered(unitsDelivered);
        return this;
    }

    public void setUnitsDelivered(Long unitsDelivered) {
        this.unitsDelivered = unitsDelivered;
    }

    public Long getUnisActivated() {
        return this.unisActivated;
    }

    public JmdSales unisActivated(Long unisActivated) {
        this.setUnisActivated(unisActivated);
        return this;
    }

    public void setUnisActivated(Long unisActivated) {
        this.unisActivated = unisActivated;
    }

    public Long getJmdMonth() {
        return this.jmdMonth;
    }

    public JmdSales jmdMonth(Long jmdMonth) {
        this.setJmdMonth(jmdMonth);
        return this;
    }

    public void setJmdMonth(Long jmdMonth) {
        this.jmdMonth = jmdMonth;
    }

    public Long getJmdYear() {
        return this.jmdYear;
    }

    public JmdSales jmdYear(Long jmdYear) {
        this.setJmdYear(jmdYear);
        return this;
    }

    public void setJmdYear(Long jmdYear) {
        this.jmdYear = jmdYear;
    }

    public Long getJmddate() {
        return this.jmddate;
    }

    public JmdSales jmddate(Long jmddate) {
        this.setJmddate(jmddate);
        return this;
    }

    public void setJmddate(Long jmddate) {
        this.jmddate = jmddate;
    }

    public Long getCreatedBy() {
        return this.createdBy;
    }

    public JmdSales createdBy(Long createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return this.createdOn;
    }

    public JmdSales createdOn(LocalDate createdOn) {
        this.setCreatedOn(createdOn);
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public Long getUpdatedBy() {
        return this.updatedBy;
    }

    public JmdSales updatedBy(Long updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return this.updatedOn;
    }

    public JmdSales updatedOn(LocalDate updatedOn) {
        this.setUpdatedOn(updatedOn);
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public JmdUser getRetailer() {
        return this.retailer;
    }

    public void setRetailer(JmdUser jmdUser) {
        this.retailer = jmdUser;
    }

    public JmdSales retailer(JmdUser jmdUser) {
        this.setRetailer(jmdUser);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JmdSales)) {
            return false;
        }
        return id != null && id.equals(((JmdSales) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JmdSales{" +
            "id=" + getId() +
            ", prmid=" + getPrmid() +
            ", unisOrder=" + getUnisOrder() +
            ", unitsDelivered=" + getUnitsDelivered() +
            ", unisActivated=" + getUnisActivated() +
            ", jmdMonth=" + getJmdMonth() +
            ", jmdYear=" + getJmdYear() +
            ", jmddate=" + getJmddate() +
            ", createdBy=" + getCreatedBy() +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy=" + getUpdatedBy() +
            ", updatedOn='" + getUpdatedOn() + "'" +
            "}";
    }
}
