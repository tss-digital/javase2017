/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business.note;

import it.tss.todoweb.business.DateUtils;
import it.tss.todoweb.business.utenti.Utente;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tss
 */
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = ToDo.FIND_ALL,
            query = "select e from ToDo e where e.utente.id= :id_utente order by e.il desc")
    ,
    @NamedQuery(name = ToDo.FIND_BY_DATE,
            query = "select e from ToDo e where e.il= :p_data order by e.titolo")
    ,
    @NamedQuery(name = ToDo.FIND_BY_WORD,
            query = "select e from ToDo e where e.titolo like :word or e.testo like :word order by e.titolo"),})
@Entity
@Table(name = "ToDo")
public class ToDo implements Serializable {

    public static final String FIND_ALL = "Todo.findAll";
    public static final String FIND_BY_DATE = "Todo.findByDate";
    public static final String FIND_BY_WORD = "Todo.findByWord";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @NotBlank
    private String titolo;

    @Basic(optional = false)
    @Column(length = 1024)
    private String testo;

    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date il;

    @Temporal(TemporalType.DATE)
    private Date scadenza;

    @Version
    private Long version;

    @XmlTransient
    @ManyToOne
    private Utente utente;

    public ToDo() {
    }

    public ToDo(String titolo, String testo, Date il) {
        this.titolo = titolo;
        this.testo = testo;
        this.il = il;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Date getIl() {
        return il;
    }

    public String getIlToString() {
        return DateUtils.dateToString(il);
    }

    public void setIl(Date il) {
        this.il = il;
    }

    public Date getScadenza() {
        return scadenza;
    }

    public void setScadenza(Date scadenza) {
        this.scadenza = scadenza;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ToDo other = (ToDo) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {

        return id + "\t-\t"
                + titolo + "\t-\t"
                + testo + "\t-\t"
                + il + "\t\t"
                + scadenza;
    }

}
