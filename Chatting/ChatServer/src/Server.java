import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

// 쓰레드와 프로세스 장단점
// 멜티프로세스 장점 :
// 비메모리자원
// Garbage Collection

public class Server extends Thread {
    private ServerSocket serverSocket;
    private ArrayList<ClientHandle> clientList;
    private volatile boolean flag;

    private Server() throws IOException {
        serverSocket = new ServerSocket(8000);
        clientList = new ArrayList<>();
        flag = true;
    }

    public static void main(String[] args) throws IOException {
        // serverSocket 생성 후 예외 처리하여 'main'에서 예외처리 없애기
        // setReuseAddress
        // 동기화...........!!!!!!
        // finalize
        // start 메소드
        // outputStream만 만들면됨
        Server server = new Server();
        server.start();
    }

    void connect(String msg, ClientHandle sendClient) throws IOException {
        if (msg.toLowerCase().equals("exit"))
            disConnectClient(sendClient);
        else {
            for (ClientHandle client : clientList) {
                if (!sendClient.equals(client))
                    client.sendMsg(msg);
            }
        }
    }

    @Override
    public void run() {
        while (flag) {
            try {
                System.out.println("Server 연결됨");
                Socket socket = serverSocket.accept();
                ClientHandle ch = new ClientHandle(this, socket);
                ch.start();
                clientList.add(ch);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    void disConnectClient(ClientHandle clientHandle) throws IOException {
        clientList.remove(clientHandle);
        clientHandle.end();
        System.out.println("Client 접속종료");
        flag = false;
        //강제종료하였을 때도 접속종료가 뜨는지 확인해야함.
    }
}