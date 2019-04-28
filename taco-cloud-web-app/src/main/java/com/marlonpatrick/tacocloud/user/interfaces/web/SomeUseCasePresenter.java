package com.marlonpatrick.tacocloud.user.interfaces.web;

public class SomeUseCasePresenter {
	
	/*
	 
	The job of the Presenter is to repackage the OutputData into viewable form as the ViewModel , which is yet another plain old Java object. The ViewModel contains mostly Strings and flags that the View uses to display the data. Whereas the OutputData may contain Date objects, the Presenter will load the ViewModel with corresponding Strings already formatted properly for the user. The same is true of Currency objects or any other business-related data. Button and MenuItem names are placed in the ViewModel , as are flags that tell the View whether those Buttons and MenuItems
	
	should be gray. This leaves the View with almost nothing to do other than to move the data from the ViewModel into the HTML page.

	The Presenter is the testable object. Its job is to accept data from the application and format it for presentation so that the View can simply move it to the screen. For example, if the application wants a date displayed in a field, it will hand the Presenter a Date object. The Presenter will then format that data into an appropriate string and place it in a simple data structure called the View Model, where the View can find it.
	
	Every button on the screen will have a name. That name will be a string in the View Model, placed there by the presenter. If those buttons should be grayed out, the Presenter will set an appropriate boolean flag in the View model. Every menu item name is a string in the View model, loaded by the Presenter. The names for every radio button, check box, and text field are loaded, by the Presenter, into appropriate strings and booleans in the View model.
	
	Anything and everything that appears on the screen, and that the application has some kind of control over, is represented in the View Model as a string, or a boolean, or an enum. Nothing is left for the View to do other than to load the data from the View Model into the screen. Thus the View is humble.

	 * */
}
