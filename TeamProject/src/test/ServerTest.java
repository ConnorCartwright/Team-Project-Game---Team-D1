package test;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.Socket;

import network.LobbyServer;

import org.junit.Test;

/**
 * Test the lobby server.
 * @author Anh Pham
 */
public class ServerTest {
	
	@Test
	public void testIDGivenByServer() {
		LobbyServer lobbyServer;
		try {
			lobbyServer = new LobbyServer(7777, "test40");
			new Thread(lobbyServer).start();

			Socket socket = new Socket("localhost", 7777);
			int id1 = socket.getInputStream().read();

			Socket socket2 = new Socket("localhost", 7777);
			int id2 = socket2.getInputStream().read();

			assertTrue(id1 != id2);
			socket.close();
			socket2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
