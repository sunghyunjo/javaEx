import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class ClientHandle extends Thread {
    private Server server;
    private Socket clientSocket;
    private InputStream inputStream;
    private OutputStream outputStream;

    ClientHandle(Server server, Socket socket) throws IOException {
        this.server = server;
        this.clientSocket = socket;
        this.inputStream = clientSocket.getInputStream();
        this.outputStream = clientSocket.getOutputStream();
    }

    @Override
    public void run() {
        int readCount;
        byte[] buffer = new byte[1024];
        String msg;
        while (true) {
            try {
                while ((readCount = inputStream.read(buffer)) > 0) {
                    msg = new String(buffer, 0, readCount);
                    server.connect(msg, this);
                }
            } catch (IOException e) {
                try {
                    server.disConnectClient(this);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    void end() throws IOException {
        inputStream.close();
        outputStream.close();
        clientSocket.close();
    }

    void sendMsg(String msg) {
        try {
            outputStream.write(msg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}