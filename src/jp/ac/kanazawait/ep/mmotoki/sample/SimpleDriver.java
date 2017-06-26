package jp.ac.kanazawait.ep.mmotoki.sample;


import jp.ac.kanazawait.ep.mmotoki.abst.AbstDriver;
import lejos.util.Delay;

/**
 * 単純な走行
 * @author mmotoki
 *
 */
public class SimpleDriver extends AbstDriver {

	@Override
	public void turnLeft() {
		setSpeed(170, 500);
		forward();
	}

	@Override
	public void turnRight() {
		setSpeed(500, 170);
		forward();
	}

	// 20160509追加
	@Override
	public void goStraight() {
		setSpeed(450, 450);
		forward();
	}

	@Override
	public void turnLeftQuick() {
		setSpeed(20, 350);
		forward();
		Delay.msDelay(10);
		//stop();
	}

	@Override
	public void turnRightQuick() {
		setSpeed(350, 20);
		forward();
		Delay.msDelay(10);
		//stop();
	}


	@Override
	public void turnLeftSlack() {
		setSpeed(100, 450);
		forward();
		Delay.msDelay(300);
		setSpeed(450, 80);
		forward();
		Delay.msDelay(380);
		//stop();
	}


	@Override
	public void turnRightSlack() {
		setSpeed(450, 100);
		forward();
		Delay.msDelay(300);
		setSpeed(80, 450);
		forward();
		Delay.msDelay(380);
		//stop();
	}

	@Override
	public void goStraightFast() {
		//stop();
	}

	@Override
	public void goStraightSlow() {
		//stop();
	}

	@Override
	public void start() {
		setSpeed(500);
		forward();
		Delay.msDelay(400);
		//stop();
	}

}
