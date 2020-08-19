/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author kil_5
 */
@Embeddable
public class ContratistaPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idContratista")
    private int idContratista;
    @Basic(optional = false)
    @Column(name = "EmpresaCliente_idEmpresaCliente")
    private int empresaClienteidEmpresaCliente;

    public ContratistaPK() {
    }

    public ContratistaPK(int idContratista, int empresaClienteidEmpresaCliente) {
        this.idContratista = idContratista;
        this.empresaClienteidEmpresaCliente = empresaClienteidEmpresaCliente;
    }

    public int getIdContratista() {
        return idContratista;
    }

    public void setIdContratista(int idContratista) {
        this.idContratista = idContratista;
    }

    public int getEmpresaClienteidEmpresaCliente() {
        return empresaClienteidEmpresaCliente;
    }

    public void setEmpresaClienteidEmpresaCliente(int empresaClienteidEmpresaCliente) {
        this.empresaClienteidEmpresaCliente = empresaClienteidEmpresaCliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idContratista;
        hash += (int) empresaClienteidEmpresaCliente;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContratistaPK)) {
            return false;
        }
        ContratistaPK other = (ContratistaPK) object;
        if (this.idContratista != other.idContratista) {
            return false;
        }
        if (this.empresaClienteidEmpresaCliente != other.empresaClienteidEmpresaCliente) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.ContratistaPK[ idContratista=" + idContratista + ", empresaClienteidEmpresaCliente=" + empresaClienteidEmpresaCliente + " ]";
    }
    
}
