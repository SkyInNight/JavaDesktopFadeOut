package Bgm;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;

/**
 * 
 * @author Sky_in_night
 * @modification time 2017��12��24������11:08:29
 * @fileName BaseBgm.java
 * @packageName BaseBgm
 * @projectName Calculator
 * @className BaseBgm
 */
public class BaseBgm {

	public BaseBgm() {
	}

	private static SourceDataLine sdl;// ���ֲ��Ŷ�ȡ����
	private static boolean start = true;// �Ƿ����ֿ�ʼ����
	private static int STEP = 64;// ������byte��ȡ��Ƶ�ļ�

	/**
	 * �������ֵĺ�����������Ƶ�ĵ�ַ
	 * 
	 * @param fileurl
	 */
	public static void play_music(String fileurl) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileurl));// ��һ����Ƶ�ļ��ļ�����
			AudioFormat af = ais.getFormat();

			DataLine.Info info = new DataLine.Info(SourceDataLine.class, af);// ���հٶȡ���������
			sdl = (SourceDataLine) AudioSystem.getLine(info);
			sdl.open(af);
			sdl.start();// ��ʼ���ţ�һ�β���
			FloatControl fc = (FloatControl) sdl.getControl(FloatControl.Type.MASTER_GAIN);
			// value��������������������0-2.0
			double value = 2;
			float dB = (float) (Math.log(value == 0.0 ? 0.0001 : value) / Math.log(10.0) * 20.0);
			fc.setValue(dB);
			int end = 0;// �����жϴ���Ƶ�Ƿ񲥷����
			int size = 1024 * STEP;// ����ÿ�ζ�ȡ64kb������֡
			byte[] buffer = new byte[size];
			while (end != -1)// ����û��ȡ�������ȡ
			{
				end = ais.read(buffer, 0, size);
				if (end == -1)// ��ֹ����
					break;
				sdl.write(buffer, 0, end);
			}
			start = false;
			sdl.stop();// ��������
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ���ű������ֵķ���
	 * 
	 * @param fileurl
	 */
	public static void PlayBgm(String fileurl) {
		new Thread() {
			@Override
			public void run() {
				super.run();
				PlayStart(fileurl);
			}
		}.start();
	}

	// interrupt �����̵߳ķ���
	public static void StopMusic() {
		sdl.stop();
		start = false;
		sdl.close();
	}

	public static void PlayStart(String fileurl) {
		/**
		 * ��ֹ�������ֻ��ڲ���
		 */
		if (sdl != null && sdl.isOpen()) {
			sdl.stop();
			sdl.close();
		}
		play_music(fileurl);
		sdl.close();
	}

	public static void ChangeStart(String fileurl) {
		if (start)
			StopMusic();
		start = true;
		play_music(fileurl);
	}
}
