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
	private static final double SPEED_GAIN = 1.00;
	private static double speedGain = SPEED_GAIN;

	public static void setGain(double gain){
		speedGain = gain;
	}

	public static void resetGain(){
		speedGain = SPEED_GAIN;
	}

	int delay = 3;

	//通常トレース速度
	int starightSpeed = 520;

	//右旋回の左右それぞれのスピード
	int turnRightLSpeed = 530;
	int turnRightRSpeed = 170;

	//左ライントレース時に、
	//青色モード切替した時の
	//左右それぞれのスピード
	int modeChangeRSpeed = 530;
	int modeChangeLSpeed = 170;

	//左ライントレース時に、
	//青色モード切替した時、黒色を検出した時の、
	//左右それぞれのスピード
	int modeChangeRSpeed2 = 530;
	int modeChangeLSpeed2 = 80;

	//スタートダッシュのパラーメータ
	int speedDash = 800;
	int speedDashTime = 670; //ms

	private int getSpeed(int sp){
		return (int)(speedGain * sp);
	}

	@Override
	public void startDash(){
		setSpeed(speedDash,speedDash);
		forward();
		Delay.msDelay(speedDashTime);
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
		Delay.msDelay(delay/2);
	}

	//青色検出時のモード切替前処理
	@Override
	public void turnRightQuick() {
		setSpeed(getSpeed(modeChangeRSpeed), getSpeed(modeChangeLSpeed));
		forward();
		Delay.msDelay(delay/2);
	}

	//青色検出時、黒色検出した時のモード切替前処理
	@Override
	public void turnLeftQuick2() {
		setSpeed(getSpeed(modeChangeLSpeed2), getSpeed(modeChangeRSpeed2));
		forward();
		Delay.msDelay(delay/2);
	}

	//青色検出時、黒色検出した時のモード切替前処理
	@Override
	public void turnRightQuick2() {
		setSpeed(getSpeed(modeChangeRSpeed2), getSpeed(modeChangeLSpeed2));
		forward();
		Delay.msDelay(delay/2);
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
