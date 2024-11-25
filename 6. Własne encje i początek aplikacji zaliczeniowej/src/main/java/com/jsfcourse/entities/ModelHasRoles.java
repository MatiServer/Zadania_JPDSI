/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsfcourse.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author matmo
 */
@Entity
@Table(name = "model_has_roles")
@NamedQueries({
    @NamedQuery(name = "ModelHasRoles.findAll", query = "SELECT m FROM ModelHasRoles m"),
    @NamedQuery(name = "ModelHasRoles.findByRoleId", query = "SELECT m FROM ModelHasRoles m WHERE m.modelHasRolesPK.roleId = :roleId"),
    @NamedQuery(name = "ModelHasRoles.findByModelType", query = "SELECT m FROM ModelHasRoles m WHERE m.modelHasRolesPK.modelType = :modelType"),
    @NamedQuery(name = "ModelHasRoles.findByModelId", query = "SELECT m FROM ModelHasRoles m WHERE m.modelHasRolesPK.modelId = :modelId")})
public class ModelHasRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ModelHasRolesPK modelHasRolesPK;
    @JoinColumn(name = "role_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Roles roles;

    public ModelHasRoles() {
    }

    public ModelHasRoles(ModelHasRolesPK modelHasRolesPK) {
        this.modelHasRolesPK = modelHasRolesPK;
    }

    public ModelHasRoles(long roleId, String modelType, long modelId) {
        this.modelHasRolesPK = new ModelHasRolesPK(roleId, modelType, modelId);
    }

    public ModelHasRolesPK getModelHasRolesPK() {
        return modelHasRolesPK;
    }

    public void setModelHasRolesPK(ModelHasRolesPK modelHasRolesPK) {
        this.modelHasRolesPK = modelHasRolesPK;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (modelHasRolesPK != null ? modelHasRolesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModelHasRoles)) {
            return false;
        }
        ModelHasRoles other = (ModelHasRoles) object;
        if ((this.modelHasRolesPK == null && other.modelHasRolesPK != null) || (this.modelHasRolesPK != null && !this.modelHasRolesPK.equals(other.modelHasRolesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.ModelHasRoles[ modelHasRolesPK=" + modelHasRolesPK + " ]";
    }
    
}
