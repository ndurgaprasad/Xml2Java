package dev.durga.java;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class PostCreatePaymentConverter {

	private static final String XML_FILE_PATH = "src/main/resources/post-create-payment-handler.xml";
	private static final String API_REPOSITORY_NAME = "YourApiRepositoryName";
	private static final String BRANCH_NAME = "YourBranchName";
	private static final String FILE_PATH = "src/main/resources/";
	private static final String FILE_NAME = "post-create-payment-handler.xml";
	private static final String VARIABLE = "variable";
	private static final String VARIABLE_NAME = "variableName";

	public static void main(String[] args) {
		try {
			List<DataLineage> dataLineageList = extractDataLineageFromXML(XML_FILE_PATH);
			for (DataLineage dataLineage : dataLineageList) {
				System.out.println(dataLineage);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<DataLineage> extractDataLineageFromXML(String filePath) throws Exception {
		Document doc = parseXML(filePath);
		List<DataLineage> dataLineageList = new ArrayList<>();
		NodeList variableNodes = doc.getElementsByTagName(VARIABLE);

		for (int i = 0; i < variableNodes.getLength(); i++) {
			Element variableElement = (Element) variableNodes.item(i);
			String variableName = variableElement.getAttribute(VARIABLE_NAME);
			String lineContent = variableElement.getTextContent();
			List<String> lineContentList = extractLineContent(lineContent);
			for(int j = 0; j < lineContentList.size(); j++) {
				DataLineage dataLineage = new DataLineage();
				dataLineage.setApiRepositoryName(API_REPOSITORY_NAME);
				dataLineage.setBranchName(BRANCH_NAME);
				dataLineage.setFilePath(FILE_PATH);
				dataLineage.setFileName(FILE_NAME);
				dataLineage.setVariableName(variableName);
				dataLineage.setTransformation("No");
				dataLineage.setLineContent(lineContentList.get(j));
				dataLineageList.add(dataLineage);
			}
		}

		return dataLineageList;
	}

	private static List<String> extractLineContent(String content) {
		List<String> variablesContent = new ArrayList<>();
		int varIndex = content.indexOf("var");
		int dashIndex = content.indexOf("---", varIndex);
		if (varIndex > -1 && dashIndex > -1) {
			String extractedText = content.substring(varIndex, dashIndex).trim();
			variablesContent.addAll(extractLinesBetweenVars(extractedText));
		}
		return variablesContent;
	}

	public static List<String> extractLinesBetweenVars(String input) {
		List<String> result = new ArrayList<>();
		int startIndex = 0;
		while (startIndex < input.length()) {
			int varIndex = input.indexOf("var ", startIndex);
			if (varIndex == -1) {
				break;
			}
			int nextVarIndex = input.indexOf("var ", varIndex + 3);
			if (nextVarIndex == -1) {
				nextVarIndex = input.length(); // Handle the last segment
			}
			String segment = input.substring(varIndex, nextVarIndex).trim();
			result.add(extractBeforeFun(segment));
			startIndex = nextVarIndex;
		}

		return result;
	}
	
	public static String extractBeforeFun(String input) {
        int funIndex = input.indexOf("fun");
        if (funIndex != -1) {
            return input.substring(0, funIndex).trim();
        } else {
            return input.trim();
        }
    }

	private static Document parseXML(String filePath) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder.parse(new File(filePath));
	}
}
