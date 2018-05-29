package ex1;

// Socket Programming
//  => TCP/IP, BSD Unix
//  => OSI

// Endian: CPU가 메모리에 데이터를 저장하고 불러오는 방식
// => Little Endian
// => Big Endian (Network)

// Complex/Reduced ISC
// x86/x64 => Intel/AMD(CISC)                   => Little
// ARM     => 엑시노스/스냅드래곤/테그라/A10x(RISC)    => Little / Big

// IP Address   : 컴퓨터의 주소
// Port Address : 프로세스의 주소
//  Well-Known Port
//  HTTP: 80
//  HTTPS: 443
//  FTP: 21
//  SSH: 22

// TCP: Stream
// UDP: Datagram


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

// Port : 2 byte => 65535

// C/C++ Server
// socket()
// bind()
// listen()
//  = new ServerSocket(port);

// accept()

public class Program {
    public static void main(String[] args) {
        try {
            ServerSocket socket = new ServerSocket(8082);
            socket.setReuseAddress(true);

            // Iteration Server : 반복적으로 클라이언트의 요청을 처리하도록 설계되어야 한다.
            byte[] data = new byte[1024];
            while (true) {
                Socket clientSocket = socket.accept();

                OutputStream os = null;
                InputStream is = clientSocket.getInputStream();

                int len = is.read(data);
                if (len > 0) {
                    os = clientSocket.getOutputStream();
                    os.write(data);
                }

                if (os != null) {
                    os.close();
                }

                clientSocket.close();
            }
        } catch (IOException e) {
            System.out.println("다음의 포트번호에 접속할 수 없습니다");
            e.printStackTrace();
        }
    }
}






