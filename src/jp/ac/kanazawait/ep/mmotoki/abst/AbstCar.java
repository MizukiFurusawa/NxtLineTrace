package jp.ac.kanazawait.ep.mmotoki.abst;

import jp.ac.kanazawait.ep.mmotoki.addon.SensorChecker;
import lejos.nxt.Button;
import lejos.nxt.LCD;

public abstract class AbstCar {
	/**
	 * カラーセンサー計測スレッド
	 */
	protected SensorChecker checker = SensorChecker.getInstance();
	/**
	 * 開始時刻(ms)
	 * 使用するには，run()の中でstart()を実行すること
	 */
	private long startTime;

	/**
	 * 終了時刻(ms)
	 * 使用するには，run()の中でstop()を実行すること
	 */
	private long endTime;

	/**
	 * 実際に走行させるためのメソッド
	 * 具象クラスの中で実装すること
	 */
	public abstract void run();

	/**
	 * 開始時刻を取得するためのメソッド
	 */
	public void start() {
		checker.start();
		LCD.clear();
		System.out.println("Press any key to start");
		Button.waitForAnyPress();
		LCD.clear();
		startTime = System.currentTimeMillis();
	}

	/**
	 * モーターを停止し，終了時刻を取得し，走行時間を表示する
	 * @param driver
	 */
	public void stop(AbstDriver driver) {
		driver.stop();
		endTime = System.currentTimeMillis();
		checker.stopThread();
		LCD.clear();
		System.out.println("RunningTime=" + (endTime - startTime) + "ms");
		// 20160509 削除
//		System.out.println("Press any key");
//		Button.waitForAnyPress();
	}
}
