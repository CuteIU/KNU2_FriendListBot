package ac.knu.service;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CommandParsingService {

    private TreeMap<String, Friend> friendList = new TreeMap<>();

    public String parseCommand(String command) {
        StringTokenizer commandTokens = new StringTokenizer(command, " ");
        switch (commandTokens.nextToken().toLowerCase()) {
            case "add": {
                return addNewFriend(commandTokens);
            }
            case "remove": {
                return removeFriend(commandTokens);
            }
            case "list": {
                return getFriendList() + "\nList done!";
            }
            case "find": {
                return findFriend(commandTokens);
            }
            case "time": {
                return printCurrentTime(new Date());
            }
            default:
                return "Not command";
        }
    }

    private String addNewFriend(StringTokenizer commandTokens) {
        if(friendListFull()){
            return "If you want to add a new fiend, Remove friend first";
        }
        if(parameterIsInsufficient(commandTokens,3)){
            return "Add fail!: Insufficient parameters";
        }

        String name = commandTokens.nextToken().toLowerCase();
        String ageStr = commandTokens.nextToken();
        String genderStr = commandTokens.nextToken();

        int age = getAge(ageStr);
        Gender gender = getGender(genderStr);

        return addSuccessOrFailure(new Friend(name, age, gender));
    }

    private String addSuccessOrFailure(Friend newFriend) {

        if(isInvalidAge(newFriend.getAge()))
            return "Add fail!: Invalid parameter - age";
        if(isInvalidGender(newFriend.getGender()))
            return "Add fail!: Invalid parameter - gender";

        if (friendList.containsKey(newFriend.getName())) {
            return "Add fail!: Name duplication";
        }
        friendList.put(newFriend.getName(), newFriend);
        return "Add done!";
    }

    private boolean isInvalidGender(Gender gender) {
        return gender==null;
    }

    private boolean isInvalidAge(int age) {
        return age<0;
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

    private int getAge(String ageStr) {
        try {
            return Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private String removeFriend(StringTokenizer commandTokens) {
        if (parameterIsInsufficient(commandTokens,1)) {
            return "Remove fail!: Insufficient parameters";
        }
        return removeSuccessOrFailure(commandTokens.nextToken().toLowerCase());
    }

    private String removeSuccessOrFailure(String name) {
        if (!friendList.containsKey(name)) {
            return "Remove fail!: Name does not exist";
        }
        friendList.remove(name);
        return "Remove done!";
    }

    private String findFriend(StringTokenizer commandTokens) {

        if (parameterIsInsufficient(commandTokens,1)) {
            return "Find fail!: Insufficient parameters";
        }
        return findSuccessOrFailure(commandTokens.nextToken().toLowerCase());

    }

    private String findSuccessOrFailure(String name) {
        if (!friendList.containsKey(name)) {
            return "Find fail!: Friend is not exist";
        }
        Friend friend = friendList.get(name);
        return "Find done!\nName: " + friend.getName() + "\nAge: " + friend.getAge() + "\nGender: " + friend.getGender();
    }

    private String printCurrentTime(Date now) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초 입니다.");
        return "Current Time is: " + simpleDateFormat.format(now);
    }

    private boolean parameterIsInsufficient(StringTokenizer commandTokens, int necessaryParameterNumber) {
        return commandTokens.countTokens() < necessaryParameterNumber;
    }

    private boolean friendListFull() {
        return friendList.size() == 10;
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
