package com.tdt.rmi_actions;

import java.math.BigInteger;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Counter extends Remote {

    /**
     * update current status to master
     * @param RSAKey A large Integer number we want to scan
     * @param position latest position that is scanned by a slave
     * @param p result, if not null which is we found 2 prime number m and n which p*q = N
     * @param success indicate that slave have found p and q or not
     * @throws RemoteException
     */
    void update(BigInteger RSAKey, BigInteger position, BigInteger p, boolean success) throws RemoteException;

    /**
     * get Current Position from master
     * @return
     * @throws RemoteException
     */
    BigInteger getCurrentPosition() throws RemoteException;

    /**
     * get result from master
     * @return
     * @throws RemoteException
     */
    BigInteger getResult() throws RemoteException;

    /**
     * get N
     * @return N
     * @throws RemoteException
     */
    BigInteger getGivenN() throws RemoteException;

}
