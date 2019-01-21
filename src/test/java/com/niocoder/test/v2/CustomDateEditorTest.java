package com.niocoder.test.v2;

import com.niocoder.beans.propertyeditors.CustomDateEditor;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 *
 */
public class CustomDateEditorTest {

    @Test
    public void testConvertString2Date() {

        CustomDateEditor editor = new CustomDateEditor(true);
        editor.setAsText("2019-01-21");
        Object value = editor.getValue();
        Assert.assertTrue(value instanceof Date);

        editor.setAsText("");
        Assert.assertTrue(editor.getValue() == null);
    }
}
