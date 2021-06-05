package bt.bitsmartmini.bl;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author JScare
 */
public abstract class DdsBL {

    EntityManager em;

    public DdsBL() {
        Map connect = new HashMap<>();
        connect.put("javax.persistence.jdbc.user", "root");
        connect.put("javax.persistence.jdbc.password", "bitstrybe@21");
        em = Persistence.createEntityManagerFactory("bitsmartminiPU", connect).createEntityManager();
    }

    abstract public int insertData(Object object);

    abstract public int updateData(Object object);

    abstract public int removeData(Object id);
}
