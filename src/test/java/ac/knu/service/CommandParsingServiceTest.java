package ac.knu.service;

import org.junit.Test;

public class CommandParsingServiceTest {

    @Test
    public void bot_should_understand_list_command() {
        CommandParsingService commandParsingService = new CommandParsingService();
        commandParsingService.parseCommand("list");
    }
}
