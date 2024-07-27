package dev.durga.java;

import java.util.List;

public class DataLineage {
	private String apiRepositoryName;
	private String branchName;
	private String filePath;
	private String fileName;
	private String variableName;
	private String transformation;
	private String lineContent;

	public String getApiRepositoryName() {
		return apiRepositoryName;
	}

	public void setApiRepositoryName(String apiRepositoryName) {
		this.apiRepositoryName = apiRepositoryName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getTransformation() {
		return transformation;
	}

	public void setTransformation(String transformation) {
		this.transformation = transformation;
	}

	public String getLineContent() {
		return lineContent;
	}

	public void setLineContent(String lineContent) {
		this.lineContent = lineContent;
	}

	@Override
	public String toString() {
		return "DataLineage [apiRepositoryName=" + apiRepositoryName + ", branchName=" + branchName + ", filePath="
				+ filePath + ", fileName=" + fileName + ", variableName=" + variableName + ", transformation="
				+ transformation + ", lineContent=" + lineContent + "]";
	}
}
