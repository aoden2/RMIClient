package com.tdt.main;

import com.tdt.client.SimpleClient;
import com.tdt.logic.PrimeUtils;
import com.tdt.rmi_actions.Counter;

import java.math.BigInteger;
import java.rmi.RemoteException;

public class Main {

    public static void main(String[] args) throws RemoteException {

        // get stub from server
        Counter counter = SimpleClient.getStub(null, 1099, "counter");
        System.out.println("Calculating with given N is: " + counter.getGivenN());
        // loop until we get the result or no result found at all
        while (counter.getResult() == null || counter.getCurrentPosition().equals(counter.getGivenN())) {
            BigInteger currentPosition = counter.getCurrentPosition();
            execute(counter, currentPosition);
        }
        // print out result when done
        if (counter.getResult() != null) {
            System.out.println("Result is: " + counter.getResult() + ";" + counter.getGivenN().divide(counter.getResult()));
        } else {
            System.out.println("No result found!");
        }
    }

    private static void execute(Counter counter, BigInteger currentPosition) throws RemoteException {

        // each slave only scans for a chunk of 1000 numbers each time
        for (int i = 0; i < 1000; i++) {
            if (currentPosition.compareTo(counter.getGivenN()) >= 0 || counter.getResult() != null) {
                break;
            }
            if (PrimeUtils.isPrimeNumber(currentPosition)) {
                if (counter.getGivenN().mod(currentPosition).equals(BigInteger.ZERO)
                        && PrimeUtils.isPrimeNumber(counter.getGivenN().divide(currentPosition))) {
                    counter.update(counter.getGivenN(), currentPosition, currentPosition, true);
                }
            }
            currentPosition = currentPosition.add(BigInteger.valueOf(1));
        }
        // update to master after chunk scanning is finished
        counter.update(counter.getGivenN(), currentPosition, null, false);
    }
}
