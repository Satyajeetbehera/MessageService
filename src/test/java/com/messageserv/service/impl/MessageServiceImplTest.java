package com.messageserv.service.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.messageserv.service.impl.MessageServiceImpl;

public class MessageServiceImplTest extends JerseyTest {
	
	@Override
    protected Application configure() {
        return new ResourceConfig(MessageServiceImpl.class);
    }

	@Test
	public void getAllMessages() {
		final List expected = new ArrayList(); 
		expected.add("");
		Response response = target("MessageServiceImpl").request().get();
//		 	assertEquals(response.getStatus(), 200);
//	        assertEquals(response.readEntity(List.class), expected);
	      }

}
