package org.example.mock;

import org.example.pojo.User;

public class PersonService {
    private PersonDao personDao;

    public PersonService(PersonDao mockDao) {

    }

    public String update (){
        return personDao.update(new User("fankai",11));
    }

}
