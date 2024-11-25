/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jsfcourse.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author matmo
 */
@Embeddable
public class ModelHasPermissionsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "permission_id")
    private long permissionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "model_type")
    private String modelType;
    @Basic(optional = false)
    @NotNull
    @Column(name = "model_id")
    private long modelId;

    public ModelHasPermissionsPK() {
    }

    public ModelHasPermissionsPK(long permissionId, String modelType, long modelId) {
        this.permissionId = permissionId;
        this.modelType = modelType;
        this.modelId = modelId;
    }

    public long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(long permissionId) {
        this.permissionId = permissionId;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) permissionId;
        hash += (modelType != null ? modelType.hashCode() : 0);
        hash += (int) modelId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ModelHasPermissionsPK)) {
            return false;
        }
        ModelHasPermissionsPK other = (ModelHasPermissionsPK) object;
        if (this.permissionId != other.permissionId) {
            return false;
        }
        if ((this.modelType == null && other.modelType != null) || (this.modelType != null && !this.modelType.equals(other.modelType))) {
            return false;
        }
        if (this.modelId != other.modelId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.jsfcourse.entities.ModelHasPermissionsPK[ permissionId=" + permissionId + ", modelType=" + modelType + ", modelId=" + modelId + " ]";
    }
    
}
