package ac.knu.service;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertTrue;

public class CommandParsingServiceTest
{
    private CommandParsingService commandParsingService;
    private SortedArrayList<Friend> friendList;
    @Before
    public void setUp()
    {
        commandParsingService = new CommandParsingService();
        friendList = new SortedArrayList<>();
    }
    @Test
    public void bot_should_word_add_command() {
        String command = commandParsingService.parseCommand("add 김재성 22 M", friendList);
        assertTrue("add done!" == command);
    }
    @Test
    public void bot_should_work_remove_command() {
        String command = commandParsingService.parseCommand("remove 김재성", friendList);
        assertTrue("remove done!" == command);
    }
    @Test
    public void bot_should_work_list_command() {
        String command = commandParsingService.parseCommand("list", friendList);
        assertTrue("list done!" == command);
    }
    @Test
    public void bot_should_sort_list(){
        commandParsingService.parseCommand("add a 21 M", friendList);
        commandParsingService.parseCommand("add b 21 M", friendList);
        commandParsingService.parseCommand("add c 21 M", friendList);
        String command = commandParsingService.parseCommand("list", friendList);
        assertTrue("a,b,c" == command);
    }
    @Test
    public void bot_should_work_find_command() {
        String command = commandParsingService.parseCommand("find 김재성", friendList);
        assertTrue("find done!" == command);
    }
}