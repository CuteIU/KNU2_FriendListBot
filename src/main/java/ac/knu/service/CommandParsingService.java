package ac.knu.service;

import org.springframework.stereotype.Service;
import java.util.StringTokenizer;

@Service
public class CommandParsingService
{
    public CommandParsingService()
    {

    }
    public String parseCommand(String command)
    {
        StringTokenizer words = new StringTokenizer(command, " ");
        switch(words.nextToken())
        {
            case "add":
                return "add done!";
            case "remove":
                return "remove done!";
            case "list":
                return "list done!";
            case "find":
                return "find done!";
        }
        return "Not command";
    }
}