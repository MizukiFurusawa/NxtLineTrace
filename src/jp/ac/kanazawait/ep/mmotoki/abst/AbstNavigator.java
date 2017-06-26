package jp.ac.kanazawait.ep.mmotoki.abst;

import jp.ac.kanazawait.ep.mmotoki.addon.SensorChecker;

/**
 * Navigatorのための抽象クラス．
 * 具象クラスでdecisionメソッドを実装すること．
 * @author mmotoki
 *
 */
public abstract class AbstNavigator {
	/**
	 * 走行戦略を具体化するメソッド
	 * @param checker	カラーセンサー測定スレッドのクラス
	 * @param driver	走行のための具象クラス
	 */
	public abstract boolean decision(SensorChecker checker, AbstDriver driver);
}
