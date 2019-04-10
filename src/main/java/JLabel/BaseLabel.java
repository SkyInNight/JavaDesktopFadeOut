package JLabel;

import javax.swing.JLabel;
import java.awt.*;

/**
 * @author ����:Sky_in_night
 * @date ����ʱ��:2018��2��15�� ����4:36:05
 * @ProjectName ��Ŀ����:HappyNewYear
 * @FileName �ļ�����:com.usc.base.JLabel.BaseLabel.java
 * 
 */
public class BaseLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//����JLabel����
	private static String fontFamily = "�����п�";
	//����Jabel�����С,�������õ�������Ҫ��BaseWindwos����Ĵ�СҪСһЩ����Ȼ��ʾ��������
	private static int fontSize = 46;
	//����JLabel����������
	private static Font myFont = new Font(fontFamily, Font.BOLD, fontSize);
	public BaseLabel(String image) {
		super(image);
		setFont(myFont);
	}
}
