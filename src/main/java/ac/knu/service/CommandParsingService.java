package ac.knu.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CommandParsingService {
    private TreeMap<String, Friend> friendList;

    public CommandParsingService() {
        friendList = new TreeMap<>();
    }

    public String parseCommand(String command) {
        StringTokenizer commandTokens = new StringTokenizer(command, " ");
        switch (commandTokens.nextToken().toLowerCase()) {
            case "add": {
                if (commandTokens.countTokens() < 3) {
                    return "Add fail!: Insufficient parameters";
                }
                String name = commandTokens.nextToken();
                String ageStr = commandTokens.nextToken();
                String genderStr = commandTokens.nextToken();
                int age;
                Gender gender;
                try {
                    age = Integer.parseInt(ageStr);
                } catch (NumberFormatException e) {
                    return "Add fail!: Invalid parameter - age";
                }
                if (genderStr.equalsIgnoreCase("M") || genderStr.equalsIgnoreCase("Male") || genderStr.equals("남") || genderStr.equals("남성") || genderStr.equals("남자")) {
                    gender = Gender.MALE;
                } else if (genderStr.equalsIgnoreCase("F") || genderStr.equalsIgnoreCase("Female") || genderStr.equals("여") || genderStr.equals("여성") || genderStr.equals("여자")) {
                    gender = Gender.FEMALE;
                } else {
                    return "Add fail!: Invalid parameter - gender";
                }
                Friend newFriend = new Friend(name, age, gender);
                if (friendList.containsKey(name)) {
                    return "Add fail!: Name duplication";
                }
                friendList.put(name, newFriend);
                return "Add done!";
            }
            case "remove": {
                if (commandTokens.countTokens() < 1) {
                    return "Remove fail!: Insufficient parameters";
                }
                String name = commandTokens.nextToken();
                if (!friendList.containsKey(name)) {
                    return "Remove fail!: Name does not exist";
                }
                friendList.remove(name);
                return "Remove done!";
            }
            case "list": {
                return getFriendList() + "\nList done!";
            }
            case "find": {
                if (commandTokens.countTokens() < 1) {
                    return "Find fail!: Insufficient parameters";
                }
                String name = commandTokens.nextToken();
                if (!friendList.containsKey(name)) {
                    return "Find fail!: Friend is not exist";
                }
                Friend friend = friendList.get(name);
                return "Find done!\nName: " + friend.getName() + "\nAge: " + friend.getAge() + "\nGender: " + friend.getGender();
            }
            case "time": {
                Date now = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초 입니다.");
                return "Current Time is: " + simpleDateFormat.format(now);
            }
            default:
                return "Not command";
        }
    }

    public String getFriendList() {
        StringBuilder stringBuilder = new StringBuilder(String.format("%-20s| %s\t| %s\n", "Name", "Age", "Gender"));
        stringBuilder.append("------------------------------------\n");
        for (Friend friend : friendList.values()) {
            stringBuilder.append(friend + "\n");
        }
        return stringBuilder.toString();
    }
}