/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsfcourse.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author matmo
 */
@Entity
@Table(name = "conferences")
@NamedQueries({
    @NamedQuery(name = "Conference.findAll", query = "SELECT c FROM Conference c"),
    @NamedQuery(name = "Conference.findById", query = "SELECT c FROM Conference c WHERE c.id = :id"),
    @NamedQuery(name = "Conference.findByTitle", query = "SELECT c FROM Conference c WHERE c.title = :title"),
    @NamedQuery(name = "Conference.findByStartDate", query = "SELECT c FROM Conference c WHERE c.startDate = :startDate"),
    @NamedQuery(name = "Conference.findByEndDate", query = "SELECT c FROM Conference c WHERE c.endDate = :endDate"),
    @NamedQuery(name = "Conference.findByLocation", query = "SELECT c FROM Conference c WHERE c.location = :location"),
    @NamedQuery(name = "Conference.findByOrganizerId", query = "SELECT c FROM Conference c WHERE c.organizerId = :organizerId"),
    @NamedQuery(name = "Conference.findByPrice", query = "SELECT c FROM Conference c WHERE c.price = :price"),
    @NamedQuery(name = "Conference.findByCreatedAt", query = "SELECT c FROM Conference c WHERE c.createdAt = :createdAt"),
    @NamedQuery(name = "Conference.findByUpdatedAt", query = "SELECT c FROM Conference c WHERE c.updatedAt = :updatedAt")})
public class Conference implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title")
    private String title;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "location")
    private String location;
    @Basic(optional = false)
    @NotNull
    @Column(name = "organizer_id")
    private long organizerId;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conferenceId")
    private Collection<ConferenceAttendee> conferenceAttendeeCollection;

    public Conference() {
    }

    public Conference(Long id) {
        this.id = id;
    }

    public Conference(Long id, String title, Date startDate, Date endDate, String location, long organizerId, BigDecimal price, Date createdAt, Date updatedAt) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.organizerId = organizerId;
        this.price = price;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getOrganizerId() {
        return organizerId;
    }

    public void setOrganizerId(long organizerId) {
        this.organizerId = organizerId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Collection<ConferenceAttendee> getConferenceAttendeeCollection() {
        return conferenceAttendeeCollection;
    }

    public void setConferenceAttendeeCollection(Collection<ConferenceAttendee> conferenceAttendeeCollection) {
        this.conferenceAttendeeCollection = conferenceAttendeeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conference)) {
            return false;
        }
        Conference other = (Conference) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.Conference[ id=" + id + " ]";
    }
    
}
