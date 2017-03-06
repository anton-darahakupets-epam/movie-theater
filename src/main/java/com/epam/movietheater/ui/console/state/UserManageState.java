package com.epam.movietheater.ui.console.state;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import org.springframework.context.ApplicationContext;

import com.epam.movietheater.domain.User;
import com.epam.movietheater.service.UserService;

/**
 * State for managing users
 * 
 * @author Yuriy_Tkach
 */
public class UserManageState extends AbstractDomainObjectManageState<User, UserService> {
	
    public UserManageState(ApplicationContext context) {
        super(context.getBean(UserService.class));
    }

    @Override
    protected int printSubActions(int maxDefaultActions) {
        int index = maxDefaultActions;
        System.out.println(" " + (++index) + ") Find user by e-mail");
        return index - maxDefaultActions;
    }

    @Override
    protected void runSubAction(int action, int maxDefaultActions) {
        int index = action - maxDefaultActions;
        switch (index) {
        case 1:
            findUserByEmail();
            break;
        default:
            System.err.println("Unknown action");
        }
    }

    private void findUserByEmail() {
        String email = readStringInput("Input user e-mail: ");
        User user = service.getUserByEmail(email);
        if (user == null) {
            System.out.println("Not found (searched for " + email + ")");
        } else {
            printObject(user);
        }
    }

    @Override
    protected String getObjectName() {
        return User.class.getSimpleName().toLowerCase(Locale.ROOT);
    }

    @Override
    protected void printObject(User user) {
        System.out.println("[" + user.getId() + "] " + user.getFirstName() + " " + user.getLastName() + ", "
                 + "Birthday:" + user.getBirthday() + ", " + user.getEmail() + ", bought " + user.getTickets().size() + " tickets");
    }

    @Override
    protected User createObject() {
        System.out.println("Adding user");
        String firstName = readStringInput("First name: ");
        String lastName = readStringInput("Last name: ");
        String email = readStringInput("E-mail: ");
        String birthday = null;
        LocalDate birthday2 = null;
        do {
        	try {
        		birthday = readStringInput("Birthday in format YYYY-MM-DD: ");
            	birthday2 = LocalDate.parse(birthday);
            	break;
            } catch (DateTimeParseException e) {
            	System.err.println("Incorrect input date");
            }
        } while (true);
        
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthday(birthday2);

        return user;
    }

}
