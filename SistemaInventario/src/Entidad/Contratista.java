/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kil_5
 */
@Entity
@Table(name = "contratista")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contratista.findAll", query = "SELECT c FROM Contratista c")
    , @NamedQuery(name = "Contratista.findByIdContratista", query = "SELECT c FROM Contratista c WHERE c.contratistaPK.idContratista = :idContratista")
    , @NamedQuery(name = "Contratista.findByNombreContratista", query = "SELECT c FROM Contratista c WHERE c.nombreContratista = :nombreContratista")
    , @NamedQuery(name = "Contratista.findByApellidoContratista", query = "SELECT c FROM Contratista c WHERE c.apellidoContratista = :apellidoContratista")
    , @NamedQuery(name = "Contratista.findByTelefonoContratista", query = "SELECT c FROM Contratista c WHERE c.telefonoContratista = :telefonoContratista")
    , @NamedQuery(name = "Contratista.findByCorreoContratista", query = "SELECT c FROM Contratista c WHERE c.correoContratista = :correoContratista")
    , @NamedQuery(name = "Contratista.findByEmpresaClienteidEmpresaCliente", query = "SELECT c FROM Contratista c WHERE c.contratistaPK.empresaClienteidEmpresaCliente = :empresaClienteidEmpresaCliente")})
public class Contratista implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContratistaPK contratistaPK;
    @Basic(optional = false)
    @Column(name = "nombreContratista")
    private String nombreContratista;
    @Basic(optional = false)
    @Column(name = "apellidoContratista")
    private String apellidoContratista;
    @Basic(optional = false)
    @Column(name = "telefonoContratista")
    private String telefonoContratista;
    @Basic(optional = false)
    @Column(name = "correoContratista")
    private String correoContratista;
    @JoinColumn(name = "EmpresaCliente_idEmpresaCliente", referencedColumnName = "idEmpresaCliente", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Empresacliente empresacliente;

    public Contratista() {
    }

    public Contratista(ContratistaPK contratistaPK) {
        this.contratistaPK = contratistaPK;
    }

    public Contratista(ContratistaPK contratistaPK, String nombreContratista, String apellidoContratista, String telefonoContratista, String correoContratista) {
        this.contratistaPK = contratistaPK;
        this.nombreContratista = nombreContratista;
        this.apellidoContratista = apellidoContratista;
        this.telefonoContratista = telefonoContratista;
        this.correoContratista = correoContratista;
    }

    public Contratista(int idContratista, int empresaClienteidEmpresaCliente) {
        this.contratistaPK = new ContratistaPK(idContratista, empresaClienteidEmpresaCliente);
    }

    public ContratistaPK getContratistaPK() {
        return contratistaPK;
    }

    public void setContratistaPK(ContratistaPK contratistaPK) {
        this.contratistaPK = contratistaPK;
    }

    public String getNombreContratista() {
        return nombreContratista;
    }

    public void setNombreContratista(String nombreContratista) {
        this.nombreContratista = nombreContratista;
    }

    public String getApellidoContratista() {
        return apellidoContratista;
    }

    public void setApellidoContratista(String apellidoContratista) {
        this.apellidoContratista = apellidoContratista;
    }

    public String getTelefonoContratista() {
        return telefonoContratista;
    }

    public void setTelefonoContratista(String telefonoContratista) {
        this.telefonoContratista = telefonoContratista;
    }

    public String getCorreoContratista() {
        return correoContratista;
    }

    public void setCorreoContratista(String correoContratista) {
        this.correoContratista = correoContratista;
    }

    public Empresacliente getEmpresacliente() {
        return empresacliente;
    }

    public void setEmpresacliente(Empresacliente empresacliente) {
        this.empresacliente = empresacliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contratistaPK != null ? contratistaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contratista)) {
            return false;
        }
        Contratista other = (Contratista) object;
        if ((this.contratistaPK == null && other.contratistaPK != null) || (this.contratistaPK != null && !this.contratistaPK.equals(other.contratistaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Contratista[ contratistaPK=" + contratistaPK + " ]";
    }
    
}
