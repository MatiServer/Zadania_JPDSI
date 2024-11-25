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
@Table(name = "model_has_permissions")
@NamedQueries({
    @NamedQuery(name = "ModelHasPermissions.findAll", query = "SELECT m FROM ModelHasPermissions m"),
    @NamedQuery(name = "ModelHasPermissions.findByPermissionId", query = "SELECT m FROM ModelHasPermissions m WHERE m.modelHasPermissionsPK.permissionId = :permissionId"),
    @NamedQuery(name = "ModelHasPermissions.findByModelType", query = "SELECT m FROM ModelHasPermissions m WHERE m.modelHasPermissionsPK.modelType = :modelType"),
    @NamedQuery(name = "ModelHasPermissions.findByModelId", query = "SELECT m FROM ModelHasPermissions m WHERE m.modelHasPermissionsPK.modelId = :modelId")})
public class ModelHasPermissions implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ModelHasPermissionsPK modelHasPermissionsPK;
    @JoinColumn(name = "permission_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Permissions permissions;

    public ModelHasPermissions() {
    }

    public ModelHasPermissions(ModelHasPermissionsPK modelHasPermissionsPK) {
        this.modelHasPermissionsPK = modelHasPermissionsPK;
    }

    public ModelHasPermissions(long permissionId, String modelType, long modelId) {
        this.modelHasPermissionsPK = new ModelHasPermissionsPK(permissionId, modelType, modelId);
    }

    public ModelHasPermissionsPK getModelHasPermissionsPK() {
        return modelHasPermissionsPK;
    }

    public void setModelHasPermissionsPK(ModelHasPermissionsPK modelHasPermissionsPK) {
        this.modelHasPermissionsPK = modelHasPermissionsPK;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public void setPermissions(Permissions permissions) {
        this.permissions = permissions;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (modelHasPermissionsPK != null ? modelHasPermissionsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModelHasPermissions)) {
            return false;
        }
        ModelHasPermissions other = (ModelHasPermissions) object;
        if ((this.modelHasPermissionsPK == null && other.modelHasPermissionsPK != null) || (this.modelHasPermissionsPK != null && !this.modelHasPermissionsPK.equals(other.modelHasPermissionsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.ModelHasPermissions[ modelHasPermissionsPK=" + modelHasPermissionsPK + " ]";
    }
    
}
