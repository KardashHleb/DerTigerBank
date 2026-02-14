import org.example.ui.Menu;
import org.example.ui.WelcomeScreen;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
class MenuTest {
    @Test
void testMenuShowsWelcomeScreen() {
    // Создаем ЗАГЛУШКУ (mock)
    WelcomeScreen fakeScreen = mock(WelcomeScreen.class);

    Menu menu = new Menu(fakeScreen);
    menu.show();

    // Проверяем, что метод show() БЫЛ ВЫЗВАН у заглушки
    verify(fakeScreen).show();
}}