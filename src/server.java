import java.io.*;
import java.net.*;
import java.util.*;

@SuppressWarnings("unused")
public class server {

    public static void main(String[] args) throws IOException {
        int port = 8888;
        @SuppressWarnings("resource")
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server listening on port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Accepted connection from " + clientSocket.getInetAddress());
            new Thread(new ClientHandler(clientSocket)).start();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            // Nhận dữ liệu từ client
            String data = in.readLine();
            System.out.println("Nhan data: " + data);

            // Xử lý dữ liệu (ví dụ: tính tổng các phần tử trong mảng)
            String[] numbers = data.split("\\s+");
            int sum = 0;
            for (String number : numbers) {
                sum += Integer.parseInt(number);
            }
            System.out.println("Calculated result: " + sum);

            // Gửi kết quả lại cho client
            out.println(sum);

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
