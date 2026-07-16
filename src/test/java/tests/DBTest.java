package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTest {

    // ОСНОВНОЙ ТЕСТ: Проверка базового SQL-запроса к системным таблицам
    @Test
    public void checkQuery() throws SQLException {
        try (Connection connection = DataBaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(
                    "SELECT * FROM information_schema.sql_features LIMIT 5;"
            );

            int count = 0;
            while (rs.next()) {
                System.out.println(rs.getString(1));
                count++;
            }
            Assert.assertTrue(count > 0, "Данные из information_schema не были возвращены");
        }
    }

    // ТЕСТ 1: Проверка успешного и активного соединения с БД
    @Test
    public void testConnectionIsValid() throws SQLException {
        try (Connection connection = DataBaseConnection.getConnection()) {
            Assert.assertNotNull(connection, "Соединение с базой данных равно null");
            // Проверяем, что соединение живое (таймаут ожидания — 3 секунды)
            Assert.assertTrue(connection.isValid(3), "Соединение с базой данных неактивно");
        }
    }

    // ТЕСТ 2: Проверка выполнения простейшего математического запроса (Smoke-тест)
    @Test
    public void testSimpleSelectMath() throws SQLException {
        try (Connection connection = DataBaseConnection.getConnection();
             Statement statement = connection.createStatement()) {

            // Выполняем легкий запрос, не привязанный к конкретным таблицам приложения
            try (ResultSet rs = statement.executeQuery("SELECT 2 + 2 AS result;")) {
                Assert.assertTrue(rs.next(), "Запрос не вернул строку с результатом");
                int result = rs.getInt("result");
                Assert.assertEquals(result, 4, "База данных неправильно посчитала значение математического выражения");
            }
        }
    }

    // ТЕСТ 3: Проверка правильной обработки синтаксических ошибок (Негативный тест)
    @Test
    public void testInvalidQueryThrowsException() {
        // Тест пройдет успешно только в том случае, если база данных выбросит SQLException
        Assert.assertThrows(SQLException.class, () -> {
            try (Connection connection = DataBaseConnection.getConnection();
                 Statement statement = connection.createStatement()) {

                // Намеренно пишем запрос с синтаксической ошибкой (пропущено слово FROM)
                statement.executeQuery("SELECT * users;");
            }
        });
    }
}
