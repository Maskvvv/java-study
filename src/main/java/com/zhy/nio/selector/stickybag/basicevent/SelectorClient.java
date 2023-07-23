package com.zhy.nio.selector.stickybag.basicevent;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * <p> </p>
 *
 * @author zhouhongyin
 * @since 2023/7/21 13:56
 */
public class SelectorClient {

    public static void main(String[] args) {

        try (SocketChannel channel = SocketChannel.open()) {
            channel.connect(new InetSocketAddress("localhost", 8080));
            Scanner scanner = new Scanner(System.in);

            while (true) {
                if (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.equals("close")) break;
                    channel.write(Charset.defaultCharset().encode(line + "\n"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
