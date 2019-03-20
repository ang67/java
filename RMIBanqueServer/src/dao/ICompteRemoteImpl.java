package dao;

import entities.Compte;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import services.ICompteRemote;

/**
 *
 * @author bini
 */
public class ICompteRemoteImpl extends UnicastRemoteObject implements ICompteRemote{
    private final EntityManager em;
    private boolean isSuccess  = false;

    public ICompteRemoteImpl() throws RemoteException{
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RMIBanqueServerPU");
        em = emf.createEntityManager();
    }

    @Override
    public boolean addCompte(Compte cp) throws RemoteException {
         try{
            this.em.getTransaction().begin();
            this.em.persist(cp);
            this.em.getTransaction().commit();
            isSuccess = true;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return isSuccess;
    }

    @Override
    public boolean updateCompte(Compte cp) throws RemoteException {
         try{
            this.em.getTransaction().begin();
            this.em.merge(cp);
            this.em.getTransaction().commit();
            isSuccess = true;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return isSuccess;
    }

    @Override
    public Compte findByCompteId(Integer cptId) throws RemoteException {
        return (Compte) em.createNamedQuery("Compte.findByCompteId").setParameter("compteId", cptId).getSingleResult();
    }

    @Override
    public boolean deleteCompte(Integer cptId) throws RemoteException {
        try{
            this.em.getTransaction().begin();
            this.em.remove(em.find(Compte.class, cptId));
            this.em.getTransaction().commit();
            isSuccess = true;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return isSuccess;
    }

    @Override
    public List<Compte> findAll() throws RemoteException {
        return em.createNamedQuery("Compte.findAll").getResultList();}

    @Override
    public Compte findOneByCompteNumero(String cptno) throws RemoteException {
        return (Compte) em.createNamedQuery("Compte.findByNumeroCompte").setParameter("numeroCompte", cptno).getSingleResult();
    }
}
