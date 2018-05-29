import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {
    private InputStream inputStream;
    private OutputStream outputStream;
    private Socket socket;
    private volatile boolean flag;
    private static String clientName;

    private Client() throws IOException {
        this.socket = new Socket("127.0.0.1", 8000);
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
        flag = true;
        clientName = null;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Client client = new Client();
        client.start();
        client.name();
        String sendMsg;
        String msg;

        while (true) {
            msg = scanner.nextLine();
            sendMsg = clientName + ":" + msg;
            if (sendMsg.toLowerCase().equals("exit")) {
                client.outputStream.write("exit".getBytes());
                client.disconnect();
                client.exit();
                break;
            } else
                client.outputStream.write(sendMsg.getBytes());
        }
    }

    private void name(){
        System.out.println("사용하실 아이디를 입력해주세요 : ");
        Scanner scanner = new Scanner(System.in);
        clientName = scanner.nextLine();
    }

    private void exit() {
        flag = false;
    }

    private void disconnect() throws IOException {
        outputStream.close();
        inputStream.close();
        socket.close();
    }

    @Override
    public void run() {
        int readCount;
        byte[] buffer = new byte[1024];
        while (flag) {
            try {
                while ((readCount = inputStream.read(buffer)) > 0) {
                    System.out.println(new String(buffer, 0, readCount));
                }
            } catch (IOException e) {
                try {
                    disconnect();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}