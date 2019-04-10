package JLabel;

import javax.swing.JLabel;
import java.awt.*;

/**
 * @author 作者:Sky_in_night
 * @date 创建时间:2018年2月15日 下午4:36:05
 * @ProjectName 项目名称:HappyNewYear
 * @FileName 文件名称:com.usc.base.JLabel.BaseLabel.java
 * 
 */
public class BaseLabel extends JLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//定义JLabel字体
	private static String fontFamily = "华文行楷";
	//定义Jabel字体大小,这里设置的字体需要比BaseWindwos窗体的大小要小一些，不然显示会有问题
	private static int fontSize = 46;
	//定义JLabel的字体类型
	private static Font myFont = new Font(fontFamily, Font.BOLD, fontSize);
	public BaseLabel(String image) {
		super(image);
		setFont(myFont);
	}
}
