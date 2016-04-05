package com.proj.test.jqgrid;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.proj.common.mapper.JsonMapper;
import com.proj.entity.jqgrid.JsonReader;
import com.proj.entity.jqgrid.Row;

public class JsonTest {

	@Test
	public void testPrint(){
		JsonReader reader = new JsonReader();
		reader.setPage("1");
		reader.setRecords("3");
		reader.setTotal(2);
		List<Row> rows = Lists.newArrayList();
		for (int i = 0; i < 3; i++) {
			Row row = new Row();
			row.setId(""+i);
			List<Object> cell = Lists.newArrayList();
			for (int j = 0; j < 7; j++) {
				cell.add(i+j+"");
			}
			row.setCell(cell);
			rows.add(row);
		}
		reader.setRows(rows);
		String json = JsonMapper.getInstance().toJson(reader);
		System.out.println(json);
	}
}
