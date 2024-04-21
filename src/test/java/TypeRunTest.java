import com.codeborne.selenide.SelenideElement;
import helpers.TestBase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Tag("smoke")
public class TypeRunTest extends TestBase {

    @DisplayName("Проверка печатания")
    @Test
    void TypeText(){
        open("https://typerun.top/#rus_adv");

        for (int i = 1; i <= 5; i++) {
            String text = $(".line1").getText(); // Забираем текст из строки

            // Разбиваем текст на отдельные символы в изначальном порядке
            List<Character> characters = new ArrayList<>();
            for (char c : text.toCharArray()) {
                if (c != '¶') {
                    characters.add(c);
                }
            }

            SelenideElement inline = $("#intext"); // Записываем поле ввода в переменную

            // Посылаем по одному знаку, пока не кончится строка
            for (char c : characters) {
                inline.sendKeys(String.valueOf(c));
            }

            inline.pressEnter();
        }
    }
}