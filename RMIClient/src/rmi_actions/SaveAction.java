package rmi_actions;

import java.io.Serializable;
import java.rmi.Remote;

public interface SaveAction extends Remote {

	long doSave(Serializable object) throws Exception;
}
