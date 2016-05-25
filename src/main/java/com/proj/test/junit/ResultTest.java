package com.proj.test.junit;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.proj.common.mapper.JsonMapper;
import com.proj.entity.Author;
import com.proj.vo.Result;

public class ResultTest {

	@Test
	public void buildResultTest(){
		JsonMapper mapper = JsonMapper.getInstance();
		Author author = new Author(2, "cccg");
		Author author2 = new Author(3, "cccg3");
		List<Author> list = Lists.newArrayList();
		list.add(author);
		list.add(author2);
		System.out.println(mapper.toJson(Result.buildSuccessResult()));
		System.out.println(mapper.toJson(Result.buildSuccessResult("success msg")));
		System.out.println(mapper.toJson(Result.buildSuccessResult(author)));
		
		System.out.println(mapper.toJson(Result.buildErrorResult()));
		System.out.println(mapper.toJson(Result.buildErrorResult("my error msg")));
		System.out.println(mapper.toJson(Result.buildErrorResult(author)));
		
		System.out.println(mapper.toJson(Result.buildResult(Result.Status.BAD_REQUEST)));
		System.out.println(mapper.toJson(Result.buildResult(Result.Status.INTERNAL_SERVER_ERROR,"inner 500错误")));
		System.out.println(mapper.toJson(Result.buildResult(Result.Status.INTERNAL_SERVER_ERROR,author)));

		System.out.println(mapper.toJson(Result.buildResult(Result.Status.INTERNAL_SERVER_ERROR,list)));
	}
}
