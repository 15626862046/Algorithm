package com.bj.并查集union;

/*
 * Quick Union - 基于rank的优化 - 路径分裂(Path Spliting)
 * 
 */
public class UnionFind_QU_R_PS extends UnionFind_QU_R {

	public UnionFind_QU_R_PS(int capacity) {
		super(capacity);
	}

	//路径分裂：使路径上的每个节点都指向其祖父节点（parent的parent）
	public int find(int v) {
		rangeCheck(v);
		while(v !=parents[v]) {
			int p = parents[v];//保存父节点
			parents[v] = parents[parents[v]];//指向祖父节点
			v =p;//把父节点赋值给V 使其同样进行此操作
		}
		return v;
	}

}
