/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.tss.todoweb.business.utenti;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
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
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author tss
 */
@NamedQueries({
    @NamedQuery(name = Utente.FIND_BY_USER,
            query = "select e from Utente e where e.username= :usr")
    ,
    @NamedQuery(name = Utente.FIND_BY_USER_PWD,
            query = "select e from Utente e where e.username= :usr and e.password= :pwd")
    ,
    @NamedQuery(name = Utente.FIND_BY_TOKEN,
            query = "select e from Utente e where e.token= :token"),})

@Entity
@Table(name = "utente")
public class Utente implements Serializable {

    public static final String FIND_BY_USER = "Utente.FindByUser";
    public static final String FIND_BY_USER_PWD = "Utente.FindByUserPwd";
    public static final String FIND_BY_TOKEN = "Utente.FindByToken";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(unique = true)
    private String username;
    @NotBlank
    private String password;
    private String email;
    
    private String token;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date tokenEnd;
    
    @Version
    private Long version;

    public Utente() {
    }

    public Utente(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getTokenEnd() {
        return tokenEnd;
    }

    public void setTokenEnd(Date tokenEnd) {
        this.tokenEnd = tokenEnd;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Utente other = (Utente) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Utente{" + "id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", token=" + token + ", tokenEnd=" + tokenEnd + '}';
    }

}
