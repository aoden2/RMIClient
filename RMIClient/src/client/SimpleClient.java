package client;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi_actions.SaveAction;

public class SimpleClient {
	
	public static Long saveObject(Serializable object) {
		
		try {
        	
        	String codeBasePath =  SimpleClient
        			.class.getProtectionDomain()
        			.getCodeSource().getLocation().toString();
            System.setProperty("java.rmi.server.codebase",codeBasePath);
            System.setProperty("java.security.policy", 
            		PolicyLoader.getLocationOfPolicyFile());
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());   
            }
            System.err.println(PolicyLoader.getLocationOfPolicyFile());
        	
        	// get rmi registry on port 1099
            Registry registry = LocateRegistry.getRegistry(null, 1099);
            // now we get the action by its name
            SaveAction stub = (SaveAction) registry.lookup("saveAction");
            // and save object
            return stub.doSave(object);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
            return null;
        }
	}

	/**
	 * for testing purpose
	 * @param args
	 */
	public static void main(String[] args) {

		// we create 10 threads and save concurrently
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
				
					SimpleClient.saveObject("this is test");
				}
			}).start();
		}
	}
}
