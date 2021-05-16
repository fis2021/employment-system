package employment.system.services;

import employment.system.Main;
import employment.system.exceptions.UserWithThisEmailAlreadyExistsException;
import employment.system.user.AccountType;
import employment.system.user.User;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

import static org.testfx.assertions.api.Assertions.assertThat;

class UserServiceTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before Class");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After Class");
    }

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-userDatabase";
        Main.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.APPLICATION_HOME_PATH.toFile());
        UserService.initUserDatabase();
        UserService.openUserDatabase();
        //UserService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        UserService.closeDatabase();
        System.out.println("After Each");
    }
    @Test
    @DisplayName("Customer is successfully added to the database")
    void testCustomerIsAddedToDatabase() throws UserWithThisEmailAlreadyExistsException {
        UserService.addUser("EMAIL","FIRST_NAME","LAST_NAME","PASSWORD", AccountType.EMPLOYEE);
        assertThat(UserService.getAllUsers()).isNotEmpty();
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);
        User user = (User) UserService.getAllUsers().get(0);
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo("EMAIL");
        assertThat(user.getFirstName()).isEqualTo("FIRST_NAME");
        assertThat(user.getLastName()).isEqualTo("LAST_NAME");
        assertThat(user.getPassword()).isEqualTo(UserService.encodePassword("PASSWORD","EMAIL"));
        assertThat(user.getAccountType()).isEqualTo(AccountType.EMPLOYEE);
    }
}