package ac.knu.service;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CommandParsingServiceTest {
    private CommandParsingService commandParsingService;

    @Before
    public void setUp() {
        commandParsingService = new CommandParsingService();
        commandParsingService.parseCommand("add 김재성 22 M");
    }

    @Test
    public void bot_should_word_add_command() {
        String command = commandParsingService.parseCommand("add Kim 22 M");
        assertEquals("Add done!", command);
    }

    @Test
    public void add_할_때_충분한_정보가_들어가지_않으면_경고_메시지_출력() {
        String command = commandParsingService.parseCommand("add 양동화 23");
        assertEquals("Add fail!: Insufficient parameters", command);
    }

    @Test
    public void add_할_때_나이_자리에_적절하지_않은_값이_들어가면_경고_메시지_출력() {
        String command = commandParsingService.parseCommand("add 양동화 23살 여성");
        assertEquals("Add fail!: Invalid parameter - age", command);
    }

    @Test
    public void add_할_때_성별_자리에_적절하지_않은_값이_들어가면_경고_메시지_출력() {
        String command = commandParsingService.parseCommand("add 양동화 23 중성");
        assertEquals("Add fail!: Invalid parameter - gender", command);
    }

    @Test
    public void 중복된_이름의_친구를_추가하려고_하면_경고_메시지_출력() {
        String command = commandParsingService.parseCommand("add 김재성 22 male");
        assertEquals("Add fail!: Name duplication", command);
    }

    @Test
    public void 대소문자만_차이나는_같은_이름의_친구를_추가하려고_하면_경고_메시지_출력(){
        commandParsingService.parseCommand("add Kim 23 F");
        String command = commandParsingService.parseCommand("add kim 23 F");
        assertEquals("Add fail!: Name duplication", command);
    }

    @Test
    public void bot_should_work_remove_command() {
        String command = commandParsingService.parseCommand("remove 김재성");
        assertEquals("Remove done!", command);
    }

    @Test
    public void remove_할_때_충분한_정보가_들어가지_않으면_경고_메시지_출력() {
        String command = commandParsingService.parseCommand("remove");
        assertEquals("Remove fail!: Insufficient parameters", command);
    }

    @Test
    public void 친구_목록에_없는_친구를_삭제하려고_하면_경고_메시지_출력() {
        String command = commandParsingService.parseCommand("remove 양동화");
        assertEquals("Remove fail!: Name does not exist", command);
    }

    @Test
    public void bot_should_work_list_command() {
        String command = commandParsingService.parseCommand("list");
        assertTrue(command.contains("List done!"));
    }

    @Test
    public void bot_should_sort_list() {
        commandParsingService.parseCommand("add Sam 21 M");
        commandParsingService.parseCommand("add Amy 22 F");
        commandParsingService.parseCommand("add Sophia 23 F");
        String command = commandParsingService.parseCommand("list");
        StringBuilder result = new StringBuilder(String.format("%-20s| %s\t| %s\n", "Name", "Age", "Gender"));
        result.append("------------------------------------\n");
        result.append(String.format("%-20s| %s\t| %s\n", "amy", "22", Gender.FEMALE));
        result.append(String.format("%-20s| %s\t| %s\n", "sam", "21", Gender.MALE));
        result.append(String.format("%-20s| %s\t| %s\n", "sophia", "23", Gender.FEMALE));
        result.append(String.format("%-20s| %s\t| %s\n", "김재성", "22", Gender.MALE));
        assertTrue(command.contains(result.toString()));
    }

    @Test
    public void bot_should_work_find_command() {
        String command = commandParsingService.parseCommand("find 김재성");
        assertTrue(command.contains("Find done!"));
    }

    @Test
    public void 찾지_못했을_때_메시지_출력() {
        String command = commandParsingService.parseCommand("find 양동화");
        assertEquals("Find fail!: Friend is not exist", command);
    }

    @Test
    public void find_할_때_충분한_정보가_들어가지_않으면_경고_메시지_출력() {
        String command = commandParsingService.parseCommand("find");
        assertEquals("Find fail!: Insufficient parameters", command);
    }

    @Test
    public void bot_should_work_time_command() {
        String command = commandParsingService.parseCommand("time");
        assertTrue(command.contains("Current Time is"));
    }

    @Test
    public void 열명_이상의_친구를_추가하면_경고_메시지_출력(){
        commandParsingService.parseCommand("add Sam 21 M");
        commandParsingService.parseCommand("add Amy 22 F");
        commandParsingService.parseCommand("add Sophia 23 F");
        commandParsingService.parseCommand("add Noah 20 M");
        commandParsingService.parseCommand("add Liam 21 F");
        commandParsingService.parseCommand("add John 22 M");
        commandParsingService.parseCommand("add Wendy 23 F");
        commandParsingService.parseCommand("add Ryan 20 M");
        commandParsingService.parseCommand("add Jin 20 M");
        String command = commandParsingService.parseCommand("add Ruby 21 F");
        assertEquals("If you want to add a new fiend, Remove friend first",command);
    }
}
