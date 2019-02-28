package com.jzzx.po;

import com.fasterxml.jackson.annotation.JsonView;

public class User {
	
	/*
	 *在实体类中，定义视图，来区分服务端返回的数据内容
	 */
	public interface showSimpleInfos {}
	public interface showDetailInfos extends showSimpleInfos {}
	
	private String id;
	private String name;
	private String age;
	
	//在get方法上定义视图
	@JsonView(showSimpleInfos.class)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JsonView(showSimpleInfos.class)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@JsonView(showDetailInfos.class)
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	
}
