package com.example.cst438_project1_group8;

import org.junit.Test;
import static org.junit.Assert.*;


public class CreateUserUnitTest {
    @Test
    public void addNewUser(){
        User test = new User();
        test.username = "johnsmith";
        test.password = "pass123";

        assertEquals(test.username, "johnsmith");
        assertNotEquals(test.username, "admin");
        assertEquals(test.password, "pass123");
        assertNotEquals(test.password, "password");
    }
}
