

package bt.bitsmartmini.bl;



/**
 *
 * @author JScare
 */
public class UomBL extends DdsBL{

    @Override
    public int insertData(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updateData(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    @Override
//    public int removeData(Object id) {
//        em.getTransaction().begin();
//        UomDef i = em.find(UomDef.class, id);
//        em.remove(i);
//        em.getTransaction().commit();
//        return 1;
//    }
//    
//    public Uom getUombyItemId(String val){
//        try{
//        TypedQuery<Uom> q = em.createQuery("SELECT u FROM UomDef u WHERE u.item.itemDesc = :itemCode", UomDef.class);
//        q.setParameter("itemCode", val);
//        return q.getSingleResult();
//        }catch(Exception ex){
//            Logger.getLogger(UomBL.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    } 

    @Override
    public int removeData(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
