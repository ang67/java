/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.ICompteRemote;
import services.IOperationRemote;

/**
 *
 * @author bini
 */
public class MakeOd {
    public  static ICompteRemote compte;
    public static IOperationRemote operation;
    
    public static final int DEFAULT_PORT = 1099;
    public static final String COMPTE_BIND_NAME = "cpt";
    public static final String OPERATION_BIND_NAME = "op";
            
            
    
    
    public MakeOd() {
         
    }
    public static ICompteRemote getCompteInstance() throws Exception{
        try {
            compte = (ICompteRemote) Naming.
                    lookup("rmi://localhost:"+DEFAULT_PORT+"/"+COMPTE_BIND_NAME);
        } catch (Exception e) {
        }
        
        return compte;
    }
    public static IOperationRemote getOperationInstance() throws Exception{
        
        try {
            operation = (IOperationRemote) Naming.
                    lookup("rmi://localhost:"+DEFAULT_PORT+"/"+OPERATION_BIND_NAME);
        } catch (Exception e) {
        } 
        return operation;
    }
}
