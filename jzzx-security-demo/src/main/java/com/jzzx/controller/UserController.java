package com.jzzx.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.jzzx.pageModel.UserPage;
import com.jzzx.po.User;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@GetMapping
	public List<User> allUsers() {
		
		List<User> userList = new ArrayList<>();
		userList.add(new User());
		userList.add(new User());
		userList.add(new User());
		return userList;
	}
	@GetMapping("/byName")
	public List<User> getUsersByName(@RequestParam(name="name", required=false, defaultValue="456") String username) {
		System.out.println(username);
		List<User> userList = new ArrayList<>();
		userList.add(new User());
		userList.add(new User());
		userList.add(new User());
		return userList;
	}
	@GetMapping("/userByCondition")
	@JsonView(User.showSimpleInfos.class)
	public List<User> getUsersByCondition(UserPage userPage, @PageableDefault(page=1,size=10,sort= {"name"},direction=Sort.Direction.DESC) Pageable pageable) {
		System.out.println(ReflectionToStringBuilder.toString(userPage, ToStringStyle.MULTI_LINE_STYLE));
		System.out.println(ReflectionToStringBuilder.toString(pageable, ToStringStyle.MULTI_LINE_STYLE));
		List<User> userList = new ArrayList<>();
		userList.add(new User());
		userList.add(new User());
		userList.add(new User());
		return userList;
	}
	/*
	 *根据用户的id来返回用户的详细信息 
	 */
	@GetMapping("/{id:\\d+}")
	@JsonView(User.showDetailInfos.class)
	public User getUserById(@PathVariable String id) {
		User u = new User();
		u.setName("tom");
		return u;
	}
}
