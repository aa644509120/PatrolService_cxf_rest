/** * A级 */
package com.meiah.test;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

public class ClientHandler implements CallbackHandler {
	public void handle(Callback[] callbacks) throws IOException,
		UnsupportedCallbackException {
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];    
        int usage = pc.getUsage();    
        if (usage == WSPasswordCallback.USERNAME_TOKEN) {    
                pc.setPassword("cGF0cm9sQDEzMTQ=");    
        } 
	}
}
