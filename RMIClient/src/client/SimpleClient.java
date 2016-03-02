package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SimpleClient {

	public static void main(String[] args) {

		//remote machine to connect, this time we use localhost
		String host = "127.0.0.1";
        try {
        	
        	String codeBasePath =  "file:/C:/Users/Yavar/git2/RMIClient/bin";
            System.setProperty("java.rmi.server.codebase",codeBasePath);
            System.setProperty("java.security.policy", 
            		"file:/C:/Users/Yavar/git2/RMIClient/client.policy");
        	
        	// get rmi registry on port 1099(I changed in server to 1099 also)
            Registry registry = LocateRegistry.getRegistry(null, 1099);
            // now we get the action by its name
            SaveAction stub = (SaveAction) registry.lookup("saveAction");
            // and save something
            stub.doSave("this is new message");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
	}
}
