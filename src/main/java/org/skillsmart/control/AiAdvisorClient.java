package org.skillsmart.control;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.lang.reflect.Method;

public class AiAdvisorClient {

    private static final String DEMO_PROXY_URL = "http://langchain4j.dev/demo/openai/v1";
    private static final String DEMO_API_KEY = "demo";
    private static final String DEMO_MODEL = "gpt-4o-mini";

    private final ChatLanguageModel model;

    public AiAdvisorClient() {
        this.model = OpenAiChatModel.builder()
                .baseUrl(DEMO_PROXY_URL)
                .apiKey(DEMO_API_KEY)
                .modelName(DEMO_MODEL)
                .build();
    }

    public String askTop3Moves(String prompt) {
        return model.generate(prompt);
    }

    public void shutdown() {
        if (model instanceof AutoCloseable closeable) {
            try {
                closeable.close();
                return;
            } catch (Exception ignored) {
                // Best-effort shutdown, game exit should continue.
            }
        }

        try {
            Method closeMethod = model.getClass().getMethod("close");
            closeMethod.invoke(model);
        } catch (Exception ignored) {
            // Model may not expose a close method in this version.
        }
    }
}
