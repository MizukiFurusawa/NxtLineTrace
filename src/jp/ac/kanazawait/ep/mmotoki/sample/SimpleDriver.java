package jp.ac.kanazawait.ep.mmotoki.sample;


import jp.ac.kanazawait.ep.mmotoki.abst.AbstDriver;
import lejos.util.Delay;


/**
 * 単純な走行
 * @author mmotoki
 *
 */
public class SimpleDriver extends AbstDriver {

	//最高速度に対する割合
	double speedGain = 1.20;

	int delay = 4;

	//通常トレース速度
	int starightSpeed = 480;

	//右旋回の左右それぞれのスピード
	int turnRightLSpeed = 480;
	int turnRightRSpeed = 120;

	//左ライントレース時に、
	//青色モード切替した時の、
	//左右それぞれのスピード
	int modeChangeRSpeed = 480;
	int modeChangeLSpeed = 60;

	//左ライントレース時に、
	//青色モード切替した時、黒色を検出した時の、
	//左右それぞれのスピード
	int modeChangeRSpeed2 = 480;
	int modeChangeLSpeed2 = 30;

	private int getSpeed(int sp){
		return (int)(speedGain * sp);
	}

	//通常の右旋回
	@Override
	public void turnRight() {
		setSpeed(getSpeed(turnRightLSpeed), getSpeed(turnRightRSpeed));
		forward();
		Delay.msDelay(delay);
	}

	//通常の左旋回
	@Override
	public void turnLeft() {
		setSpeed(getSpeed(turnRightRSpeed), getSpeed(turnRightLSpeed));
		forward();
		Delay.msDelay(delay);
	}

	//直進
	// 20160509追加
	@Override
	public void goStraight() {
		setSpeed(getSpeed(starightSpeed), getSpeed(starightSpeed));
		forward();
		Delay.msDelay(delay);
	}

	//青色検出時のモード切替前処理
	@Override
	public void turnLeftQuick() {
		setSpeed(getSpeed(modeChangeLSpeed), getSpeed(modeChangeRSpeed));
		forward();
		Delay.msDelay(delay);
	}

	//青色検出時のモード切替前処理
	@Override
	public void turnRightQuick() {
		setSpeed(getSpeed(modeChangeRSpeed), getSpeed(modeChangeLSpeed));
		forward();
		Delay.msDelay(delay);
	}

	//青色検出時、黒色検出した時のモード切替前処理
	@Override
	public void turnLeftQuick2() {
		setSpeed(getSpeed(modeChangeLSpeed2), getSpeed(modeChangeRSpeed2));
		forward();
		Delay.msDelay(delay);
	}

	//青色検出時、黒色検出した時のモード切替前処理
	@Override
	public void turnRightQuick2() {
		setSpeed(getSpeed(modeChangeRSpeed2), getSpeed(modeChangeLSpeed2));
		forward();
		Delay.msDelay(delay);
	}

	//以下未使用メソッド群
	@Override
	public void turnLeftSlack() {
		stop();
	}

	@Override
	public void turnRightSlack() {
		stop();
	}

	@Override
	public void goStraightFast() {
		stop();
	}

	@Override
	public void goStraightSlow() {
		stop();
	}

	@Override
	public void start() {
		stop();
	}
}
