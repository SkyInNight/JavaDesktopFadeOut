package option;

import Bgm.BaseBgm;
import JFrame.BaseWindow;

import java.awt.Dimension;
import java.awt.Toolkit;


/**
 * @author 作者:Sky_in_night
 * @date 创建时间:2018年2月15日 下午6:29:44
 * @ProjectName 项目名称:HappyNewYear
 * @FileName 文件名称:com.usc.option.OptionClass.java
 * 
 */
public class OptionClass {
	// 设置BaseWindows窗体的大小
	private static int windowsSize = 50;
	// 获得桌面的大小
	private static Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	// 获得桌面宽度
	private static int width = (int) screensize.getWidth();
	// 获得桌面高度
	private static int height = (int) screensize.getHeight();
	// 尾部提名
	private static String Name = "——刘可舟";
	// 背景音乐地址
	private static String bgm = System.getProperty("user.dir") + "/src/main/resources/data/bgm/001.wav";
	// 音乐总时长
	private static int allTime = 28000;

	/**
	 * 用来显示文本的函数
	 * 
	 * @param content
	 *            要显示的文本
	 */
	public static void showText(String content) {
		// 每个字间隔显示的时间
		int time = allTime / (content.length() + Name.length());
		// 每行文字的个数
		int coluns = width / (windowsSize + 20);
		// 换行的次数
		int swap = 0;
		// 总共的行数
		int rows = content.length() / coluns + (content.length() % coluns >= 1 ? 1 : 0);
		// 初始的横坐标
		int initX = width / 2 - coluns * (windowsSize + 5) / 2;
		// 初始的纵坐标
		int initY = height / 2 - rows * (windowsSize + 5) / 2;
		// 播放背景音乐
		new Thread() {
			public void run() {
				BaseBgm.PlayStart(bgm);
				System.exit(0);
			};
		}.start();
		// 输出文本内容
		for (int i = 0; i < content.length(); i++) {
			new BaseWindow(windowsSize, windowsSize, initX + (i % coluns) * (windowsSize + 5), initY + swap * (windowsSize + 5), content.substring(i, i + 1), i * time);
			if ((i + 1) % coluns == 0)
				swap++;
		}
		// 输出提名
		for (int i = 0; i < Name.length(); i++) {
			new BaseWindow(
					windowsSize, windowsSize, initX + (coluns - Name.length() + i) * (windowsSize + 5), initY + rows * (windowsSize + 5), Name.substring(i, i + 1),
					(i + content.length()) * time);
		}
	}
}
