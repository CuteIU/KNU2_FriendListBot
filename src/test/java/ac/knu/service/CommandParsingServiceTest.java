package ac.knu.service;

import org.junit.Test;
import org.junit.Before;

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
        assertTrue(command.equals("Add done!"));
    }

    @Test
    public void add_할_때_충분한_정보가_들어가지_않으면_경고_메시지_출력() {
        String command = commandParsingService.parseCommand("add 양동화 23");
        assertTrue(command.equals("Add fail!: Insufficient parameters"));
    }

    @Test
    public void add_할_때_나이_자리에_적절하지_않은_값이_들어가면_경고_메시지_출력() {
        String command = commandParsingService.parseCommand("add 양동화 23살 여성");
        assertTrue(command.equals("Add fail!: Invalid parameter - age"));
    }

    @Test
    public void add_할_때_성별_자리에_적절하지_않은_값이_들어가면_경고_메시지_출력() {
        String command = commandParsingService.parseCommand("add 양동화 23 중성");
        assertTrue(command.equals("Add fail!: Invalid parameter - gender"));
    }

    @Test
    public void 중복된_이름의_친구를_추가하려고_하면_경고_메시지_출력() {
        String command = commandParsingService.parseCommand("add 김재성 22 male");
        assertTrue(command.equals("Add fail!: Name duplication"));
    }

    @Test
    public void bot_should_work_remove_command() {
        String command = commandParsingService.parseCommand("remove 김재성");
        assertTrue(command.equals("Remove done!"));
    }

    @Test
    public void remove_할_때_충분한_정보가_들어가지_않으면_경고_메시지_출력() {
        String command = commandParsingService.parseCommand("remove");
        assertTrue(command.equals("Remove fail!: Insufficient parameters"));
    }

    @Test
    public void 친구_목록에_없는_친구를_삭제하려고_하면_경고_메시지_출력() {
        String command = commandParsingService.parseCommand("remove 양동화");
        assertTrue(command.equals("Remove fail!: Name does not exist"));
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
        result.append(String.format("%-20s| %s\t| %s\n", "Amy", "22", Gender.FEMALE));
        result.append(String.format("%-20s| %s\t| %s\n", "Sam", "21", Gender.MALE));
        result.append(String.format("%-20s| %s\t| %s\n", "Sophia", "23", Gender.FEMALE));
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
        assertTrue(command.equals("Find fail!: Friend is not exist"));
    }

    @Test
    public void find_할_때_충분한_정보가_들어가지_않으면_경고_메시지_출력() {
        String command = commandParsingService.parseCommand("find");
        assertTrue(command.equals("Find fail!: Insufficient parameters"));
    }

    @Test
    public void bot_should_work_time_command() {
        String command = commandParsingService.parseCommand("time");
        assertTrue(command.contains("Current Time is"));
    }
}