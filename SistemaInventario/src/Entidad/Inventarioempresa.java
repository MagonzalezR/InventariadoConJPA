/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
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
@Table(name = "inventarioempresa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inventarioempresa.findAll", query = "SELECT i FROM Inventarioempresa i")
    , @NamedQuery(name = "Inventarioempresa.findByIdInventarioEmpresa", query = "SELECT i FROM Inventarioempresa i WHERE i.idInventarioEmpresa = :idInventarioEmpresa")
    , @NamedQuery(name = "Inventarioempresa.findByCantidadAlquilada", query = "SELECT i FROM Inventarioempresa i WHERE i.cantidadAlquilada = :cantidadAlquilada")
    , @NamedQuery(name = "Inventarioempresa.findByDisponibleEnBodega", query = "SELECT i FROM Inventarioempresa i WHERE i.disponibleEnBodega = :disponibleEnBodega")
    , @NamedQuery(name = "Inventarioempresa.findByTotalExistencias", query = "SELECT i FROM Inventarioempresa i WHERE i.totalExistencias = :totalExistencias")})
public class Inventarioempresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idInventarioEmpresa")
    private Integer idInventarioEmpresa;
    @Basic(optional = false)
    @Column(name = "cantidadAlquilada")
    private int cantidadAlquilada;
    @Basic(optional = false)
    @Column(name = "disponibleEnBodega")
    private int disponibleEnBodega;
    @Basic(optional = false)
    @Column(name = "totalExistencias")
    private int totalExistencias;
    @OneToMany(mappedBy = "inventarioEmpresaidInventarioEmpresa")
    private List<Mueble> muebleList;

    public Inventarioempresa() {
    }

    public Inventarioempresa(Integer idInventarioEmpresa) {
        this.idInventarioEmpresa = idInventarioEmpresa;
    }

    public Inventarioempresa(Integer idInventarioEmpresa, int cantidadAlquilada, int disponibleEnBodega, int totalExistencias) {
        this.idInventarioEmpresa = idInventarioEmpresa;
        this.cantidadAlquilada = cantidadAlquilada;
        this.disponibleEnBodega = disponibleEnBodega;
        this.totalExistencias = totalExistencias;
    }

    public Integer getIdInventarioEmpresa() {
        return idInventarioEmpresa;
    }

    public void setIdInventarioEmpresa(Integer idInventarioEmpresa) {
        this.idInventarioEmpresa = idInventarioEmpresa;
    }

    public int getCantidadAlquilada() {
        return cantidadAlquilada;
    }

    public void setCantidadAlquilada(int cantidadAlquilada) {
        this.cantidadAlquilada = cantidadAlquilada;
    }

    public int getDisponibleEnBodega() {
        return disponibleEnBodega;
    }

    public void setDisponibleEnBodega(int disponibleEnBodega) {
        this.disponibleEnBodega = disponibleEnBodega;
    }

    public int getTotalExistencias() {
        return totalExistencias;
    }

    public void setTotalExistencias(int totalExistencias) {
        this.totalExistencias = totalExistencias;
    }

    @XmlTransient
    public List<Mueble> getMuebleList() {
        return muebleList;
    }

    public void setMuebleList(List<Mueble> muebleList) {
        this.muebleList = muebleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idInventarioEmpresa != null ? idInventarioEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventarioempresa)) {
            return false;
        }
        Inventarioempresa other = (Inventarioempresa) object;
        if ((this.idInventarioEmpresa == null && other.idInventarioEmpresa != null) || (this.idInventarioEmpresa != null && !this.idInventarioEmpresa.equals(other.idInventarioEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidad.Inventarioempresa[ idInventarioEmpresa=" + idInventarioEmpresa + " ]";
    }
    
}
