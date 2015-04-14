/** * A级 */
package com.meiah.services.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;

import com.meiah.core.util.Base64;


public class ServerUsernamePasswordHandler implements CallbackHandler {

    private Map<String, String> users;

    public ServerUsernamePasswordHandler() {
        users = new HashMap<String, String>();
        users.put("PATROL", "cGF0cm9sQDEzMTQ=");
    }

    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        WSPasswordCallback callback = (WSPasswordCallback) callbacks[0];
        String id = callback.getIdentifier();
        if (users.containsKey(id)) {
            if (!callback.getPassword().equals(users.get(id))) {
                throw new SecurityException("无效密码");
            }
        } else {
            throw new SecurityException("无效用户");
        }
    }
    
    public static void main(String[] args) throws Exception {
		System.out.println(Base64.encode("patrol@1314"));
		System.out.println(Base64.decode("cGF0cm9sQDEzMTQ="));
	}
}
