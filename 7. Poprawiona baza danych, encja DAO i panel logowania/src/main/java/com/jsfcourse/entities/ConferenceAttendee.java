/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsfcourse.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author matmo
 */
@Entity
@Table(name = "conference_attendees")
@NamedQueries({
    @NamedQuery(name = "ConferenceAttendee.findAll", query = "SELECT c FROM ConferenceAttendee c"),
    @NamedQuery(name = "ConferenceAttendee.findById", query = "SELECT c FROM ConferenceAttendee c WHERE c.id = :id"),
    @NamedQuery(name = "ConferenceAttendee.findByPaymentStatus", query = "SELECT c FROM ConferenceAttendee c WHERE c.paymentStatus = :paymentStatus")})
public class ConferenceAttendee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 7)
    @Column(name = "payment_status")
    private String paymentStatus;
    @JoinColumn(name = "conference_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Conference conferenceId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;

    public ConferenceAttendee() {
    }

    public ConferenceAttendee(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Conference getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(Conference conferenceId) {
        this.conferenceId = conferenceId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof ConferenceAttendee)) {
            return false;
        }
        ConferenceAttendee other = (ConferenceAttendee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.ConferenceAttendee[ id=" + id + " ]";
    }
    
}
