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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mozilla.testopia.TestopiaAPI;
import org.mozilla.testopia.model.Build;
import org.mozilla.testopia.model.TestRun;


/**
 * Tests for BuildService.
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @since 0.1
 */
@Ignore("MOCKME!")
public class TestBuildService {

    private TestopiaAPI api;
    
    @Before
    public void setUp() throws MalformedURLException {
        URL url = new URL("http://localhost/bugzilla/xmlrpc.cgi");
        api = new TestopiaAPI(url);
        api.login("smayoorans@gmail.com", "velanai1987*");
    }
    
    @Test
    public void testCheckBuild() {
        System.out.println("Login " + api.isLoggedIn());

       /* Build builds = api.create("ProductId", "productName", "milestone", "Description", true);
        System.out.println("Build" + builds);
*/
        Build build = api.checkBuild("MyBuild", "TestProduct");
        assertNotNull(build);
        assertTrue(build.getId() > 0);

        System.out.println("Get Description " + build.getDescription());
        System.out.println("Get milestone " + build.getMilestone());
        System.out.println("Is active " + build.getIsActive());

    }

    @Test
    public void testCreateBuild() throws Exception {

        System.out.println("Login " + api.isLoggedIn());

        Build build = api.create("TestProduct", "MyBuild3", "Milestone", "My build description", Boolean.TRUE);

/*        assertNotNull(build);
        assertTrue(build.getId() > 0);*/

        System.out.println("Get Description " + build.getDescription());
        System.out.println("Get milestone " + build.getMilestone());
        System.out.println("Is active " + build.getIsActive());

    }
}
