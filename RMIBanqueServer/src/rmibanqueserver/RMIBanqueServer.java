/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmibanqueserver;

import dao.ICompteRemoteImpl;
import dao.IOperationRemoteImpl;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import services.ICompteRemote;
import services.IOperationRemote;

/**
 *
 * @author bini
 */
public class RMIBanqueServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        
                      
            try {
                Registry registry = LocateRegistry.createRegistry(1099);
                ICompteRemote icpt = new ICompteRemoteImpl();
                IOperationRemote iop = new IOperationRemoteImpl();
                
                registry.bind("cpt", icpt);
                registry.bind("op", iop);
                System.out.println(icpt.toString() + "\n" + iop.toString());
                System.out.println("Objets distants publiés");
                System.out.println("En attente de requête du client...");
            } catch (Exception ex) {ex.printStackTrace();}
            
    }
    
}
