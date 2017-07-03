package jp.ac.kanazawait.ep.mmotoki.sample;

import jp.ac.kanazawait.ep.mmotoki.abst.AbstDriver;
import jp.ac.kanazawait.ep.mmotoki.abst.AbstNavigator;
import jp.ac.kanazawait.ep.mmotoki.addon.SensorChecker;

/**
 * 左エッジ走行のためのNavigatorクラス
 * @author mmotoki
 *
 */
public class LeftEdgeTracer extends AbstNavigator {

	int modeChangePattern = 0;

	@Override
	public boolean decision(SensorChecker checker, AbstDriver driver) {

		//色情報取得
		int colorID = checker.getColorID();

		switch(modeChangePattern){

		//通常トレース
		case 0:
			//青色で走行エッジ切り替え
			if(colorID == 1 || colorID == 2){
				modeChangePattern = 1;
				break;
			}
			// 左エッジ走行：白→左より走行　
			if(colorID == 6 || colorID == 8) {
				driver.turnLeft();
			}
			// 左エッジ走行：黒→右より走行　
			else if(colorID == 7) {
				driver.turnRightQuick();
			}
			else {
				driver.goStraight();
			}
			break;

		//青色走行エッジ切り替えモード
		case 1:

			//青色→急な左旋回
			if(colorID == 1 || colorID == 2){
				driver.turnLeft();
				break;
			}
			//黒色→曲がりきれていないので更に左旋回する
			else if(colorID == 7) {
				driver.turnLeftQuick2();
				break;
			}
			//白色→曲がりきれている
			if(colorID == 6 || colorID == 8) {
				modeChangePattern = 2;
				break;
			}
			break;

		//黒が来るまで右旋回
		case 2:
			driver.turnRightQuick();
			if(colorID == 7) {
				modeChangePattern = 3;
				break;
			}
			break;

		//白が来るまで素早く左旋回→右エッジ走行に移動
		case 3:
			driver.turnLeftQuick2();
			if(colorID == 6 || colorID == 8) {
				modeChangePattern = 0;
				driver.goStraight();
				return true;
			}
			break;
		}
		return false;
	}
}
