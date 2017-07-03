package jp.ac.kanazawait.ep.mmotoki.sample;

import jp.ac.kanazawait.ep.mmotoki.abst.AbstDriver;
import jp.ac.kanazawait.ep.mmotoki.abst.AbstNavigator;
import jp.ac.kanazawait.ep.mmotoki.addon.SensorChecker;

/**
 * 右エッジ走行のためのNavigatorクラス
 * @author mmotoki
 *
 */
public class RightEdgeTracer extends AbstNavigator {

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
			// 右エッジ走行：白→右より走行
			if(colorID == 6 || colorID == 8) {
				driver.turnRight();
			}
			// 右エッジ走行：黒→左より走行
			else if(colorID == 7) {
				driver.turnLeftQuick();
			}
			else {
				driver.goStraight();
			}
			break;

		//青色走行エッジ切り替えモード
		case 1:

			//青色→急な右旋回
			if(colorID == 1 || colorID == 2){
				driver.turnRightQuick();
				break;
			}
			//黒色→曲がりきれていないので更に右旋回する
			else if(colorID == 7) {
				driver.turnRightQuick2();
				break;
			}
			//白色→曲がりきれている
			if(colorID == 6 || colorID == 8) {
				modeChangePattern = 2;
				break;
			}
			break;

		//黒が来るまで左旋回
		case 2:
			driver.turnLeftQuick();
			if(colorID == 7) {
				modeChangePattern = 3;
				break;
			}
			break;

		//白が来るまで素早く右旋回→左エッジ走行に移動
		case 3:
			driver.turnRightQuick2();
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
