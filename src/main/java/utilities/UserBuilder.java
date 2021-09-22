package utilities;

import Model.User;

public class UserBuilder {

    public static User buildUser() {
        return new User("Authorized User",
                "authorized@qa.com.br",
                "12test",
                "true");
    }
}
