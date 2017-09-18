package com.alio.graph;

import java.util.ArrayList;

public class GraphBuilder<T> {

	protected class Edge<T1, T2> {

		private T1 mT1;

		private T2 mT2;

		private int mWeight = 1;

		public Edge(T1 t1, T2 t2) {
			super();
			mT1 = t1;
			mT2 = t2;
		}

		public Edge(T1 t1, T2 t2, int weight) {
			super();
			mT1 = t1;
			mT2 = t2;
			mWeight = weight;
		}

		public T1 getT1() {
			return mT1;
		}

		public T2 getT2() {
			return mT2;
		}

		public int getWeight() {
			return mWeight;
		}

		public void setWeight(int mWeight) {
			this.mWeight = mWeight;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Edge) {
				try {
					@SuppressWarnings("unchecked")
					Edge<T, T> cEdge = (Edge<T, T>) obj;
					return mT1.equals(cEdge.mT1) && mT2.equals(cEdge.mT2);
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			} else {
				return super.equals(obj);
			}
		}

	}

	private ArrayList<T> mVertexList;

	private ArrayList<Edge<T, T>> mEdgeList;

	public GraphBuilder() {
		super();
		mVertexList = new ArrayList<>();
		mEdgeList = new ArrayList<>();
	}

	public GraphBuilder<T> insertVertex(T vertex) {
		mVertexList.add(vertex);
		return this;
	}

	public GraphBuilder<T> deleteVertex(T vertex) {
		mEdgeList.remove(vertex);
		return this;
	}

	public GraphBuilder<T> insertEdge(T vertex1, T vertex2) {
		mEdgeList.add(new Edge<T, T>(vertex1, vertex2));
		return this;
	}

	public GraphBuilder<T> insertEdge(T vertex1, T vertex2, int weight) {
		mEdgeList.add(new Edge<T, T>(vertex1, vertex2, weight));
		return this;
	}

	public GraphBuilder<T> deleteEdge(T vertex1, T vertex2) {
		mEdgeList.remove(new Edge<T, T>(vertex1, vertex2));
		return this;
	}

	public Graph<T> build() {
		Graph<T> result = new Graph<>(mVertexList.size());
		result.insertAllVertex(mVertexList);
		for (Edge<T, T> edge : mEdgeList) {
			result.insertEdge(mVertexList.indexOf(edge.getT1()), 
					mVertexList.indexOf(edge.getT2()), edge.getWeight());
		}
		return result;
	}

	public static void main(String[] args) {
		String labels[] = { "1", "2", "3", "4", "5", "6", "7", "8" };// 节点的标识
		GraphBuilder<String> graphBuilder = new GraphBuilder<String>();
		for (String label : labels) {
			graphBuilder.insertVertex(label);// 插入节点
		}
		graphBuilder.insertEdge("1", "2", 1);
		graphBuilder.insertEdge("1", "3", 1);
		graphBuilder.insertEdge("2", "4", 1);
		graphBuilder.insertEdge("2", "5", 1);
		graphBuilder.insertEdge("4", "8", 1);
		graphBuilder.insertEdge("5", "8", 1);
		graphBuilder.insertEdge("3", "6", 1);
		graphBuilder.insertEdge("3", "7", 1);
		graphBuilder.insertEdge("6", "7", 1);
		graphBuilder.insertEdge("2", "1", 1);
		graphBuilder.insertEdge("3", "1", 1);
		graphBuilder.insertEdge("4", "2", 1);
		graphBuilder.insertEdge("5", "2", 1);
		graphBuilder.insertEdge("8", "4", 1);
		graphBuilder.insertEdge("8", "5", 1);
		graphBuilder.insertEdge("7", "3", 1);
		graphBuilder.insertEdge("6", "3", 1);
		graphBuilder.insertEdge("7", "6", 1);
		
		Graph<String> graph = graphBuilder.build();
		System.out.println("深度优先搜索序列为：");
		for (String str : graph.depthFirstSearch()) {
			System.out.print(str + "  ");
		}
		System.out.println();
		System.out.println("广度优先搜索序列为：");
		for (String str : graph.broadFirstSearch()) {
			System.out.print(str + "  ");
		}
	}

}
