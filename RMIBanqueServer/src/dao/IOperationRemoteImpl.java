package dao;

import entities.Compte;
import entities.Operation;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import services.IOperationRemote;

/**
 *
 * @author bini
 */
public class IOperationRemoteImpl extends UnicastRemoteObject implements IOperationRemote{
    private final EntityManager em;
    private boolean isSuccess  = false;

    public IOperationRemoteImpl() throws RemoteException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RMIBanqueServerPU");
        em = emf.createEntityManager();
    }
    
    @Override
    public List<Operation> listOperation(int numero) throws RemoteException {
        return em.createNamedQuery("Operation.findAll").getResultList();
    }

    @Override
    public boolean addOperation(Operation op) throws RemoteException {
        try{
            this.em.getTransaction().begin();
            this.em.persist(op);
            this.em.getTransaction().commit();
            isSuccess = true;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return isSuccess;
    }

    @Override
    public boolean updateOperation(Operation op) throws RemoteException {
        try{
            this.em.getTransaction().begin();
            this.em.merge(op);
            this.em.getTransaction().commit();
            isSuccess = true;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return isSuccess;
    }

    @Override
    public boolean deleteOperation(int opId) throws RemoteException {
        try{
            this.em.getTransaction().begin();
            this.em.remove(em.find(Operation.class, opId));
            this.em.getTransaction().commit();
            isSuccess = true;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }

    @Override
    public Operation findByOperationId(int opId) throws RemoteException {
        return (Operation) em.createNamedQuery("Operation.findByOperationId")
                .setParameter("operationId", opId).getSingleResult();
    
    }
    
    @Override
    public List<Operation> findByCompteNumero(String cptno) throws RemoteException{
        return em.createNamedQuery("Operation.findByCompteNumero").setParameter("compteNumero", cptno).getResultList();
    }
    
}
