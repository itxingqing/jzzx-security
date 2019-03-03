package com.jzzx.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	
	//构建spring mvc的环境
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	//在测试方法之前构建mock
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	@Test
	public void whenQuerySuccess() throws Exception {
		//在eclipse中favorites中配置后，可以省略MockMvcRequestBuilders,直接写方法
		mockMvc.perform(MockMvcRequestBuilders.get("/user")
						.contentType(MediaType.APPLICATION_JSON_UTF8))
						//增加期望
						.andExpect(MockMvcResultMatchers.status().isOk())
						.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
	}
	@Test
	public void whenQuerySuccessByName() throws Exception {
		mockMvc.perform(get("/user/userByCondition")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.param("name", "123"))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.length()").value(3));
						
		
	}
	@Test
	public void queryUsersByCondition() throws Exception {
		String result = mockMvc.perform(get("/user/userByCondition")
						.contentType(MediaType.APPLICATION_JSON_UTF8)
						.param("name", "tom")
						.param("ageRange", "10")
						.param("id", "Fsdfasdfdsf")
		/*
		 * .param("size", "15") .param("page", "3") .param("sort", "age,desc")
		 */)
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.length()").value(3))
						//增加返回结果的信息
						.andReturn().getResponse().getContentAsString();
		System.out.println(result);//结果中，没有age信息，因为controller中定义了简单的视图
						
	}
	
	/**
	 * 根据用户的id来获取用户的详细信息
	 * @throws Exception 
	 */
	@Test
	public void queryUserInfoById() throws Exception {
		String result = mockMvc.perform(get("/user/1")
						.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("tom"))
		//增加返回值的处理
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);//这里调用的方法，使用的详细视图，详细视图中包含age
	}
	/**
	 * 测试用户的id被服务器端验证后的效果
	 * 服务器端的方法会再url中增加正则表达式来验证客户端传来的参数
	 * @throws Exception 
	 */
	@Test
	public void queryUserInfoFail() throws Exception {
		mockMvc.perform(get("/user/a")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().is4xxClientError());
	}
	/*
	 * 创建用户
	 */
	@Test
	public void createUser() throws Exception {
		Date date = new Date();
		System.out.println(date.getTime());
		String userInfo = "{\"id\":null,\"name\":\"tom\",\"age\":18, \"birthday\":"+date.getTime()+"}";
		String result = mockMvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(userInfo)//设定内容json字符串
				)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(111))
				.andReturn().getResponse().getContentAsString();
		
		System.out.println(result);
				
		
	}
}
