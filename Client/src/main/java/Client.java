import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String host = "localhost"; // Или "127.0.0.1"
        int port = 9595; // Используем тот же порт, что и на сервере

        try (Socket socket = new Socket(host, port)) {
            // Создаем потоки для чтения и записи
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Отправляем сообщение на сервер
            String message = "Привет, Александр!";
            out.println(message);

            // Читаем ответ от сервера
            String response = in.readLine();
            System.out.println("Ответ от сервера: " + response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}