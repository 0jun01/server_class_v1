package ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFile {

	public static void main(String[] args) {

		// 준비물
		// 1. 서버 소켓이 필요하다.
		// 2. 포트 번호가 필요하다. ( 0 ~ 65535 까지 존재 )
		// 2.1 잘 알려진 포트 번호 : 주로 시스템 레벨 - 0 ~ 1023까지 사용
		// 2.2 등록 가능하는 포트 : 1024 ~ 49151까지 등록 가능
		// 2.3 동적/사설 포트번호 - 그 외 임시 사용을 위해 할당 된다.

		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(5001);
			System.out.println("서버를 시작 합니다 - 포트번호 : 5001");

			// 클라이언트와 연결된 소켓이 생성 됨
			socket = serverSocket.accept();
			System.out.println(">>> 클라이언트가 연결 하였습니다. <<<");

			// 대상은 소켓이다. ( input stream ) 작업
			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			
			// 1. 클라이언트에서 먼저 보낸 데이터를 읽는다.
			// 실제 데이터를 읽는 행위가 필요 하다.
			String message = reader.readLine();
			System.out.println("클라이언트 측 메세지 전달 받음 : " + message);
			
			// 2. 클라이언트 측으로 데이터를 보낸다.
			// 대상은 소켓이다. ( output stream ) 작업
			//OutputStream outputStream = new OutStream() 과 같다
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true); // auto flush
			writer.println("난 서버디, 클라이언트 반갑디"); // 줄바꿈 포함 메서드 안녕 ---> 안녕\n을 보내는 메서드
			
			
			
			

			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			if(socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (serverSocket != null) {
				try {
					serverSocket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
