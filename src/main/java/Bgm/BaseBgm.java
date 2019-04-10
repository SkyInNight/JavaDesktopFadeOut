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
 * @modification time 2017年12月24日下午11:08:29
 * @fileName BaseBgm.java
 * @packageName BaseBgm
 * @projectName Calculator
 * @className BaseBgm
 */
public class BaseBgm {

	public BaseBgm() {
	}

	private static SourceDataLine sdl;// 音乐播放读取函数
	private static boolean start = true;// 是否音乐开始播放
	private static int STEP = 64;// 按多少byte读取音频文件

	/**
	 * 播放音乐的函数，传入音频的地址
	 * 
	 * @param fileurl
	 */
	public static void play_music(String fileurl) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File(fileurl));// 打开一个音频文件文件搜索
			AudioFormat af = ais.getFormat();

			DataLine.Info info = new DataLine.Info(SourceDataLine.class, af);// 仿照百度。。。。。
			sdl = (SourceDataLine) AudioSystem.getLine(info);
			sdl.open(af);
			sdl.start();// 开始播放，一次播放
			FloatControl fc = (FloatControl) sdl.getControl(FloatControl.Type.MASTER_GAIN);
			// value可以用来设置音量，从0-2.0
			double value = 2;
			float dB = (float) (Math.log(value == 0.0 ? 0.0001 : value) / Math.log(10.0) * 20.0);
			fc.setValue(dB);
			int end = 0;// 用来判断此音频是否播放完毕
			int size = 1024 * STEP;// 设置每次读取64kb的数据帧
			byte[] buffer = new byte[size];
			while (end != -1)// 当还没读取完继续读取
			{
				end = ais.read(buffer, 0, size);
				if (end == -1)// 防止下溢
					break;
				sdl.write(buffer, 0, end);
			}
			start = false;
			sdl.stop();// 结束播放
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 播放背景音乐的方法
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

	// interrupt 结束线程的方法
	public static void StopMusic() {
		sdl.stop();
		start = false;
		sdl.close();
	}

	public static void PlayStart(String fileurl) {
		/**
		 * 防止还有音乐还在播放
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
