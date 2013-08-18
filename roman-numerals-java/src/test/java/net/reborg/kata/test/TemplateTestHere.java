package net.reborg.template.test;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TemplateTestHere {

    @Test
    public void testThis() {
        assertThat("Help! Integers don't work", 0, is(1));
    }
    
}