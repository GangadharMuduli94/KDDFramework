package com.kdd.utility;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.kdd.config.GlobalVariables;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

public class CSVFileUtils {

	public static void generateDynamicCSV(String fileName, List<String[]> data) throws IOException {
		String filePath = GlobalVariables.baseDirectory + "/" + fileName + ".csv";
		File file = new File(filePath);
		FileWriter outputfile = new FileWriter(file);
		CSVWriter writer = new CSVWriter(outputfile, ',', CSVWriter.NO_QUOTE_CHARACTER,
				CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
		writer.writeAll(data);
		writer.flush();
		writer.close();
	}

	public static String readValueFromTheCSV(String fileName, String row, String col) throws IOException, CsvException {
		String filePath = GlobalVariables.baseDirectory + "/" + fileName + ".csv";
		FileReader filereader = new FileReader(filePath);
		CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
		List<String[]> allData = csvReader.readAll();
		String[] rowVal = allData.get(Integer.parseInt(row));
		return rowVal[Integer.parseInt(col)];
	}

}