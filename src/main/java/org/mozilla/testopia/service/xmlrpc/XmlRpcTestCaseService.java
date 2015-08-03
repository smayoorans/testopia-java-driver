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
package org.mozilla.testopia.service.xmlrpc;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.mozilla.testopia.model.TestCase;
import org.mozilla.testopia.model.TestPlan;
import org.mozilla.testopia.service.TestCaseService;


/**
 * XML-RPC implementation of TestCaseService.
 * @author Bruno P. Kinoshita - http://www.kinoshita.eti.br
 * @see TestCaseService
 * @since 0.1
 */
public class XmlRpcTestCaseService implements TestCaseService {
    // Attributes 
    // ------------------------------------------------------------------------
    private final XmlRpcClient client;
    // Methods
    // ------------------------------------------------------------------------
    /**
     * Create a XmlRpcTestCaseService.
     * @param xmlRpcClient XML-RPC client
     */
    public XmlRpcTestCaseService(XmlRpcClient xmlRpcClient) {
        client = xmlRpcClient;
    }
    /* (non-Javadoc)
     * @see org.mozilla.testopia.service.TestCaseService#lookupStatusNameById(java.lang.Integer)
     */
    public String lookupStatusNameById(Integer id) {
        String name = null;
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("id", id);
        try {
            Object o = this.client.execute("TestCase.lookup_status_name_by_id", new Object[]{map});
            name = (String)o;
            /*
             *  (java.util.HashMap<K,V>) {milestone=---, isactive=1, product_id=3, description=Sample build, name=My build, build_id=1}
             */
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return name;
    }

    @SuppressWarnings("unchecked")
    public TestCase createTestCase(TestCase testCase) {
        TestCase testCase1 = new TestCase();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("summary", testCase.getSummary());
        map.put("category", testCase.getCategoryId());
        map.put("product", testCase.getProductId());
        map.put("priority", testCase.getPriorityId());
        try {
            Object r = this.client.execute("TestCase.create", new Object[]{map});
            System.out.println("............>" + r);
         /*   testCase1 = new TestCase(Integer.parseInt(""+r.get("case_id")),
                    Integer.parseInt(""+r.get("status_id")), Integer.parseInt(""+r.get("category_id")), Integer.parseInt(""+r.get("priority_id")),
                    Integer.parseInt(""+r.get("status_id")), Integer.parseInt(""+r.get("status_id")), null, null, Boolean.FALSE, r.get(""),
                    ""+r.get("name"), ""+r.get("type"), ""+r.get("default_product_version"),
                    Boolean.parseBoolean(""+r.get("isactive")));*/
            /*
             *  (java.util.HashMap<K,V>) {creation_date=2012-05-29 18:59:18, test_run_count=1, isactive=1, product_id=3, name=my plan, author_id=1, test_case_count=1, default_product_version=unspecified, type_id=5, plan_id=1}
             */
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return testCase1;
    }

}
