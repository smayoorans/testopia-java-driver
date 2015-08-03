/*
 * The MIT License
 *
 * Copyright (c) <2012> <Bruno P. Kinoshita>
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.mozilla.testopia.service;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mozilla.testopia.TestopiaAPI;
import org.mozilla.testopia.model.TestCase;
import org.mozilla.testopia.model.TestPlan;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


/**
 * Tests for TestPlanService.
 * @author Mayooran
 */
@Ignore("MOCKME!")
public class TestTestCaseService {

    private TestopiaAPI api;
    private static final String USER_NAME = "smayoorans@gmail.com";
    private static final String PASSWORD = "velanai1987*";

    
    @Before
    public void setUp() throws MalformedURLException {
        URL url = new URL("http://localhost/bugzilla/xmlrpc.cgi");
        api = new TestopiaAPI(url);
        api.login(USER_NAME, PASSWORD);
    }
    
    @Test
    public void testCreateTestCase() {

        System.out.println("Login " + api.isLoggedIn());

        TestCase testCase = new TestCase();
        testCase.setSummary("This is summary of the testcase.");
        testCase.setDefaultTesterId(1);
        testCase.setProductId(1);
        testCase.setPriorityId(1);
        testCase.setRequirement("This is requirement");
        testCase.setActions("This is action of the testcase");
        testCase.setCategoryId(1);

        TestCase testPlan1 = api.createTestCase(testCase);
        assertNotNull(testPlan1);
        assertTrue(testPlan1.getId() > 0);

        System.out.println("Get Description " + testPlan1.getSummary());

    }


}
