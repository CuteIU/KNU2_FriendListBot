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
    public void When_add_there_is_not_enough_information_alert_warning_message() {
        String command = commandParsingService.parseCommand("add 양동화 23");
        assertEquals("Add fail!: Insufficient parameters", command);
    }

    @Test
    public void When_add_there_is_wrong_input_alert_warning_message() {
        String command = commandParsingService.parseCommand("add 양동화 23살 여성");
        assertEquals("Add fail!: Invalid parameter", command);
    }

    @Test
    public void When_add_there_is_wrong_Gender_input_alert_warning_message() {
        String command = commandParsingService.parseCommand("add 양동화 23 중성");
        assertEquals("Add fail!: Invalid parameter", command);
    }

    @Test
    public void When_try_add_same_name_alert_waring_message() {
        String command = commandParsingService.parseCommand("add 김재성 22 male");
        assertEquals("Add fail!: Name duplication", command);
    }

    @Test
    public void When_try_add_same_name_but_upper_case_lower_case_difference_alert_warning_message() {
        commandParsingService.parseCommand("add Kim 23 F");
        String command = commandParsingService.parseCommand("add KIM 23 F");
        assertEquals("Add fail!: Name duplication", command);
    }

    @Test
    public void Try_add_friends_more_than_10_alert_warning_message() {
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
        assertEquals("Add fail!: If you want to add a new fiend, Remove friend first", command);
    }

    @Test
    public void Bot_should_work_remove_command() {
        String command = commandParsingService.parseCommand("remove 김재성");
        assertEquals("Remove done!", command);
    }

    @Test
    public void When_remove_there_is_not_enough_information_alert_warning_message() {
        String command = commandParsingService.parseCommand("remove");
        assertEquals("Remove fail!: Insufficient parameters", command);
    }

    @Test
    public void Try_remove_not_exist_friend_alert_warning_message() {
        String command = commandParsingService.parseCommand("remove 양동화");
        assertEquals("Remove fail!: Friend is not exist", command);
    }

    @Test
    public void Bot_should_work_list_command() {
        String command = commandParsingService.parseCommand("list");
        StringBuilder result = new StringBuilder(String.format("%-20s| %s\t| %s\n", "Name", "Age", "Gender"));
        result.append("------------------------------------\n");
        result.append(String.format("%-20s| %s\t| %s\n", "김재성", "22", Gender.Male));
        assertEquals(command, result.toString());
    }

    @Test
    public void Bot_should_sort_list() {
        commandParsingService.parseCommand("add Sam 21 M");
        commandParsingService.parseCommand("add Amy 22 F");
        commandParsingService.parseCommand("add Sophia 23 F");
        String command = commandParsingService.parseCommand("list");
        StringBuilder result = new StringBuilder(String.format("%-20s| %s\t| %s\n", "Name", "Age", "Gender"));
        result.append("------------------------------------\n");
        result.append(String.format("%-20s| %s\t| %s\n", "Amy", "22", Gender.Female));
        result.append(String.format("%-20s| %s\t| %s\n", "Sam", "21", Gender.Male));
        result.append(String.format("%-20s| %s\t| %s\n", "Sophia", "23", Gender.Female));
        result.append(String.format("%-20s| %s\t| %s\n", "김재성", "22", Gender.Male));
        assertTrue(command.contains(result.toString()));
    }

    @Test
    public void Bot_should_work_find_command() {
        String command = commandParsingService.parseCommand("find 김재성");
        assertTrue(command.contains("김재성"));
    }

    @Test
    public void When_bot_could_not_find_name() {
        String command = commandParsingService.parseCommand("find 양동화");
        assertEquals("Find fail!: Friend is not exist", command);
    }

    @Test
    public void When_find_there_in_not_enough_information_alert_warning_message() {
        String command = commandParsingService.parseCommand("find");
        assertEquals("Find fail!: Insufficient parameters", command);
    }

    @Test
    public void Find_should_work_ignores_case() {
        commandParsingService.parseCommand("add kim 22 M");
        String command = commandParsingService.parseCommand("find KIM");
        assertTrue(command.contains("Kim"));
    }

    @Test
    public void Bot_should_work_time_command() {
        String command = commandParsingService.parseCommand("time");
        assertTrue(command.contains("Current Time is"));
    }
}
