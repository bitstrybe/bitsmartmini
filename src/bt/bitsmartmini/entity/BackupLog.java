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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Jscar
 */
@Entity
@Table(name = "backup_log")
@NamedQueries({
    @NamedQuery(name = "BackupLog.findAll", query = "SELECT b FROM BackupLog b"),
    @NamedQuery(name = "BackupLog.findByBackupLogId", query = "SELECT b FROM BackupLog b WHERE b.backupLogId = :backupLogId"),
    @NamedQuery(name = "BackupLog.findByBackupLogStamp", query = "SELECT b FROM BackupLog b WHERE b.backupLogStamp = :backupLogStamp")})
public class BackupLog implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "backup_log_id", nullable = false)
    private Integer backupLogId;
    @Basic(optional = false)
    @Column(name = "backup_log_stamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date backupLogStamp;

    public BackupLog() {
    }

    public BackupLog(Integer backupLogId) {
        this.backupLogId = backupLogId;
    }

    public BackupLog(Integer backupLogId, Date backupLogStamp) {
        this.backupLogId = backupLogId;
        this.backupLogStamp = backupLogStamp;
    }

    public Integer getBackupLogId() {
        return backupLogId;
    }

    public void setBackupLogId(Integer backupLogId) {
        this.backupLogId = backupLogId;
    }

    public Date getBackupLogStamp() {
        return backupLogStamp;
    }

    public void setBackupLogStamp(Date backupLogStamp) {
        this.backupLogStamp = backupLogStamp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (backupLogId != null ? backupLogId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BackupLog)) {
            return false;
        }
        BackupLog other = (BackupLog) object;
        if ((this.backupLogId == null && other.backupLogId != null) || (this.backupLogId != null && !this.backupLogId.equals(other.backupLogId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bt.bitsmartmini.entity.BackupLog[ backupLogId=" + backupLogId + " ]";
    }
    
}
