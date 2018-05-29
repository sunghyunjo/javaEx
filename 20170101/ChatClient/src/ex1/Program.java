package ex1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

// C/C++
// 1. socket()
// 2. connect()
//  = new Socket(host, port)


public class Program {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            Socket socket = new Socket("localhost", 8082);
            byte[] data = new byte[1024];

            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();

            String line = scanner.next();
            os.write(line.getBytes());

            int len = is.read(data);
            if (len > 0) {
                String result = new String(data);
                System.out.println(result);
            }

        } catch (IOException e) {
            System.out.println("해당 포트번호에 접속할 수 없습니다");
            e.printStackTrace();
        }
    }
}
