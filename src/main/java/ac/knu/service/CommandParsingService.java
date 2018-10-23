package ac.knu.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CommandParsingService {

    private final TreeMap<String, Friend> friendList = new TreeMap<>();

    public String parseCommand(String command) {
        StringTokenizer commandTokens = new StringTokenizer(command, " ");
        switch (commandTokens.nextToken().toLowerCase()) {
            case "add": { return addFriend(commandTokens); }
            case "remove": { return removeFriend(commandTokens); }
            case "list": { return getFriendList() + "\nList done!"; }
            case "find": { return findFriend(commandTokens); }
            case "time": { return printCurrentTime(new Date()); }
            default: { return "Not command"; }
        }
    }

    private String addFriend(StringTokenizer commandTokens) {
        if(isFriendListFull()) {
            return "Add fail!: If you want to add a new fiend, Remove friend first";
        }
        if(isParameterIsInsufficient(commandTokens,3)){
            return "Add fail!: Insufficient parameters";
        }

        String name = getName(commandTokens.nextToken());
        int age = getAge(commandTokens.nextToken());
        Gender gender = getGender(commandTokens.nextToken());

        return addSuccessOrFailure(new Friend(name, age, gender));
    }

    private String addSuccessOrFailure(Friend newFriend) {
        if(isInvalidAge(newFriend.getAge())) {
            return "Add fail!: Invalid parameter - age";
        }
        if(isInvalidGender(newFriend.getGender())) {
            return "Add fail!: Invalid parameter - gender";
        }
        if(friendList.containsKey(newFriend.getName())) {
            return "Add fail!: Name duplication";
        }

        friendList.put(newFriend.getName(), newFriend);
        return "Add done!";
    }

    private boolean isParameterIsInsufficient(StringTokenizer commandTokens, int necessaryParameterNumber) {
        return commandTokens.countTokens() < necessaryParameterNumber;
    }

    private boolean isFriendListFull() {
        return friendList.size() >= 10;
    }

    private boolean isInvalidAge(int age) {
        return age < 0;
    }

    private boolean isInvalidGender(Gender gender) {
        return gender == null;
    }

    private String getName(String nameStr) { return StringUtils.capitalize(nameStr.toLowerCase()); }

    private int getAge(String ageStr) {
        try {
            return Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private Gender getGender(String genderStr) {
        if (genderStr.equalsIgnoreCase("M") || genderStr.equalsIgnoreCase("Male") || genderStr.equals("남") || genderStr.equals("남성") || genderStr.equals("남자")) {
            return Gender.MALE;
        } else if (genderStr.equalsIgnoreCase("F") || genderStr.equalsIgnoreCase("Female") || genderStr.equals("여") || genderStr.equals("여성") || genderStr.equals("여자")) {
            return Gender.FEMALE;
        } else {
            return null;
        }
    }

    private String removeFriend(StringTokenizer commandTokens) {
        if (isParameterIsInsufficient(commandTokens,1)) {
            return "Remove fail!: Insufficient parameters";
        }
        String name = getName(commandTokens.nextToken());
        if(!friendList.containsKey((name))) {
            return "Remove fail!: Name does not exist";
        }

        friendList.remove(name);
        return "Remove done!";
    }

    private String findFriend(StringTokenizer commandTokens) {
        if (isParameterIsInsufficient(commandTokens,1)) {
            return "Find fail!: Insufficient parameters";
        }
        String name = getName(commandTokens.nextToken());
        if (!friendList.containsKey(name)) {
            return "Find fail!: Friend is not exist";
        }

        Friend friend = friendList.get(name);
        return "Find done!\nName: " + friend.getName() + "\nAge: " + friend.getAge() + "\nGender: " + friend.getGender();
    }

    private String getFriendList() {
        StringBuilder stringBuilder = new StringBuilder(String.format("%-20s| %s\t| %s\n", "Name", "Age", "Gender"));
        stringBuilder.append("------------------------------------\n");
        for (Friend friend : friendList.values()) {
            stringBuilder.append(friend)
                         .append("\n");
        }
        return stringBuilder.toString();
    }

    private String printCurrentTime(Date now) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초 입니다.");
        return "Current Time is: " + simpleDateFormat.format(now);
    }
}
