/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidad;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kil_5
 */
@Entity
@Table(name = "contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c")
    , @NamedQuery(name = "Contrato.findByIdContrato", query = "SELECT c FROM Contrato c WHERE c.idContrato = :idContrato")
    , @NamedQuery(name = "Contrato.findByFechaInicio", query = "SELECT c FROM Contrato c WHERE c.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Contrato.findByFechaFinal", query = "SELECT c FROM Contrato c WHERE c.fechaFinal = :fechaFinal")
    , @NamedQuery(name = "Contrato.findByCostoContrato", query = "SELECT c FROM Contrato c WHERE c.costoContrato = :costoContrato")
    , @NamedQuery(name = "Contrato.findByCostoMensual", query = "SELECT c FROM Contrato c WHERE c.costoMensual = :costoMensual")
    , @NamedQuery(name = "Contrato.findByEmpresa", query = "SELECT c FROM Contrato c WHERE c.empresaClienteidEmpresaCliente = :empresa")})
public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idContrato")
    private Integer idContrato;
    @Basic(optional = false)
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Basic(optional = false)
    @Column(name = "fechaFinal")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Basic(optional = false)
    @Column(name = "costoContrato")
    private int costoContrato;
    @Basic(optional = false)
    @Column(name = "costoMensual")
    private int costoMensual;
    @JoinColumn(name = "EmpresaCliente_idEmpresaCliente", referencedColumnName = "idEmpresaCliente")
    @ManyToOne(optional = false)
    private Empresacliente empresaClienteidEmpresaCliente;
    @OneToMany(mappedBy = "contratoidContrato")
    private List<Mueble> muebleList;

    public Contrato() {
    }

    public Contrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Contrato(Integer idContrato, Date fechaInicio, Date fechaFinal, int costoContrato, int costoMensual) {
        this.idContrato = idContrato;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.costoContrato = costoContrato;
        this.costoMensual = costoMensual;
    }

    public Integer getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(Integer idContrato) {
        this.idContrato = idContrato;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public int getCostoContrato() {
        return costoContrato;
    }

    public void setCostoContrato(int costoContrato) {
        this.costoContrato = costoContrato;
    }

    public int getCostoMensual() {
        return costoMensual;
    }

    public void setCostoMensual(int costoMensual) {
        this.costoMensual = costoMensual;
    }

    public Empresacliente getEmpresaClienteidEmpresaCliente() {
        return empresaClienteidEmpresaCliente;
    }

    public void setEmpresaClienteidEmpresaCliente(Empresacliente empresaClienteidEmpresaCliente) {
        this.empresaClienteidEmpresaCliente = empresaClienteidEmpresaCliente;
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
        hash += (idContrato != null ? idContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.idContrato == null && other.idContrato != null) || (this.idContrato != null && !this.idContrato.equals(other.idContrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        java.sql.Date sqlIni = new java.sql.Date(fechaInicio.getTime());
        java.sql.Date sqlFin = new java.sql.Date(fechaFinal.getTime());
        return "Numero de contrato: " + idContrato + "\n\nEmpresa cliente: "+empresaClienteidEmpresaCliente+
                "\n\nFecha de inicio del contrato: "+sqlIni +"\n\nFecha de finalizacion del contrato: "+sqlFin+
                "\n\nCosto total del contrato: "+ costoContrato + "\n\nCosto mensual: "+costoMensual+
                "\n\nMuebles en el contrato: \n";
    }
    
}
