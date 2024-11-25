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
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author matmo
 */
@Entity
@Table(name = "organizers_codes")
@NamedQueries({
    @NamedQuery(name = "OrganizersCodes.findAll", query = "SELECT o FROM OrganizersCodes o"),
    @NamedQuery(name = "OrganizersCodes.findById", query = "SELECT o FROM OrganizersCodes o WHERE o.id = :id"),
    @NamedQuery(name = "OrganizersCodes.findByCode", query = "SELECT o FROM OrganizersCodes o WHERE o.code = :code"),
    @NamedQuery(name = "OrganizersCodes.findByCreatedAt", query = "SELECT o FROM OrganizersCodes o WHERE o.createdAt = :createdAt"),
    @NamedQuery(name = "OrganizersCodes.findByUpdatedAt", query = "SELECT o FROM OrganizersCodes o WHERE o.updatedAt = :updatedAt")})
public class OrganizersCodes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "code")
    private String code;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public OrganizersCodes() {
    }

    public OrganizersCodes(Long id) {
        this.id = id;
    }

    public OrganizersCodes(Long id, String code) {
        this.id = id;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrganizersCodes)) {
            return false;
        }
        OrganizersCodes other = (OrganizersCodes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.OrganizersCodes[ id=" + id + " ]";
    }
    
}
