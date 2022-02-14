package test.epam.bartenderhelper.sql;

import by.epam.bartenderhelper.model.dao.sql.Column;
import by.epam.bartenderhelper.model.dao.sql.SqlBuilderFactory;
import by.epam.bartenderhelper.model.dao.sql.Table;
import by.epam.bartenderhelper.model.dao.sql.query.LogicOperator;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class SqlBuilderFactoryTest {

    @Test
    public void testSelect() {
        String actual = SqlBuilderFactory.select()
                .selectColumns(Table.PHOTOS)
                .from(Table.PHOTOS)
                .where(Column.PHOTO_ID, LogicOperator.EQUALS, "1")
                .toString();

        String expected = "SELECT photos.photo_id, photos.name, photos.data FROM photos WHERE photos.photo_id=1";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testInsert() {
        String actual = SqlBuilderFactory.insert(Table.PHOTOS).setColumns(Table.PHOTOS).toString();
        String expected = "INSERT INTO photos(name,data) VALUES(?,?)";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testDelete() {
        String actual = SqlBuilderFactory.delete().from(Table.PHOTOS).where(Column.PHOTO_ID, LogicOperator.EQUALS).toString();
        String expected = "DELETE FROM photos WHERE photos.photo_id=?";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testUpdate() {
        String actual = SqlBuilderFactory.update(Table.PHOTOS).setAll(Table.PHOTOS)
                .where(Column.PHOTO_ID, LogicOperator.EQUALS).toString();

        String expected = "UPDATE photos SET photos.name=?,photos.data=? WHERE photos.photo_id=?";
        Assert.assertEquals(actual, expected);
    }
}