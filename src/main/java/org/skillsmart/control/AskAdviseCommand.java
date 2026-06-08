package org.skillsmart.control;

import org.skillsmart.core.GameEngine;

import java.util.List;

public class AskAdviseCommand implements GameCommand {

    private final MoveAdvisor moveAdvisor;
    private final AiAdvisorClient aiAdvisorClient;

    public AskAdviseCommand(GameEngine game) {
        this.moveAdvisor = new MoveAdvisor(game);
        this.aiAdvisorClient = game.getAiAdvisorClient();
    }

    @Override
    public void execute() {
        List<String> availableMoves = moveAdvisor.findAvailableMoves();
        if (availableMoves.isEmpty()) {
            System.out.println("Подходящих ходов не найдено.");
            return;
        }

        System.out.println("Возможные ходы:");
        for (String move : availableMoves) {
            System.out.println("- " + move);
        }

        try {
            String prompt = buildPrompt(availableMoves);
            String response = aiAdvisorClient.askTop3Moves(prompt);
            System.out.println("AI рекомендации:");
            System.out.println(response);
        } catch (Exception ex) {
            System.out.println("Не удалось получить совет от AI: " + ex.getMessage());
        }
    }

    @Override
    public void undo() {
        // Nothing to undo for advisory command.
    }

    private String buildPrompt(List<String> availableMoves) {
        return "Ты помощник для игры три-в-ряд. Дай ТОЛЬКО ТОП-3 хода из списка ниже.\n"
                + "Распредели их строго по ролям: лучший, запасной, рискованный.\n"
                + "Если ходов меньше трех, укажи только доступные роли по порядку.\n"
                + "Список ходов:\n"
                + String.join("\n", availableMoves)
                + "\nОтветь строго в 3 строках максимум, без вступления, в формате:\n"
                + "лучший: <ход> - <короткая причина>\n"
                + "запасной: <ход> - <короткая причина>\n"
                + "рискованный: <ход> - <короткая причина>";
    }
}
