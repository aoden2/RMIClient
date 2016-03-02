package client;

import java.io.Serializable;
import java.rmi.Remote;

public interface SaveAction extends Remote {

	void doSave(Serializable object) throws Exception;
}
