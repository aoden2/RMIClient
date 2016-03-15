package com.tdt.client;

import com.tdt.rmi_actions.Counter;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SimpleClient {

    public static Counter getStub(String host, int port, String name) {

        try {
            // get rmi registry on port 1099
            Registry registry = LocateRegistry.getRegistry(host, port);
            return (Counter) registry.lookup(name);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
            return null;
        }
    }
}
