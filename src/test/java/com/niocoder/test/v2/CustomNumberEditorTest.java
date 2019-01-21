package com.niocoder.test.v2;

import com.niocoder.beans.propertyeditors.CustomDateEditor;
import com.niocoder.beans.propertyeditors.CustomNumberEditor;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 *
 */
public class CustomNumberEditorTest {

    @Test
    public void testConvertString2Number() {

        CustomNumberEditor editor = new CustomNumberEditor(Integer.class, true);
        editor.setAsText("1");
        Object value = editor.getValue();
        Assert.assertTrue(value instanceof Integer);
        Assert.assertEquals(1, ((Integer) editor.getValue()).intValue());

        editor.setAsText("");
        Assert.assertTrue(editor.getValue() == null);

        try {
            editor.setAsText("3.1");
        } catch (IllegalArgumentException e) {
            return;
        }
        Assert.fail();
    }
}
