package ac.knu.service;

enum ExceptionType {
    InvalidParameter, InsufficientParameter, FullFriendList, DuplicatedFriendName, FriendNotFound
}

public class Exception {
    private ExceptionType exceptionType;

    public void setExceptionType(ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
    }

    public String toString() {
        String result = " fail!: ";
        switch(exceptionType) {
            case InvalidParameter: { return result + "Invalid parameter"; }
            case InsufficientParameter: { return result + "Insufficient parameters"; }
            case FullFriendList: { return result + "If you want to add a new fiend, Remove friend first"; }
            case DuplicatedFriendName: { return result + "Name duplication"; }
            case FriendNotFound: { return result + "Friend is not exist"; }
            default: { return ""; }
        }
    }
}
