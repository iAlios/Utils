Utils
=====

  这是一个工具集合.
  
  MNumberList 类主要是用来实现多断数字的集合列表, 比如"[10-100]/10,[100-5000]/100", "1-10,15,20,50"，"1-10,[10-100]/10,500"等，自由组合的数字集合。
  具体操作如下：
  
  
		System.out.println("========3,[12-32]/2,45=======");
		SNumber.parseNumber("3,[12-32]/2,45").forEach(System.out::println);

		System.out.println("=======3,12-32/2,45========");
		SNumber.parseNumber("3,12-32/2,45").forEach(System.out::println);

		System.out.println("=======3,[12-32],45========");
		SNumber.parseNumber("3,[12-32],45").forEach(System.out::println);

		System.out.println("=======3,12-32,45========");
		SNumber.parseNumber("3,12-32,45").forEach(System.out::println);
	
	
  		String num1 = "1-10,15,20,50";
		String num2 = "[10-100]/10,[100-5000]/100,5200";
		MNumberList numberList = new MNumberList(num1);
		System.out.println(numberList.next(4)); // 5
		System.out.println(numberList.next(18)); // 将18转为20，因此后一个内容为50
		System.out.println(numberList.next(10)); // 15
		System.out.println(numberList.prev(15)); // 10
		System.out.println(numberList.prev(12)); // 将12转为10，因此前一个内容为9
		System.out.println(numberList.prev(9)); // 8
		numberList = new MNumberList(num2);
		System.out.println(numberList.next(4)); // 20
		System.out.println(numberList.next(12)); // 20
		System.out.println(numberList.next(10000)); // 5200
		System.out.println(numberList.prev(580)); // 500
		System.out.println(numberList.next(100)); // 200
		System.out.println(numberList.next(98)); // 200
	
	在这里主要是将数据就近原则，然后进行这种数据的集合。方便集合获取某个数据的前一个数字，以及后一个数字的获取。以及将数据转为规定集合中的数据。

BitUtil 主要是用来进行二进制数据处理，将 byte 数据转为8位 boolean 数据


HexDump 为十六进制与byte进行转换的工具


IntUtils 主要是用来将 int 数据转换为 byte[] 的工具

Graph 为图结构实现，其使用方式如下：

		String labels[] = { "1", "2", "3", "4", "5", "6", "7", "8" };// 节点的标识
		GraphBuilder<String> graphBuilder = new GraphBuilder<String>();
		for (String label : labels) {
			graphBuilder.insertVertex(label);// 插入节点
		}
		graphBuilder.insertEdge("1", "2");
		graphBuilder.insertEdge("1", "3");
		graphBuilder.insertEdge("2", "4");
		graphBuilder.insertEdge("2", "5");
		graphBuilder.insertEdge("4", "8");
		graphBuilder.insertEdge("5", "8");
		graphBuilder.insertEdge("3", "6");
		graphBuilder.insertEdge("3", "7");
		graphBuilder.insertEdge("6", "7");
		
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

