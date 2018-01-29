/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package expensereport;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author denis
 */
@Entity
@Table(name = "expensereport", catalog = "expensereports", schema = "")
@NamedQueries({
    @NamedQuery(name = "Expensereport_1.findAll", query = "SELECT e FROM Expensereport_1 e")
    , @NamedQuery(name = "Expensereport_1.findById", query = "SELECT e FROM Expensereport_1 e WHERE e.id = :id")
    , @NamedQuery(name = "Expensereport_1.findByFirstName", query = "SELECT e FROM Expensereport_1 e WHERE e.firstName = :firstName")
    , @NamedQuery(name = "Expensereport_1.findByLastName", query = "SELECT e FROM Expensereport_1 e WHERE e.lastName = :lastName")
    , @NamedQuery(name = "Expensereport_1.findByTechExpense", query = "SELECT e FROM Expensereport_1 e WHERE e.techExpense = :techExpense")
    , @NamedQuery(name = "Expensereport_1.findByFoodExpense", query = "SELECT e FROM Expensereport_1 e WHERE e.foodExpense = :foodExpense")
    , @NamedQuery(name = "Expensereport_1.findByTransportExpense", query = "SELECT e FROM Expensereport_1 e WHERE e.transportExpense = :transportExpense")})
public class Expensereport_1 implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "techExpense")
    private String techExpense;
    @Column(name = "foodExpense")
    private String foodExpense;
    @Column(name = "transportExpense")
    private String transportExpense;

    public Expensereport_1() {
    }

    public Expensereport_1(Short id) {
        this.id = id;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        Short oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        String oldFirstName = this.firstName;
        this.firstName = firstName;
        changeSupport.firePropertyChange("firstName", oldFirstName, firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        String oldLastName = this.lastName;
        this.lastName = lastName;
        changeSupport.firePropertyChange("lastName", oldLastName, lastName);
    }

    public String getTechExpense() {
        return techExpense;
    }

    public void setTechExpense(String techExpense) {
        String oldTechExpense = this.techExpense;
        this.techExpense = techExpense;
        changeSupport.firePropertyChange("techExpense", oldTechExpense, techExpense);
    }

    public String getFoodExpense() {
        return foodExpense;
    }

    public void setFoodExpense(String foodExpense) {
        String oldFoodExpense = this.foodExpense;
        this.foodExpense = foodExpense;
        changeSupport.firePropertyChange("foodExpense", oldFoodExpense, foodExpense);
    }

    public String getTransportExpense() {
        return transportExpense;
    }

    public void setTransportExpense(String transportExpense) {
        String oldTransportExpense = this.transportExpense;
        this.transportExpense = transportExpense;
        changeSupport.firePropertyChange("transportExpense", oldTransportExpense, transportExpense);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Expensereport_1)) {
            return false;
        }
        Expensereport_1 other = (Expensereport_1) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "expensereport.Expensereport_1[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
