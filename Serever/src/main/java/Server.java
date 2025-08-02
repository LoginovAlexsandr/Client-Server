import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 9595; // Можем выбрать другой порт, если этот занят
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен и ожидает подключения на порту " + port);

            while (true) {
                // Ждем подключения
                Socket clientSocket = serverSocket.accept();
                System.out.println("Новое соединение принято");

                // Создаем потоки для чтения и записи
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                // Читаем строку от клиента
                final String name = in.readLine();
                System.out.println("Получено сообщение: " + name + " с порта: " + clientSocket.getPort());

                // Отправляем ответ клиенту
                out.println(String.format("Привет %s, ваш порт: %d", name, clientSocket.getPort()));

                // Закрываем соединение
                clientSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}