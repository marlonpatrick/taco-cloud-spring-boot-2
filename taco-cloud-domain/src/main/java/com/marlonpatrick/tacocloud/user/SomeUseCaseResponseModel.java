package com.marlonpatrick.tacocloud.user;

public class SomeUseCaseResponseModel {

	/*
	 
	 You might be tempted to have these data structures contain references to Entity objects. 
	 
	 You might think this makes sense because the Entities and the request/response models share so much data. 
	 
	 Avoid this temptation! The purpose of these two objects is very different. 
	 
	 Over time they will change for very different reasons, so tying them together in any way violates the Common Closure 
	 and Single Responsibility Principles. 
	 
	 The result would be lots of tramp data, and lots of conditionals in your code.
	 
	 * */

}
