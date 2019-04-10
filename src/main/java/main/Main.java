package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import option.OptionClass;

/**
 * @author 作者:Sky_in_night
 * @date 创建时间:2018年2月15日 下午10:07:01
 * @ProjectName 项目名称:HappyNewYear
 * @FileName 文件名称:.Main.java
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
