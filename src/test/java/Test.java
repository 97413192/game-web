import java.rmi.RemoteException;
import java.util.Map;

import com.game.cache.CacheGameServer;
import com.game.constant.RMIConstant;

import cocl.rmi.GameRMIServer;

public class Test {
	@org.junit.Test
	public void test() throws RemoteException{
		// TODO Auto-generated method stub
				GameRMIServer gameRMIServer = CacheGameServer.getGameServer();
				Map<String,Object> map = (Map<String, Object>) gameRMIServer.exec(RMIConstant.GM_TOOL_FIND_SHARECONFIG);
				System.out.println(map);
	}
}
