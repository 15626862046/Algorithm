package com.bj.并查集union;
/*
 * Quick Union - 基于rank的优化
 */
public class UnionFind_QU_R extends UnionFind_QU {

	private int[] ranks;//树高
	
	public UnionFind_QU_R(int capacity) {
		super(capacity);
		ranks = new int[capacity];
		for (int i = 0; i < ranks.length; i++) {
			 ranks[i] = 1;//初始化高度为1
		}
	}
	/*
	 * 树高度低加入到树高的，高度不变
	 * 如果高度一样，则默认左嫁接右，高度加1
	 */
	public void union(int v1, int v2) {
		int p1 = find(v1);
		int p2 = find(v2);
		if(p1==p2) return;
		
		if(ranks[p1] < ranks[p2]) {
			parents[p1] = p2;
		}else if(ranks[p1] > ranks[p2]){
			parents[p2] =p1;
		}else {
			parents[p1] = p2;
			ranks[p2] +=1;
		}
	}
	
	

}
