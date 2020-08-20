/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "mueble")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mueble.findAll", query = "SELECT m FROM Mueble m")
    , @NamedQuery(name = "Mueble.findByIdMueble", query = "SELECT m FROM Mueble m WHERE m.idMueble = :idMueble")
    , @NamedQuery(name = "Mueble.findByNombreMueble", query = "SELECT m FROM Mueble m WHERE m.nombreMueble = :nombreMueble")
    , @NamedQuery(name = "Mueble.findByTipoMueble", query = "SELECT m FROM Mueble m WHERE m.tipoMueble = :tipoMueble")
    , @NamedQuery(name = "Mueble.findByCostoMueble", query = "SELECT m FROM Mueble m WHERE m.costoMueble = :costoMueble")
    , @NamedQuery(name = "Mueble.findByContrato", query = "SELECT m FROM Mueble m WHERE m.contratoidContrato = :idContrato")
    , @NamedQuery(name = "Mueble.countAllTypes", query = "SELECT count( m) from Mueble m where m.nombreMueble = :tipoMueble and m.inventarioEmpresaidInventarioEmpresa= :num")
    , @NamedQuery(name = "Mueble.getAllTypes", query = "SELECT DISTINCT m.tipoMueble from Mueble m")
    , @NamedQuery(name = "Mueble.getAllMuebles", query = "SELECT DISTINCT m.nombreMueble from Mueble m where m.tipoMueble = :tipoMueble")
    , @NamedQuery(name = "Mueble.getAllInventory", query = "SELECT m from Mueble m where m.nombreMueble = :nombreMueble and m.inventarioEmpresaidInventarioEmpresa = :num")})
public class Mueble implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idMueble")
    private Integer idMueble;
    @Basic(optional = false)
    @Column(name = "nombreMueble")
    private String nombreMueble;
    @Basic(optional = false)
    @Column(name = "tipoMueble")
    private String tipoMueble;
    @Basic(optional = false)
    @Column(name = "costoMueble")
    private int costoMueble;
    @JoinColumn(name = "Contrato_idContrato", referencedColumnName = "idContrato")
    @ManyToOne
    private Contrato contratoidContrato;
    @JoinColumn(name = "InventarioEmpresa_idInventarioEmpresa", referencedColumnName = "idInventarioEmpresa")
    @ManyToOne
    private Inventarioempresa inventarioEmpresaidInventarioEmpresa;

    public Mueble() {
    }

    public Mueble(Integer idMueble) {
        this.idMueble = idMueble;
    }

    public Mueble(Integer idMueble, String nombreMueble, String tipoMueble, int costoMueble) {
        this.idMueble = idMueble;
        this.nombreMueble = nombreMueble;
        this.tipoMueble = tipoMueble;
        this.costoMueble = costoMueble;
    }

    public Integer getIdMueble() {
        return idMueble;
    }

    public void setIdMueble(Integer idMueble) {
        this.idMueble = idMueble;
    }

    public String getNombreMueble() {
        return nombreMueble;
    }

    public void setNombreMueble(String nombreMueble) {
        this.nombreMueble = nombreMueble;
    }

    public String getTipoMueble() {
        return tipoMueble;
    }

    public void setTipoMueble(String tipoMueble) {
        this.tipoMueble = tipoMueble;
    }

    public int getCostoMueble() {
        return costoMueble;
    }

    public void setCostoMueble(int costoMueble) {
        this.costoMueble = costoMueble;
    }

    public Contrato getContratoidContrato() {
        return contratoidContrato;
    }

    public void setContratoidContrato(Contrato contratoidContrato) {
        this.contratoidContrato = contratoidContrato;
    }

    public Inventarioempresa getInventarioEmpresaidInventarioEmpresa() {
        return inventarioEmpresaidInventarioEmpresa;
    }

    public void setInventarioEmpresaidInventarioEmpresa(Inventarioempresa inventarioEmpresaidInventarioEmpresa) {
        this.inventarioEmpresaidInventarioEmpresa = inventarioEmpresaidInventarioEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMueble != null ? idMueble.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mueble)) {
            return false;
        }
        Mueble other = (Mueble) object;
        if ((this.idMueble == null && other.idMueble != null) || (this.idMueble != null && !this.idMueble.equals(other.idMueble))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Mueble[ idMueble=" + idMueble + " ]";
    }
    
}
