package org.github.nyrkovalex.ironadmin.core.servlet;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public abstract class UnitTest {

    @Before
    public void setUpMocks() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
}
