package model.db.dao.impl;

import model.db.Database;
import model.db.dao.DaoException;
import model.entities.Question;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

public class QuestionDaoImplTest {

    QuestionDaoImpl questionDao = new QuestionDaoImpl();

    @Test
    public void testIsExistByText() throws DaoException {

        String existent_question = "Дата ухвалення Верховною Радою України Конституції України";
        String fake_question = "Фейк";
        boolean question_exist;

        question_exist = questionDao.isExistByText(existent_question);
        Assert.assertTrue(question_exist);

        question_exist = questionDao.isExistByText(fake_question);
        Assert.assertFalse(question_exist);
    }

    @Test
    public void testInsertQuestion() throws DaoException {

        Connection connection = Database.getConnection();

        int question_id;
        Question existent_question = Question.builder()
                .question_text("Дата ухвалення Верховною Радою України Конституції України")
                .category_id(3).build();

        Question nonexistent_question = Question.builder()
                .question_text("Фейк")
                .category_id(3).build();

        question_id = questionDao.insertQuestion(existent_question, connection);
        Assert.assertEquals(question_id, -1);

        question_id = questionDao.insertQuestion(nonexistent_question, connection);
        Assert.assertNotEquals(question_id, -1);

    }
}