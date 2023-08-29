package com.report.customreport.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.report.customreport.domain.enumeration.JmdClass;
import com.report.customreport.domain.enumeration.JmdRole;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A JmdUser.
 */
@Entity
@Table(name = "jmd_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class JmdUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "prmid")
    private Long prmid;

    @Enumerated(EnumType.STRING)
    @Column(name = "jmd_role")
    private JmdRole jmdRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "jmd_class")
    private JmdClass jmdClass;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_on")
    private LocalDate createdOn;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "updated_on")
    private LocalDate updatedOn;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "retailer")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "retailer" }, allowSetters = true)
    private Set<JmdSales> salesData = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "rel_jmd_user__retailer",
        joinColumns = @JoinColumn(name = "jmd_user_id"),
        inverseJoinColumns = @JoinColumn(name = "retailer_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "salesData", "retailers", "jmdos" }, allowSetters = true)
    private Set<JmdUser> retailers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "retailers")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "salesData", "retailers", "jmdos" }, allowSetters = true)
    private Set<JmdUser> jmdos = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public JmdUser id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrmid() {
        return this.prmid;
    }

    public JmdUser prmid(Long prmid) {
        this.setPrmid(prmid);
        return this;
    }

    public void setPrmid(Long prmid) {
        this.prmid = prmid;
    }

    public JmdRole getJmdRole() {
        return this.jmdRole;
    }

    public JmdUser jmdRole(JmdRole jmdRole) {
        this.setJmdRole(jmdRole);
        return this;
    }

    public void setJmdRole(JmdRole jmdRole) {
        this.jmdRole = jmdRole;
    }

    public JmdClass getJmdClass() {
        return this.jmdClass;
    }

    public JmdUser jmdClass(JmdClass jmdClass) {
        this.setJmdClass(jmdClass);
        return this;
    }

    public void setJmdClass(JmdClass jmdClass) {
        this.jmdClass = jmdClass;
    }

    public String getName() {
        return this.name;
    }

    public JmdUser name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPhone() {
        return this.phone;
    }

    public JmdUser phone(Long phone) {
        this.setPhone(phone);
        return this;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public JmdUser isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Long getCreatedBy() {
        return this.createdBy;
    }

    public JmdUser createdBy(Long createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return this.createdOn;
    }

    public JmdUser createdOn(LocalDate createdOn) {
        this.setCreatedOn(createdOn);
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public Long getUpdatedBy() {
        return this.updatedBy;
    }

    public JmdUser updatedBy(Long updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDate getUpdatedOn() {
        return this.updatedOn;
    }

    public JmdUser updatedOn(LocalDate updatedOn) {
        this.setUpdatedOn(updatedOn);
        return this;
    }

    public void setUpdatedOn(LocalDate updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Set<JmdSales> getSalesData() {
        return this.salesData;
    }

    public void setSalesData(Set<JmdSales> jmdSales) {
        if (this.salesData != null) {
            this.salesData.forEach(i -> i.setRetailer(null));
        }
        if (jmdSales != null) {
            jmdSales.forEach(i -> i.setRetailer(this));
        }
        this.salesData = jmdSales;
    }

    public JmdUser salesData(Set<JmdSales> jmdSales) {
        this.setSalesData(jmdSales);
        return this;
    }

    public JmdUser addSalesData(JmdSales jmdSales) {
        this.salesData.add(jmdSales);
        jmdSales.setRetailer(this);
        return this;
    }

    public JmdUser removeSalesData(JmdSales jmdSales) {
        this.salesData.remove(jmdSales);
        jmdSales.setRetailer(null);
        return this;
    }

    public Set<JmdUser> getRetailers() {
        return this.retailers;
    }

    public void setRetailers(Set<JmdUser> jmdUsers) {
        this.retailers = jmdUsers;
    }

    public JmdUser retailers(Set<JmdUser> jmdUsers) {
        this.setRetailers(jmdUsers);
        return this;
    }

    public JmdUser addRetailer(JmdUser jmdUser) {
        this.retailers.add(jmdUser);
        jmdUser.getJmdos().add(this);
        return this;
    }

    public JmdUser removeRetailer(JmdUser jmdUser) {
        this.retailers.remove(jmdUser);
        jmdUser.getJmdos().remove(this);
        return this;
    }

    public Set<JmdUser> getJmdos() {
        return this.jmdos;
    }

    public void setJmdos(Set<JmdUser> jmdUsers) {
        if (this.jmdos != null) {
            this.jmdos.forEach(i -> i.removeRetailer(this));
        }
        if (jmdUsers != null) {
            jmdUsers.forEach(i -> i.addRetailer(this));
        }
        this.jmdos = jmdUsers;
    }

    public JmdUser jmdos(Set<JmdUser> jmdUsers) {
        this.setJmdos(jmdUsers);
        return this;
    }

    public JmdUser addJmdo(JmdUser jmdUser) {
        this.jmdos.add(jmdUser);
        jmdUser.getRetailers().add(this);
        return this;
    }

    public JmdUser removeJmdo(JmdUser jmdUser) {
        this.jmdos.remove(jmdUser);
        jmdUser.getRetailers().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JmdUser)) {
            return false;
        }
        return id != null && id.equals(((JmdUser) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JmdUser{" +
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
            "}";
    }
}
