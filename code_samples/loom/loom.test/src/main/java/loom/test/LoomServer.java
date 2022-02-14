package loom.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.Executors;

public final class LoomServer {

    public static void main(String[] args) {
        System.out.println("HERE");
        try (var serverSocket = new ServerSocket(8080)) {
            try (var pool = Executors.newVirtualThreadPerTaskExecutor()) {
                while (true) {
                    Socket connectionSocket = serverSocket.accept();
                    pool.submit(() -> {
                            InputStream inputToServer = connectionSocket.getInputStream();
                            OutputStream outputFromServer = connectionSocket.getOutputStream();
                            Scanner scanner = new Scanner(inputToServer, StandardCharsets.UTF_8);
                            PrintWriter serverPrintOut = new PrintWriter(new OutputStreamWriter(outputFromServer, "UTF-8"), true);
                            while (scanner.hasNextLine()) {
                                String line = scanner.nextLine();
                                serverPrintOut.println("Echo from <Your Name Here> Server: " + line);
                                if (line.equals("exit")) {
                                    connectionSocket.close();
                                    return null;
                                }
                            }
                            return null;
                        }
                    );
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
