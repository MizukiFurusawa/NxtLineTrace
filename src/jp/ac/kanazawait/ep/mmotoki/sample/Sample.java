package jp.ac.kanazawait.ep.mmotoki.sample;

import jp.ac.kanazawait.ep.mmotoki.abst.AbstCar;

/**
 * 単純な走行法で左エッジ走行を行うライントレーサー
 * @author mmotoki
 *
 */
public class Sample {
	public static void main(String[] args) {
		AbstCar car = new SampleCar();
		car.run();
	}

}
