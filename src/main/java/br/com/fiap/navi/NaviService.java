package br.com.fiap.navi;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class NaviService {

    private final String systemMessage = """
            Você é um assistente de escrita capaz de adaptar qualquer texto
            para diferentes estilos de linguagem (como formal, informal, poético, cordel, etc).
            Sempre reescreva o texto solicitado mantendo o significado, mas ajustando o tom e vocabulário.
            Se o estilo for "Cordel", use rimas e ritmo típicos da literatura de cordel nordestina.
            Responda apenas com o texto transformado, sem explicações adicionais.
        """;

    private final ChatMemory chatMemory = MessageWindowChatMemory.builder().maxMessages(20).build();

    private final ChatOptions options = ChatOptions.builder()
            .temperature(0.7)
            .presencePenalty(0.8)
            .frequencyPenalty(0.8)
            .build();

    private final ChatClient chatClient;

    public NaviService(ChatClient.Builder builder) {
        this.chatClient = builder
                        .defaultSystem(systemMessage)
                        .defaultOptions(options)
                        .defaultAdvisors(
                                MessageChatMemoryAdvisor.builder(chatMemory).build(),
                                new SimpleLoggerAdvisor()
                        ).build();
    }

    public String translate(String text, String style) {
        String prompt = String.format("Reescreva o texto a seguir no estilo '%s':\n\n%s", style, text);

        return chatClient
                .prompt()
                .user(prompt)
                .call()
                .content();
    }

    public Flux<String> getResponse(String message) {
        return chatClient
                .prompt()
                .user(message)
                .stream()
                .content();
    }
}
