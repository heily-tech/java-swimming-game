package server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.StandardProtocolFamily;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ClientEx.MainActivity;
import ClientEx.Player;

public class udpServer {
	private ExecutorService executorService; //스레드풀인 ExecutorService 필드를 선언
	private DatagramChannel datagramChannel;
	private SocketAddress inet = new InetSocketAddress("127.0.0.1", 5002);
	private DatagramSocket ds;
	private Charset charset = Charset.forName("UTF-8");
	int[] lanes = {5, 105, 198, 290, 385, 480, 573, 668};
	ArrayList<Player> players;
	
	udpServer() {
		players = new ArrayList<>();
	}
	
	int settingLane()
	{
		int ran = (int)((Math.random()*10000)%8);
		
		while(lanes[ran] == -1) {
			ran = (int)((Math.random()*10000)%8);
		}
		
		return ran;
	}
	
	void startServer() { //서버 시작 코드
		executorService = Executors.newFixedThreadPool(1000); //CPU 코어 수에 맞게 스레드를 생성해서 관리하는 ExcutorService를 생성
		
		try {
			datagramChannel = DatagramChannel.open(StandardProtocolFamily.INET);
		    datagramChannel.bind(inet);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("[UDP]이미 사용되고 있는 포트번호 입니다."); //해당 포트를 이미 다른 프로그램에서 사용하고 있을때.
			if(datagramChannel.isOpen())
				stopServer(); //서버종료
			return; //startServer() 메소드 종료
		}
		
		//ServerSocketChannel은 반복해서 클라이언트 연결 요청을 기다려야 하므로 스레드풀의 작업스레드상에서 accept() 메소드를 반복적으로 호출해주어야한다.
		Runnable runnable = new Runnable() { //연결 수락 작업을 Runnable로 정의
			public void run() { 
				System.out.println("[UDP 서버시작]");
				
				while (true) {
					try {
	                	System.out.println("[UDP 수신 시작]");
	                    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(100);
	                    SocketAddress socketAddress = datagramChannel.receive(byteBuffer);
	                    InetSocketAddress in = (InetSocketAddress) socketAddress;
	                    byteBuffer.flip();
	                    String data = charset.decode(byteBuffer).toString();
	                    
	                    String message = "[주소]: " + socketAddress.toString() + "\n[내용]: " + data;
	                    System.out.println(message);
	                    
	                    
	                    if(data.matches("^@udpEnter.*")) {
	                    	data = data.substring(9, data.length());
	                    	String id = data;
	                    	int r = settingLane();
	                    	int lane = lanes[r];
	                    	lanes[r] = -1;
	                    	//players.add(new Player(0, lane, id));
	                    	String msg = "@enterUser/" + data;
	                    	System.out.println(msg);
	                    	udpSend(msg, new InetSocketAddress("localhost", 5002));
	                    } else {
	                    	System.out.println("아무것도 없음");
	                    }
					} catch(Exception e) {
						System.out.println(e);
						if(datagramChannel.isOpen()) //예외 발생시 serverSocketChannel이 열려 있는지 확인한다.
							stopServer(); //stopServer()호출
						break; //무한 루프를 종료시킨다.
					}
                }
				
			};
		};
		executorService.submit(runnable); //연결 수락 작업을 스레드풀에서 처리하기 위해 excutorService의 submit()을 호출한다.
	}
	
	void sendAll(String data)
	{
	}
	
	void udpSend(String data, InetSocketAddress address) { //데이터 전송 코드
		Runnable runnable = new Runnable() { //데이터를 클라이언트로 보내는 작업을 Runnable로 생성한다.
			public void run() {
					try {
						ByteBuffer byteBuffer = charset.encode(data); //매개값으로 받은 data 문자열로부터 UTF-8로 인코딩한 ByteBuffer를 얻는다.
						datagramChannel.send(byteBuffer, address);
						System.out.println("전송완료");
					} catch (IOException e) {
						System.out.println("[UDP 전송 오류]");
					}

			}
		};
		executorService.submit(runnable); //스레드풀에서 처리하기 위해 submit()을 호출한다.
	}
	
	void stopServer() { //서버 종료 코드
		try {			
			if(datagramChannel!=null && datagramChannel.isOpen()) //serverSocketChannel이 null이 아니고 열려있으면
				datagramChannel.close(); //serverSocketChannel을 닫는다.
			
			if(executorService!=null && !executorService.isShutdown()) //excutorService가 null이 아니고 종료상태가 아니면 
				executorService.shutdown(); //excutorService를 종료한다.
			
			System.out.println("[UDP 서버 멈춤]");
			//System.out.println("udpStart");
		} catch (Exception e) {}
	}
}
