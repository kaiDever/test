package org.example.client;

import junit.framework.TestCase;
import org.example.mock.PersonDao;
import org.example.mock.PersonService;
import org.example.pojo.User;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestTest extends TestCase {

    private PersonDao mockDao;
    private PersonService personService;

    @Before
    public void setUp() {
        //模拟PersonDao对象
        mockDao = mock(PersonDao.class);
        when(mockDao.getPerson(1)).thenReturn(new User("liuDeHua", 1));
        when(mockDao.update(isA(User.class))).thenReturn("problem");

        personService = new PersonService(mockDao);
    }

    @Test
    public void testUpdate() {
//        boolean result = personService.update(1, "new name");
//        User person = mockDao.getPerson(1);
//        assertTrue(Objects.equals(new User("liuDeHua", 1).toString(), person.toString()));
        System.out.println(personService.update());
//        assertTrue("must true", result);
        //验证是否执行过一次getPerson(1)
//        verify(mockDao, times(1)).getPerson(eq(1));
        //验证是否执行过一次update
//        verify(mockDao, times(1)).update(isA(Person.class));
    }
}