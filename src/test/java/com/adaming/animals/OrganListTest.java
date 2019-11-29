package com.adaming.animals;

import com.adaming.animals.dto.OrganDto;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


public class OrganListTest extends AbstractClassTest {
    @Test
    public void testOrganList() throws Exception {
        String uri = "/organs";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        OrganDto[] organList = super.mapFromJson(content, OrganDto[].class);
        assertTrue(organList.length > 0);
    }
}
