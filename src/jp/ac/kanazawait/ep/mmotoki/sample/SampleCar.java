package jp.ac.kanazawait.ep.mmotoki.sample;

import jp.ac.kanazawait.ep.mmotoki.abst.AbstCar;
import jp.ac.kanazawait.ep.mmotoki.abst.AbstDriver;
import jp.ac.kanazawait.ep.mmotoki.abst.AbstNavigator;
import jp.ac.kanazawait.ep.mmotoki.addon.Logger;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.robotics.Color;

/**
 * Enterボタンを押す毎に,
 * 右エッジ・左エッジを切り替えてトレースするCar具象クラス
 * @author mmotoki
 *
 */
public class SampleCar extends AbstCar {
	private AbstNavigator navL = new LeftEdgeTracer();
	private AbstNavigator navR = new RightEdgeTracer();
	private AbstDriver driver = new SimpleDriver();
	private boolean direction = true;
	private boolean isStartDash = true;


	@Override
	public void run() {
		// 初期化処理
		start();

		// 20160509追加
		// ログ記録時のみ必要
		Logger logger = Logger.getInstance();

		while(Button.ENTER.isDown());
		logger.start();
		while (checker.getColorID()!=0 && !Button.ESCAPE.isDown()) {
			if(isStartDash){
				driver.startDash();
				isStartDash = false;
			}
			show();
			if(direction)changeDirection(navL.decision(checker, driver));
			if(!direction)changeDirection(navR.decision(checker, driver));
		}

		// 停止処理
		stop(driver);


		// 20170424 追加
		System.out.println("Press ENTER to send logs");
		System.out.println("Press the others to finish");
		int pressedButton = Button.waitForAnyPress();
		// ENTERが押された時だけログ送信
		if(pressedButton == Button.ID_ENTER){
			logger.stopThread();
			logger.SendLog();
		}
	}

	private void changeDirection(boolean ret){
		if(ret || Button.ENTER.isDown()){
			direction = !direction;
			Sound.beep();
			while(Button.ENTER.isDown());
		}
	}
	private void show() {


		LCD.clear();
		Color color = checker.getColor();

		LCD.drawString("Color ID = ", 0, 0); // カラーIDを表示
		int id = color.getColor();
		LCD.drawInt(id, 11, 0);
		LCD.drawString(colorNames[id], 0, 2);

		LCD.drawString("R", 0, 3); // 赤成分を取得
		LCD.drawInt(color.getRed(), 0, 4);

		LCD.drawString("G", 4, 3); // 緑成分を取得
		LCD.drawInt(color.getGreen(), 4, 4);

		LCD.drawString("B", 8, 3); // 青成分を取得
		LCD.drawInt(color.getBlue(), 8, 4);

		//laptimeのリアルタイム表示
		LCD.drawString("LapTime=" + (System.currentTimeMillis() - getStartTimeMillis())*0.80 + "ms", 0, 6);

	}

	static String[] colorNames = { "Red", "Green", "Blue", "Yellow", "Magenta", "Orange", "White", "Black", "Pink",
			"Gray", "Light gray", "Dark gray", "Cyan" };
}
