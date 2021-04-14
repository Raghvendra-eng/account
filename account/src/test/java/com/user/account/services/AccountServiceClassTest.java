package com.user.account.services;

import com.user.account.entity.User;
import com.user.account.repositories.UserRepository;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)

public class AccountServiceClassTest {

    @Mock
    private UserRepository userRepository;

    @Autowired
    private AccountService accountService;

    @Test
    public void updateUserTest() throws Exception{
        User inputUser = new User(2, 1200, "Shyam");
        Mockito.when(userRepository.save(inputUser)).thenReturn(inputUser);
        assertEquals(inputUser, accountService.updateUser(inputUser));
    }

    @Test
    public void getUserTest() throws Exception{
        User inputUser = new User(2, 1200, "Shyam");
        Mockito.when(userRepository.getOne(2L)).thenReturn(inputUser);
        assertEquals(inputUser, accountService.getUser(2L));
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(userRepository);
    }
}
