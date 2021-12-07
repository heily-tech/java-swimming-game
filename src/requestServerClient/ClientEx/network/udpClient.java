package ClientEx.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.StandardProtocolFamily;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;
import java.util.StringTokenizer;

import ClientEx.MainActivity;
import ClientEx.Player;


public class udpClient {
	private DatagramChannel datagramChannel;
	private Charset charset = Charset.forName("UTF-8");
	private SocketAddress socketAddress;
	private SocketAddress tempsocket;
	private Thread receiveThread;
	private Thread remainThread;
	MainActivity main;
	
	public udpClient(MainActivity main) {
		this.main = main;
		try {
			datagramChannel = DatagramChannel.open(StandardProtocolFamily.INET);
			datagramChannel.bind(new InetSocketAddress(5002));
		} catch (IOException e) {}
		//this.socketAddress = new InetSocketAddress("jdeok.iptime.org", 5002); //Relay UDP서버
		//this.tempsocket = new InetSocketAddress("jdeok.iptime.org", 5002);
		this.socketAddress = new InetSocketAddress("127.0.0.1", 5002); //Relay UDP서버
		this.tempsocket = new InetSocketAddress("127.0.0.1", 5002);
		
	}
	
	@SuppressWarnings("deprecation")
	public void reaminStop() {
		remainThread.interrupt();
		remainThread.stop();
	}
	
	public void remainClient() {
		remainThread = new Thread() {
			public void run() {
				setSocketAddress(new InetSocketAddress("127.0.0.1", 5002));
				while(true) {
					
					try {
						if(main.getGameState() == 0) { //방대기실 or Game 중일때 더미패킷을 10초마다 전송하여 UDP세션유지
							String reamin = "#dummy"; //UDP세션유지를 위한 더미패킷
							ByteBuffer byteBuffer = charset.encode(reamin);
							datagramChannel.send(byteBuffer, socketAddress);
							Thread.sleep(10000); //30초 정도 UDP세션을 유지하지만 안전하게 10초마다 더미패킷을 보내기 위해 사용.
						}
					} catch (Exception e) {
						System.out.println("reamin");
						if(datagramChannel.isOpen())
							stopClient();
						return; //스레드 종료
					}
					/**/
				}
			}
		};
		remainThread.start();
	}

	public void stopClient() {
		try {
			String message = "[UDP 연결 끊음]";
			System.out.println(message);

			if(datagramChannel!=null && datagramChannel.isOpen())
				datagramChannel.close();
			
			if(socketAddress!=null)
				socketAddress = null;

		} catch(IOException e) {}
	}
	

	public void receive() {
		receiveThread = new Thread() {
			public void run() {
				while(true) {
					try {
						 ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100);
		                 socketAddress = datagramChannel.receive(byteBuffer);
		                 byteBuffer.flip();
		                 String data = charset.decode(byteBuffer).toString();
		                 System.out.println(data);
		                 
		                 if (data.matches("^@KeyPressed.*")) {
		                	 data = data.substring(11, data.length());
		                	 StringTokenizer infos = new StringTokenizer(data, "#", false);
		                	 
		                	 String player = infos.nextToken(); 
		                	 
		                 } else if(data.matches("^@KeyRelease.*")) {
		                	 data = data.substring(11, data.length());
		                	 StringTokenizer infos = new StringTokenizer(data, "#", false);
		                	 
		                	 String player = infos.nextToken(); 
		                	 
		                 } else if (data.matches("^#reset.*")) { //방나가기 (*소켓리셋 및 정보 초기화*)
		                	 data = data.substring(6, data.length());
		                	 String userID = data;
		                	 setSocketAddress(new InetSocketAddress("127.0.0.1", 5002));
		                 } else if (data.matches("^@GExit")) { //게임중 나가기
		                	 
		                 } 
		                 
		                 if (data.matches("^@p2p/.*")) { //방입장시 Relay 서버에서 받은 상대방의 주소와 포트로 UDP연결 (서로의 정보를 교환)
								data = data.substring(5, data.length());
								StringTokenizer infos = new StringTokenizer(data, ":", false);
								String name = infos.nextToken();
								String pointX = infos.nextToken();
								String pointY = infos.nextToken();
								
								main.setOther(new Player(Integer.parseInt(pointX), Integer.parseInt(pointY), name));
								
								//String mydata="";
								//send(mydata);
		                 } else if(data.matches("^@enterUser/.*")) {
		                	 data = data.substring(10, data.length());
		                	 System.out.println(data);
		                	 //main.setMe(new Player(0, imgY[4]));
		                 } else if (data.matches("^@p1Info.*")) { //P2가 P1의 데이터를 받아와 저장 및 갱신
		                	 data = data.substring(7, data.length());
		                	 StringTokenizer info = new StringTokenizer(data, "#", false);
		                	 String userID = info.nextToken();
		                	 String userIcon = info.nextToken();
		                	 int stage = Integer.parseInt(info.nextToken());
		                	 
		                 } else if (data.matches("^@p2Info.*")) { //P1이 P2의 데이터를 받아와 저장 및 갱신
		                	 data = data.substring(7, data.length());
		                	 StringTokenizer info = new StringTokenizer(data, "#", false);
		                	 String userID = info.nextToken();
		                	 String userIcon = info.nextToken();
		                 }
		                 
					} catch (Exception e) {
						System.out.println("[서버 통신 안됨]");
						System.out.println("receive");
						stopClient();
						break;
					}
				}
			}
		};
		receiveThread.start();
	}
	
	public void send(String data) {
		Thread thread = new Thread() {
			@SuppressWarnings("deprecation")
			public void run() {
				try {
					if(!socketAddress.equals(tempsocket)) {
						ByteBuffer byteBuffer = charset.encode(data);
						datagramChannel.send(byteBuffer, socketAddress);
					} else if(socketAddress.equals(tempsocket) && !data.matches("^@KeyPressed.*") && !data.matches("^@KeyRelease.*") && !data.matches("^@chat.*") && !data.matches("^@Gchat.*")) {
						ByteBuffer byteBuffer = charset.encode(data);
						datagramChannel.send(byteBuffer, socketAddress);
					}
					
					if(data.matches("^#reset.*")) { //방 퇴장할 시 상대방에게 #reset 데이터를 보내고 자신의 소켓도 Relay 서버로 초기화
						receiveThread.stop();
						setSocketAddress(new InetSocketAddress("127.0.0.1", 5002));
						receive();
						
						send(data);
					}
				} catch(Exception e) {
					System.out.println("send");
					System.out.println("[서버 통신 안됨]");
					stopClient();
				}
			}
		};
		thread.start();
	}
	
	public DatagramChannel getDatagramChannel() {
		return datagramChannel;
	}
	
	public void setSocketAddress(SocketAddress socketAddress) {
		this.socketAddress = socketAddress;
	}
}
