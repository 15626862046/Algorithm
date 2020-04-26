package com.bj.串Sequence;
/**
 * 串匹配 --蛮力
 * @author hee
 *
 */
public class BruteForce {

	/**
	 * 蛮力1
	 * @param text
	 * @param patten
	 * @return
	 */
	public static int indexOf01(String text,String patten) {
		if(text == null || patten == null ) return -1;
		int tlen = text.length();
		int plen = patten.length();
		if(tlen == 0 || plen == 0 || tlen < plen) return -1;
		int pi=0, ti=0;
		while(pi < plen && ti < tlen) {
			if(text.charAt(ti) == patten.charAt(pi)) {
				ti++;
				pi++;
			}else {
				ti -= pi - 1;
				pi = 0;
			}
		}
		return pi == plen ? ti - pi :-1;
	}
	/**蛮力1
	 * 恰当的时候可以提前退出，减少比较次数
	 * ti的退出条件可以从ti< tlen改为ti–pi<= tlen–plen
	 * ti–pi是指每一轮比较中text 首个比较字符的位置
	 * @param patten
	 * @return
	 */
	public static int indexOf02(String text,String patten) {
		if(text == null || patten == null ) return -1;
		int tlen = text.length();
		int plen = patten.length();
		if(tlen == 0 || plen == 0 || tlen < plen) return -1;
		int pi=0, ti=0;
		while(pi < plen && ti -pi <= tlen - plen) {
			if(text.charAt(ti) == patten.charAt(pi)) {
				ti++;
				pi++;
			}else {
				ti -= pi - 1;
				pi = 0;
			}
		}
		return pi == plen ? ti - pi :-1;
	}
	/**
	 * 蛮力2
	 * @param text
	 * @param patten
	 * @return
	 */
	public static int indexOf(String text,String patten) {
		if(text == null || patten == null ) return -1;
		int tlen = text.length();
		int plen = patten.length();
		if(tlen == 0 || plen == 0 || tlen < plen) return -1;
		for (int ti = 0; ti <= tlen - plen; ti++) {
			int pi = 0;
			for (; pi < plen; pi++) {
				if(text.charAt(ti+pi) != patten.charAt(pi)) break;
			}
			if(pi == plen) return ti;
		}
		return -1;
	}
}
