package JFrame;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JWindow;

import JLabel.BaseLabel;
import com.sun.awt.AWTUtilities;

/**
 * @author 作者:Sky_in_night
 * @date 创建时间:2018年2月15日 下午4:31:00
 * @ProjectName 项目名称:HappyNewYear
 * @FileName 文件名称:com.usc.base.JFrame.BaseFrame.java
 * 
 */
public class BaseWindow extends JWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float alpha = 0;
	protected static float MAX_VIRT = 1.0f;// 窗体透明程度
	protected static float STEP_WEIGTH = 0.01f;// 窗体渐变幅度
	protected static int RUN_TIME = 15;// 每次执行线程时的间隔时间

	public BaseWindow(int width, int heigth, int x, int y, String image, int time) {
		AWTUtilities.setWindowOpacity(this, 0.0f);
		// 设置窗体背景颜色为透明
		AWTUtilities.setWindowOpaque(this, false);
		// 设置窗体大小
		setSize(width, heigth);
		// 设置窗体位置
		setLocation(x, y);
		// 插入字体
		add(new BaseLabel(image));
		// 显示窗体
		setVisible(true);
		// 淡入窗体
		showWindow(time);
	}

	/**
	 * 显示窗体的函数，有渐渐淡入的特效
	 * 
	 * @param startTime
	 *            开始显示窗体的时间
	 */
	public void showWindow(int startTime) {
		new Timer().schedule(new MyTaskShow(this), startTime);
	}

	/**
	 * 窗体显示特效的任务对象
	 * 
	 * @author Sky_in_night
	 *
	 */
	private class MyTaskShow extends TimerTask {
		BaseWindow bw;

		public MyTaskShow(BaseWindow bw) {
			super();
			this.bw = bw;
		}

		@Override
		public void run() {
			while (alpha < MAX_VIRT - STEP_WEIGTH) {
				alpha = alpha + STEP_WEIGTH;
				AWTUtilities.setWindowOpacity(bw, alpha);
				try {
					Thread.sleep(RUN_TIME);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
