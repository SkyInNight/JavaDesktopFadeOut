package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import option.OptionClass;

/**
 * @author ����:Sky_in_night
 * @date ����ʱ��:2018��2��15�� ����10:07:01
 * @ProjectName ��Ŀ����:HappyNewYear
 * @FileName �ļ�����:.Main.java
 * 
 */
public class Main {
	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		BufferedReader bfBufferedReader = new BufferedReader(
				new FileReader(new File("src/main/resources/data/properties/content.properties")));
		OptionClass.showText(bfBufferedReader.readLine().toString());
	}
}
