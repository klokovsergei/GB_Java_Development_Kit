package hw1.server;

import hw1.common.IOInterface;
import hw1.common.Processable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BufferDataBase implements IOInterface, Processable {

    private final List<String> chat;
    int lastReadLine;
    private final String urlChatFile = "db_chat_history";

    public BufferDataBase() {
        this.chat = new ArrayList<>();
        lastReadLine = 0;
    }

    @Override
    public String read() {
        return readFewLastLine(chat.size());
    }
    @Override
    public String readFewLastLine(int i) {
        i = i > chat.size() ? chat.size() : i;
        StringBuilder txt = new StringBuilder();
        for (int j = chat.size() - i; j < i; j++) {
            txt.append(chat.get(j));
            txt.append(System.lineSeparator());
        }
        return txt.toString();
    }

    @Override
    public void append(String msg) {
        chat.add(msg);
    }

    @Override
    public void connect() {
        if (chat.size() == 0)
            fillBufferChat();
    }

    @Override
    public void disconnect() {
        flush();
    }
    private void fillBufferChat() {
        try (BufferedReader reader = new BufferedReader(new FileReader(urlChatFile))) {
            while (reader.ready())
                chat.add(reader.readLine());
            lastReadLine = chat.size() - 1;
        } catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void flush() {
        try(FileWriter writer = new FileWriter(urlChatFile,true)) {
            for (int i = lastReadLine + 1; i < chat.size(); i++) {
                writer.write(chat.get(i));
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
}
