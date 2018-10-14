package ac.knu.service;

import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommandParsingService
{
    public CommandParsingService()
    {

    }
    public String parseCommand(String command)
    {
        String[] words = command.split(" ");
        if(stringLine[1].equalsIgnoreCase("time"))
        {
            return "(JSK)Current time is " + new Date();
        }
        String result = "";
        for (int i = 0; i < commandList.size(); i++) {
            result += commandList.get(i) + ",";
        }
        result = result.substring(0, result.lastIndexOf(","));

        return result;
    }
}
