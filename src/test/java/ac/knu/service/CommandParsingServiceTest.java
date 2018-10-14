package ac.knu.service;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertTrue;

public class CommandParsingServiceTest
{
    private CommandParsingService commandParsingService;
    @Before
    public void setUp()
    {
        commandParsingService = new CommandParsingService();
    }
    @Test
    public void bot_should_understand_add_command() {
        String command = commandParsingService.parseCommand("add");
        assertTrue("add done!" == command);
    }
    @Test
    public void bot_should_understand_remove_command() {
        String command = commandParsingService.parseCommand("remove");
        assertTrue("remove done!" == command);
    }
    @Test
    public void bot_should_understand_list_command() {
        String command = commandParsingService.parseCommand("list");
        assertTrue("list done!" == command);
    }
    @Test
    public void bot_should_understand_find_command() {
        String command = commandParsingService.parseCommand("find");
        assertTrue("find done!" == command);
    }
}