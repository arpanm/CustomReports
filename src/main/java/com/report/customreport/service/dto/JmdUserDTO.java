package com.report.customreport.service.dto;

import com.report.customreport.domain.enumeration.JmdClass;
import com.report.customreport.domain.enumeration.JmdRole;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.report.customreport.domain.JmdUser} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class JmdUserDTO implements Serializable {

    private Long id;

    private Long prmid;

    private JmdRole jmdRole;

    private JmdClass jmdClass;

    private String name;

    private Long phone;

    private Boolean isActive;

    private Long createdBy;

    private LocalDate createdOn;

    private Long updatedBy;

    private LocalDate updatedOn;

    private Set<JmdUserDTO> retailers = new HashSet<>();

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

    public JmdRole getJmdRole() {
        return jmdRole;
    }

    public void setJmdRole(JmdRole jmdRole) {
        this.jmdRole = jmdRole;
    }

    public JmdClass getJmdClass() {
        return jmdClass;
    }

    public void setJmdClass(JmdClass jmdClass) {
        this.jmdClass = jmdClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
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

    public Set<JmdUserDTO> getRetailers() {
        return retailers;
    }

    public void setRetailers(Set<JmdUserDTO> retailers) {
        this.retailers = retailers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JmdUserDTO)) {
            return false;
        }

        JmdUserDTO jmdUserDTO = (JmdUserDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, jmdUserDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JmdUserDTO{" +
            "id=" + getId() +
            ", prmid=" + getPrmid() +
            ", jmdRole='" + getJmdRole() + "'" +
            ", jmdClass='" + getJmdClass() + "'" +
            ", name='" + getName() + "'" +
            ", phone=" + getPhone() +
            ", isActive='" + getIsActive() + "'" +
            ", createdBy=" + getCreatedBy() +
            ", createdOn='" + getCreatedOn() + "'" +
            ", updatedBy=" + getUpdatedBy() +
            ", updatedOn='" + getUpdatedOn() + "'" +
            ", retailers=" + getRetailers() +
            "}";
    }
}
