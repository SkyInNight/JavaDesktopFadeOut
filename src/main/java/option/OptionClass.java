package option;

import Bgm.BaseBgm;
import JFrame.BaseWindow;

import java.awt.Dimension;
import java.awt.Toolkit;


/**
 * @author ����:Sky_in_night
 * @date ����ʱ��:2018��2��15�� ����6:29:44
 * @ProjectName ��Ŀ����:HappyNewYear
 * @FileName �ļ�����:com.usc.option.OptionClass.java
 * 
 */
public class OptionClass {
	// ����BaseWindows����Ĵ�С
	private static int windowsSize = 50;
	// �������Ĵ�С
	private static Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	// ���������
	private static int width = (int) screensize.getWidth();
	// �������߶�
	private static int height = (int) screensize.getHeight();
	// β������
	private static String Name = "����������";
	// �������ֵ�ַ
	private static String bgm = System.getProperty("user.dir") + "/src/main/resources/data/bgm/001.wav";
	// ������ʱ��
	private static int allTime = 28000;

	/**
	 * ������ʾ�ı��ĺ���
	 * 
	 * @param content
	 *            Ҫ��ʾ���ı�
	 */
	public static void showText(String content) {
		// ÿ���ּ����ʾ��ʱ��
		int time = allTime / (content.length() + Name.length());
		// ÿ�����ֵĸ���
		int coluns = width / (windowsSize + 20);
		// ���еĴ���
		int swap = 0;
		// �ܹ�������
		int rows = content.length() / coluns + (content.length() % coluns >= 1 ? 1 : 0);
		// ��ʼ�ĺ�����
		int initX = width / 2 - coluns * (windowsSize + 5) / 2;
		// ��ʼ��������
		int initY = height / 2 - rows * (windowsSize + 5) / 2;
		// ���ű�������
		new Thread() {
			public void run() {
				BaseBgm.PlayStart(bgm);
				System.exit(0);
			};
		}.start();
		// ����ı�����
		for (int i = 0; i < content.length(); i++) {
			new BaseWindow(windowsSize, windowsSize, initX + (i % coluns) * (windowsSize + 5), initY + swap * (windowsSize + 5), content.substring(i, i + 1), i * time);
			if ((i + 1) % coluns == 0)
				swap++;
		}
		// �������
		for (int i = 0; i < Name.length(); i++) {
			new BaseWindow(
					windowsSize, windowsSize, initX + (coluns - Name.length() + i) * (windowsSize + 5), initY + rows * (windowsSize + 5), Name.substring(i, i + 1),
					(i + content.length()) * time);
		}
	}
}
