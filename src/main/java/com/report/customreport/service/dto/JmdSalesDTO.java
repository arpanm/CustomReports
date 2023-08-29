package com.report.customreport.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.report.customreport.domain.JmdSales} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class JmdSalesDTO implements Serializable {

    private Long id;

    private Long prmid;

    private Long unisOrder;

    private Long unitsDelivered;

    private Long unisActivated;

    private Long jmdMonth;

    private Long jmdYear;

    private Long jmddate;

    private Long createdBy;

    private LocalDate createdOn;

    private Long updatedBy;

    private LocalDate updatedOn;

    private JmdUserDTO retailer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrmid() {
        return prmid;
    }

    public void setPrmid(Long prmid) {
        this.prmid = prmid;
    }

    public Long getUnisOrder() {
        return unisOrder;
    }

    public void setUnisOrder(Long unisOrder) {
        this.unisOrder = unisOrder;
    }

    public Long getUnitsDelivered() {
        return unitsDelivered;
    }

    public void setUnitsDelivered(Long unitsDelivered) {
        this.unitsDelivered = unitsDelivered;
    }

    public Long getUnisActivated() {
        return unisActivated;
    }

    public void setUnisActivated(Long unisActivated) {
        this.unisActivated = unisActivated;
    }

    public Long getJmdMonth() {
        return jmdMonth;
    }

    public void setJmdMonth(Long jmdMonth) {
        this.jmdMonth = jmdMonth;
    }

    public Long getJmdYear() {
        return jmdYear;
    }

    public void setJmdYear(Long jmdYear) {
        this.jmdYear = jmdYear;
    }

    public Long getJmddate() {
        return jmddate;
    }

    public void setJmddate(Long jmddate) {
        this.jmddate = jmddate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public JmdUserDTO getRetailer() {
        return retailer;
    }

    public void setRetailer(JmdUserDTO retailer) {
        this.retailer = retailer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JmdSalesDTO)) {
            return false;
        }

        JmdSalesDTO jmdSalesDTO = (JmdSalesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, jmdSalesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JmdSalesDTO{" +
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
            ", retailer=" + getRetailer() +
            "}";
    }
}
