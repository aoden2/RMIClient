package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SimpleClient {

	public static void main(String[] args) {

		String host = "127.0.0.1";
        try {
            Registry registry = LocateRegistry.getRegistry(null, 1099);
            SaveAction stub = (SaveAction) registry.lookup("saveAction");
            stub.doSave("this is new message");
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
	}
}
