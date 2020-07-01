package com.fubon.portal;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fubon.portal.repository.Agent;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PortalControllerTest {

	@Autowired private PortalController controller;
	
//	@Test
//	public void testFindDeptInfoByUserName() {
//		List<Agent> list = this.controller.findAgentist("152722", "2020-05-05");
//		Assert.assertNotNull(list);
//	}
}
