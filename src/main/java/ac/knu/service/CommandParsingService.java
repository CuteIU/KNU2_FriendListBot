package ac.knu.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CommandParsingService {

    private final TreeMap<String, Friend> friendList = new TreeMap<>();
    private String commandType;

    public String parseCommand(String command) {
        StringTokenizer commandTokens = new StringTokenizer(command, " ");
        commandType = makeCapitalize(commandTokens.nextToken());

        switch (commandType) {
            case "Add": { return addFriend(commandTokens); }
            case "Remove": { return removeFriend(commandTokens); }
            case "List": { return getFriendList(); }
            case "Find": { return findFriend(commandTokens); }
            case "Time": { return printCurrentTime(new Date()); }
            default: { return "Not command"; }
        }
    }

    private String addFriend(StringTokenizer commandTokens) {
        if(isFriendListFull()) {
            return getExceptionMessage(ExceptionType.FullFriendList);
        }
        if(isParameterInsufficient(commandTokens,3)){
            return getExceptionMessage(ExceptionType.InsufficientParameter);
        }

        String name = makeCapitalize(commandTokens.nextToken());
        int age = getAge(commandTokens.nextToken());
        Gender gender = getGender(commandTokens.nextToken());

        return addSuccessOrFailure(new Friend(name, age, gender));
    }

    private String addSuccessOrFailure(Friend newFriend) {
        if(isInvalidAge(newFriend.getAge()) || isInvalidGender(newFriend.getGender())) {
            return getExceptionMessage(ExceptionType.InvalidParameter);
        }
        if(friendList.containsKey(newFriend.getName())) {
            return getExceptionMessage(ExceptionType.DuplicatedFriendName);
        }

        friendList.put(newFriend.getName(), newFriend);
        return getSuccessMessage();
    }

    private boolean isParameterInsufficient(StringTokenizer commandTokens, int necessaryParameterNumber) {
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

    private String makeCapitalize(String nameStr) { return StringUtils.capitalize(nameStr.toLowerCase()); }

    private int getAge(String ageStr) {
        try {
            return Integer.parseInt(ageStr);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private Gender getGender(String genderStr) {
        if (genderStr.equalsIgnoreCase("M") || genderStr.equalsIgnoreCase("Male") || genderStr.equals("남") || genderStr.equals("남성") || genderStr.equals("남자")) {
            return Gender.Male;
        } else if (genderStr.equalsIgnoreCase("F") || genderStr.equalsIgnoreCase("Female") || genderStr.equals("여") || genderStr.equals("여성") || genderStr.equals("여자")) {
            return Gender.Female;
        } else {
            return null;
        }
    }

    private String removeFriend(StringTokenizer commandTokens) {
        if (isParameterInsufficient(commandTokens,1)) {
            return getExceptionMessage(ExceptionType.InsufficientParameter);
        }
        String name = makeCapitalize(commandTokens.nextToken());
        if(!friendList.containsKey((name))) {
            return getExceptionMessage(ExceptionType.FriendNotFound);
        }

        friendList.remove(name);
        return getSuccessMessage();
    }

    private String findFriend(StringTokenizer commandTokens) {
        if (isParameterInsufficient(commandTokens,1)) {
            return getExceptionMessage(ExceptionType.InsufficientParameter);
        }
        String name = makeCapitalize(commandTokens.nextToken());
        if (!friendList.containsKey(name)) {
            return getExceptionMessage(ExceptionType.FriendNotFound);
        }

        Friend friend = friendList.get(name);
        return "Name: " + friend.getName() + "\nAge: " + friend.getAge() + "\nGender: " + friend.getGender();
    }

    private String getFriendList() {
        StringBuilder listFormat = new StringBuilder(String.format("%-20s| %s\t| %s\n", "Name", "Age", "Gender"));
        listFormat.append("------------------------------------\n");
        for (Friend friend : friendList.values()) {
            listFormat.append(friend)
                      .append("\n");
        }
        return listFormat.toString();
    }

    private String printCurrentTime(Date now) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초 입니다.");
        return "Current Time is: " + simpleDateFormat.format(now);
    }

    private String getExceptionMessage(ExceptionType exceptionType) {
        Exception exception = new Exception();
        exception.setExceptionType(exceptionType);
        return commandType + exception;
    }

    private String getSuccessMessage() {
        return commandType + " done!";
    }
}
