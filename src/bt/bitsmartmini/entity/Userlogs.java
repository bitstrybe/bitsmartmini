/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bt.bitsmartmini.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author scarface
 */
@Entity
@Table(name = "userlogs")
@NamedQueries({
    @NamedQuery(name = "Userlogs.findAll", query = "SELECT u FROM Userlogs u")
    , @NamedQuery(name = "Userlogs.findByLogsid", query = "SELECT u FROM Userlogs u WHERE u.logsid = :logsid")
    , @NamedQuery(name = "Userlogs.findByLoginDatetime", query = "SELECT u FROM Userlogs u WHERE u.loginDatetime = :loginDatetime")
    , @NamedQuery(name = "Userlogs.findByLogoutDatetime", query = "SELECT u FROM Userlogs u WHERE u.logoutDatetime = :logoutDatetime")})
public class Userlogs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "logsid", nullable = false)
    private Integer logsid;
    @Column(name = "login_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loginDatetime;
    @Column(name = "logout_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logoutDatetime;
    @JoinColumn(name = "username", referencedColumnName = "userid", nullable = false)
    @ManyToOne(optional = false)
    private Users username;

    public Userlogs() {
    }

    public Userlogs(Integer logsid) {
        this.logsid = logsid;
    }

    public Integer getLogsid() {
        return logsid;
    }

    public void setLogsid(Integer logsid) {
        this.logsid = logsid;
    }

    public Date getLoginDatetime() {
        return loginDatetime;
    }

    public void setLoginDatetime(Date loginDatetime) {
        this.loginDatetime = loginDatetime;
    }

    public Date getLogoutDatetime() {
        return logoutDatetime;
    }

    public void setLogoutDatetime(Date logoutDatetime) {
        this.logoutDatetime = logoutDatetime;
    }

    public Users getUsername() {
        return username;
    }

    public void setUsername(Users username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logsid != null ? logsid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Userlogs)) {
            return false;
        }
        Userlogs other = (Userlogs) object;
        if ((this.logsid == null && other.logsid != null) || (this.logsid != null && !this.logsid.equals(other.logsid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bt.bitsmartmini.entity.Userlogs[ logsid=" + logsid + " ]";
    }
    
}
