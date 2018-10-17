package ac.knu.service;

import org.springframework.stereotype.Service;
import java.util.StringTokenizer;

@Service
public class CommandParsingService
{
    public CommandParsingService()
    {

    }
    public String parseCommand(String command, SortedArrayList friendList)
    {
        StringTokenizer words = new StringTokenizer(command, " ");
        switch(words.nextToken())
        {
            case "add":
                Friend newFriend = new Friend(words.nextToken(), Integer.parseInt(words.nextToken()), Gender.valueOf(words.nextToken()));
                friendList.insert(newFriend);
                return "add done!";
            case "remove":
                friendList.remove(words.nextToken());
                return "remove done!";
            case "list":
                friendList.print();
                return "list done!";
            case "find":
                if(friendList.search(words.nextToken())) {
                    return "find done!";
                }
                else {
                    return "find fail!";
                }
        }
        return "Not command";
    }
}