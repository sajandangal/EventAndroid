package com.example.eventscheduler;

import com.example.eventscheduler.api.UsersAPI;
import com.example.eventscheduler.serverresponse.SignUpResponse;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import retrofit2.Call;
import retrofit2.Callback;

import static org.mockito.ArgumentMatchers.any;

public class TestWithMockito {
    @Test
    public void TestWithMockito() {
        UsersAPI loginAPI = Mockito.mock(UsersAPI.class);
        final Call<SignUpResponse> mockedCall = Mockito.mock(Call.class);


        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Callback<SignUpResponse> callback = invocation.getArgument(0, Callback.class);
//
                return null;
            }
        }).when(mockedCall).enqueue(any(Callback.class));
    }
}
