package com.bj.并查集union;

/*
 * Quick Union - 基于rank的优化 - 路径压缩(Path Compression)
 */
public class UnionFind_QU_R_PC extends UnionFind_QU_R {

	public UnionFind_QU_R_PC(int capacity) {
		super(capacity);
	}

	//路径压缩使路径上的所有节点都指向根节点，所以实现成本稍高
	@Override
	public int find(int v) {// v == 1, parents[v] == 2
		rangeCheck(v);
		if (v != parents[v]) {
			parents[v] = find(parents[v]);
		}
		return parents[v];
	}
}
