package com.points.http;

import junit.framework.TestCase;

public class MembershipResourceTest extends TestCase {

    public void testGetLocation() throws Exception {
        assertEquals("/users/1", new MembershipResource(null, null).getLocation(1).toString());
    }
}
