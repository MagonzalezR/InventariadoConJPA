/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kil_5
 */
@Entity
@Table(name = "empresacliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresacliente.findAll", query = "SELECT e FROM Empresacliente e")
    , @NamedQuery(name = "Empresacliente.findByIdEmpresaCliente", query = "SELECT e FROM Empresacliente e WHERE e.idEmpresaCliente = :idEmpresaCliente")
    , @NamedQuery(name = "Empresacliente.findByNombreEmpresa", query = "SELECT e FROM Empresacliente e WHERE e.nombreEmpresa = :nombreEmpresa")
    , @NamedQuery(name = "Empresacliente.findByNitEmpresa", query = "SELECT e FROM Empresacliente e WHERE e.nitEmpresa = :nitEmpresa")
    , @NamedQuery(name = "Empresacliente.findByDireccionEmpresa", query = "SELECT e FROM Empresacliente e WHERE e.direccionEmpresa = :direccionEmpresa")
    , @NamedQuery(name = "Empresacliente.findByTelefonoEmpresa", query = "SELECT e FROM Empresacliente e WHERE e.telefonoEmpresa = :telefonoEmpresa")})
public class Empresacliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idEmpresaCliente")
    private Integer idEmpresaCliente;
    @Basic(optional = false)
    @Column(name = "nombreEmpresa")
    private String nombreEmpresa;
    @Basic(optional = false)
    @Column(name = "nitEmpresa")
    private String nitEmpresa;
    @Basic(optional = false)
    @Column(name = "direccionEmpresa")
    private String direccionEmpresa;
    @Basic(optional = false)
    @Column(name = "telefonoEmpresa")
    private String telefonoEmpresa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresacliente")
    private List<Contratista> contratistaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empresaClienteidEmpresaCliente")
    private List<Contrato> contratoList;

    public Empresacliente() {
    }

    public Empresacliente(Integer idEmpresaCliente) {
        this.idEmpresaCliente = idEmpresaCliente;
    }

    public Empresacliente(Integer idEmpresaCliente, String nombreEmpresa, String nitEmpresa, String direccionEmpresa, String telefonoEmpresa) {
        this.idEmpresaCliente = idEmpresaCliente;
        this.nombreEmpresa = nombreEmpresa;
        this.nitEmpresa = nitEmpresa;
        this.direccionEmpresa = direccionEmpresa;
        this.telefonoEmpresa = telefonoEmpresa;
    }

    public Integer getIdEmpresaCliente() {
        return idEmpresaCliente;
    }

    public void setIdEmpresaCliente(Integer idEmpresaCliente) {
        this.idEmpresaCliente = idEmpresaCliente;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getNitEmpresa() {
        return nitEmpresa;
    }

    public void setNitEmpresa(String nitEmpresa) {
        this.nitEmpresa = nitEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public String getTelefonoEmpresa() {
        return telefonoEmpresa;
    }

    public void setTelefonoEmpresa(String telefonoEmpresa) {
        this.telefonoEmpresa = telefonoEmpresa;
    }

    @XmlTransient
    public List<Contratista> getContratistaList() {
        return contratistaList;
    }

    public void setContratistaList(List<Contratista> contratistaList) {
        this.contratistaList = contratistaList;
    }

    @XmlTransient
    public List<Contrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpresaCliente != null ? idEmpresaCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresacliente)) {
            return false;
        }
        Empresacliente other = (Empresacliente) object;
        if ((this.idEmpresaCliente == null && other.idEmpresaCliente != null) || (this.idEmpresaCliente != null && !this.idEmpresaCliente.equals(other.idEmpresaCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Empresacliente[ idEmpresaCliente=" + idEmpresaCliente + " ]";
    }
    
}
