package services;

import entities.Compte;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author bini
 */
public interface ICompteRemote extends Remote{
    public boolean addCompte(Compte cp) throws RemoteException;
    public boolean updateCompte(Compte cp) throws RemoteException;
    public Compte findByCompteId(Integer cptId) throws RemoteException;
    public Compte findOneByCompteNumero(String cptno) throws RemoteException;
    public List<Compte> findAll() throws RemoteException;
    public boolean deleteCompte(Integer cptId) throws RemoteException;
}
