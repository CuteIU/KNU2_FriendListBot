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
    public void bot_should_word_add_command() {
        String command = commandParsingService.parseCommand("add 김재성 22 M");
        assertTrue("add done!" == command);
    }
    @Test
    public void bot_should_work_remove_command() {
        String command = commandParsingService.parseCommand("remove 김재성");
        assertTrue("remove done!" == command);
    }
    @Test
    public void bot_should_work_list_command() {
        String command = commandParsingService.parseCommand("list");
        assertTrue("list done!" == command);
    }
    @Test
    public void bot_should_work_find_command() {
        String command = commandParsingService.parseCommand("find 김재성");
        assertTrue("find done!" == command);
    }
}