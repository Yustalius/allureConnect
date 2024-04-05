import helpers.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import pages.RegistrationPage;
import static io.qameta.allure.Allure.step;


import static com.codeborne.selenide.Selenide.*;

public class TextBoxTest extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();

    @DisplayName("Проверка заполнения формы с разными параметрами")
    @CsvFileSource(resources = "/parametrs.csv")
    @ParameterizedTest(name = "{0} {1}")
    void demoQAAuthFormTest(
            String userName,
            String userLastName,
            String userEmail,
            String gender,
            String mobileNumber,
            String day,
            String month,
            String year,
            String firstSubject,
            String secondSubject,
            String firstHobby,
            String secondHobby,
            String address,
            String state,
            String city
    ) {

        step("Open registrations form", () -> {registrationPage.openPage();});
        step("Fill form", () -> {
            registrationPage.setFirstName(userName)
                .setLastName(userLastName)
                .setEmail(userEmail)
                .setGender(gender)
                .setMobileNumber(mobileNumber)
                .setBirthDate(day, month, year)
                .setSubject(firstSubject, secondSubject)
                .setHobbies(firstHobby, secondHobby)
                .uploadFile()
                .setAddress(address)
                .setStateAndCity(state, city)
                .submitResults();
        });

        step("Verify results", () -> {registrationPage.verifyResultsModalAppears().
                    verifyResult("Student Name", userName + " " + userLastName).
                    verifyResult("Student Email", userEmail).
                    verifyResult("Gender", gender).
                    verifyResult("Mobile", mobileNumber);});

    }
}
