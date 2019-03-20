/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bini
 */
@Entity
@Table(name = "operation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Operation.findAll", query = "SELECT o FROM Operation o")
    , @NamedQuery(name = "Operation.findByOperationId", query = "SELECT o FROM Operation o WHERE o.operationId = :operationId")
    , @NamedQuery(name = "Operation.findByTypeOperation", query = "SELECT o FROM Operation o WHERE o.typeOperation = :typeOperation")
    , @NamedQuery(name = "Operation.findByCompteNumero", query = "SELECT o FROM Operation o WHERE o.compteNumero = :compteNumero")
    , @NamedQuery(name = "Operation.findByDateOperation", query = "SELECT o FROM Operation o WHERE o.dateOperation = :dateOperation")})
public class Operation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "operation_id")
    private Integer operationId;
    @Basic(optional = false)
    @Column(name = "type_operation")
    private String typeOperation;
    @Basic(optional = false)
    @Column(name = "compte_numero")
    private String compteNumero;
    @Basic(optional = false)
    @Column(name = "date_operation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOperation;

    public Operation() {
        this.dateOperation = new Date();
    }

    public Operation(Integer operationId) {
        this.operationId = operationId;
    }

    public Operation(Integer operationId, String typeOperation, String compteNumero, Date dateOperation) {
        this.operationId = operationId;
        this.typeOperation = typeOperation;
        this.compteNumero = compteNumero;
        this.dateOperation = dateOperation;
    }

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public String getCompteNumero() {
        return compteNumero;
    }

    public void setCompteNumero(String compteNumero) {
        this.compteNumero = compteNumero;
    }

    public String getDateOperation() {
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy à H:mm:ss");
        return date.format(dateOperation);
        
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (operationId != null ? operationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operation)) {
            return false;
        }
        Operation other = (Operation) object;
        if ((this.operationId == null && other.operationId != null) || (this.operationId != null && !this.operationId.equals(other.operationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Operation[ operationId=" + operationId + " ]";
    }
    
}
