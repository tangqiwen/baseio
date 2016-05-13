package test;

import java.io.IOException;

import com.gifisan.nio.client.ClientSession;
import com.gifisan.nio.client.ClientTCPConnector;
import com.gifisan.nio.client.OnReadFuture;
import com.gifisan.nio.component.future.ReadFuture;

public class TestSessionDisconnect {
	
	
	public static void main(String[] args) throws IOException {


		String serviceName = "TestSessionDisconnectServlet";
		String param = ClientUtil.getParamString();
		
		ClientTCPConnector connector = null;
		try {
			connector = ClientUtil.getClientConnector();
			
			connector.connect();
			
			ClientSession session = connector.getClientSession();
			
			ReadFuture future = session.request(serviceName, param);
			System.out.println(future.getText());
			
			session.listen(serviceName, new OnReadFuture() {
				public void onResponse(ClientSession session, ReadFuture future) {
					System.out.println(future.getText());
				}
			});
			
			
			session.write(serviceName, param);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
//			ThreadUtil.sleep(1000);
//			CloseUtil.close(connector);
		}
		
	
	}
}