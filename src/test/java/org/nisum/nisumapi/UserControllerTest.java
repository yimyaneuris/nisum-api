package org.nisum.nisumapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.nisum.nisumapi.model.Phone;
import org.nisum.nisumapi.model.User;
import org.nisum.nisumapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
public class UserControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;

    @MockBean
    UserService userService;

    User user1 = new User();
    Phone phone1 = new Phone();
    Phone phone2 = new Phone();
}
