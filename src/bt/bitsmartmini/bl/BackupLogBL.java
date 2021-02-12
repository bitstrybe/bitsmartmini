package bt.bitsmartmini.bl;

import bt.bitsmartmini.entity.BackupLog;
import java.util.Date;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author scarface
 */
public class BackupLogBL extends DdsBL {

    @Override
    public int insertData(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateData(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int removeData(Object id) {
        em.getTransaction().begin();
        BackupLog c = em.find(BackupLog.class, id);
        em.remove(c);
        em.getTransaction().commit();
        return 1;
    }

    public List<BackupLog> findBackups() {
        TypedQuery<BackupLog> q = em.createQuery("SELECT b FROM BackupLog b", BackupLog.class);
        return q.getResultList();
    }

    public Date findBackup(int b) {
        try {
            TypedQuery<Date> q = em.createQuery("SELECT m.backupLogStamp FROM BackupLog m WHERE m.backupLogId = :b", Date.class);
            q.setParameter("b", b);
            return q.getSingleResult();
        } catch (NoResultException ex) {
            return null;

        }
    }

    public Date findLastBackup() {
        try {
            TypedQuery<Date> q = em.createQuery("SELECT MAX(b.backupLogStamp) FROM BackupLog b", Date.class);
            return q.getSingleResult();
        } catch (NoResultException ex) {
            return null;

        }
    }
}
