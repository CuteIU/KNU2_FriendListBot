package ac.knu.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class FriendTest {
    private final Friend friend = new Friend("김재성", 22, Gender.MALE);

    @Test
    public void get_Friend_name_by_lombok() {
        assertEquals("김재성", friend.getName());
    }

    @Test
    public void get_Friend_age_by_lombok() { assertEquals(22, friend.getAge()); }

    @Test
    public void get_Friend_gender_by_lombok() {
        assertEquals(Gender.MALE, friend.getGender());
    }

    @Test
    public void toString이_의도한_대로_동작함() {
        assertEquals("김재성                 | " + 22 + "\t| MALE", friend.toString());
    }
}