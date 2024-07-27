package dev.durga.java;

import java.util.ArrayList;
import java.util.List;

public class DataLineageList {
	private List<DataLineage> data = new ArrayList<>();

	public DataLineageList() {
	}

	public List<DataLineage> getData() {
		return data;
	}

	public void setData(List<DataLineage> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DataLineageList [data=" + data + "]";
	}
}
