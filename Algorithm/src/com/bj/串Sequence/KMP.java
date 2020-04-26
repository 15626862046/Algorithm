package com.bj.串Sequence;
/**
 *  串匹配 --KMP
 * @author hee
 *
 */
public class KMP {
	
	public static int indexOf(String text,String patten) {
		if(text == null || patten == null ) return -1;
		int tlen = text.length();
		int plen = patten.length();
		if(tlen == 0 || plen == 0 || tlen < plen) return -1;
		int [] next = next(patten);
		int pi=0, ti=0;
		while(pi < plen && ti -pi <= tlen - plen) {
			if(pi <0 || text.charAt(ti) == patten.charAt(pi)) {
				ti++;
				pi++;
			}else {
				pi = next[pi];
			}
		}
		return pi == plen ? ti - pi :-1;
	}
	private static int[] next(String patten) {
		int len = patten.length();
		int[] next = new int[len];
		int i = 0;
		int n = next[i] = -1;
		int imax = len-1;
		while(i < imax) {
			if(n <0 || patten.charAt(i) == patten.charAt(n)) {
				i++;
				n++;
				if(n <0 || patten.charAt(i) == patten.charAt(n)) {
					next[i] = next[n];
				}else {
					next[i] = n;
				}
			}else {
				n = next[n];
			}
		}
		return next;
	}
	private static int[] next1(String patten) {
		int len = patten.length();
		int[] next = new int[len];
		int i = 0;
		int n = next[i] = -1;
		int imax = len-1;
		while(i < imax) {
			if(n <0 || patten.charAt(i) == patten.charAt(n)) {
				next[++i] = ++n;
			}else {
				n = next[n];
			}
		}
		return next;
	}

}
