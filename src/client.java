import java.io.*;
import java.net.*;
import java.util.*;

public class client {

    public static void main(String[] args) {
        String serverIp = "127.0.0.1";  // IP của server
        int serverPort = 8888;          // Cổng của server

        try {
            Socket socket = new Socket(serverIp, serverPort);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Tạo mảng 
            int[] A = createRandomArray(3);  // N > 100
            StringBuilder data = new StringBuilder();
            for (int num : A) {
                data.append(num).append(" ");
            }
            System.out.println("Gui data: " + data.toString());

            // Gửi mảng tới server
            out.println(data.toString().trim());

            // Nhận kết quả từ server
            String result = in.readLine();
            System.out.println("Ket Qua tinh tong cac phan tu trong mang: " + result);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] createRandomArray(int n) {
        Random rand = new Random();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = rand.nextInt(100) + 1;
        }
        return array;
    }
}
