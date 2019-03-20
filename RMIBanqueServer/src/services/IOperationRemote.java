package services;

import entities.Operation;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author bini
 */
public interface IOperationRemote extends Remote{
    public List<Operation> listOperation(int numero) throws RemoteException;     
    public boolean addOperation(Operation op) throws RemoteException;
    public boolean updateOperation(Operation op) throws RemoteException;
    public boolean deleteOperation(int opId) throws RemoteException;
    public Operation findByOperationId(int opId) throws RemoteException;
    public List<Operation> findByCompteNumero(String cptno) throws RemoteException;
            
}
