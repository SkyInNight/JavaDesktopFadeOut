package JFrame;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JWindow;

import JLabel.BaseLabel;
import com.sun.awt.AWTUtilities;

/**
 * @author ����:Sky_in_night
 * @date ����ʱ��:2018��2��15�� ����4:31:00
 * @ProjectName ��Ŀ����:HappyNewYear
 * @FileName �ļ�����:com.usc.base.JFrame.BaseFrame.java
 * 
 */
public class BaseWindow extends JWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private float alpha = 0;
	protected static float MAX_VIRT = 1.0f;// ����͸���̶�
	protected static float STEP_WEIGTH = 0.01f;// ���彥�����
	protected static int RUN_TIME = 15;// ÿ��ִ���߳�ʱ�ļ��ʱ��

	public BaseWindow(int width, int heigth, int x, int y, String image, int time) {
		AWTUtilities.setWindowOpacity(this, 0.0f);
		// ���ô��屳����ɫΪ͸��
		AWTUtilities.setWindowOpaque(this, false);
		// ���ô����С
		setSize(width, heigth);
		// ���ô���λ��
		setLocation(x, y);
		// ��������
		add(new BaseLabel(image));
		// ��ʾ����
		setVisible(true);
		// ���봰��
		showWindow(time);
	}

	/**
	 * ��ʾ����ĺ������н����������Ч
	 * 
	 * @param startTime
	 *            ��ʼ��ʾ�����ʱ��
	 */
	public void showWindow(int startTime) {
		new Timer().schedule(new MyTaskShow(this), startTime);
	}

	/**
	 * ������ʾ��Ч���������
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
