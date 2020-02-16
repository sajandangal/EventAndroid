package com.example.eventscheduler;

import com.example.eventscheduler.bll.LoginBLL;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LoginTest {


    @Test
    public void LoginTest(){
        LoginBLL loginBLL=new LoginBLL("user123","user123");
        boolean result=loginBLL.checkUser();
        assertEquals(false,result);
    }
}
