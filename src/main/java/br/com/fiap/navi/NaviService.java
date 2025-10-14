package br.com.fiap.navi;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class NaviService {

    private final ChatClient chatClient;

    public NaviService(ChatClient.Builder buider) {
        this.chatClient = buider.build();
    }

    public String translate(String text, String style) {
        // altere o retornar para char a AI generativa para realizar a tradução de acordo com o estilo
        return text;
    }
}
