cse23152, 212497509, Hasan, Sadman Sakib
ksangeev, 213179734, Navaratnam, Sangeev
shfb0019, 213159116, Abdullah, Ashfaq


---------------------
Special Requirements: (optional)
---------------------

**************PLEASE READ THIS BEFORE YOU TEST OUR APPLICATION**************

Our application is called the "SketchPad" and it uses several special features. 
	A new file is named "Untitled" by default.
	When you open or save a file, the the application title changes from "Untitled" to the currently opened or saved filename.

When you hover a button from the toolbar, you will get a tooltip explaining the button feature.
1)**************Shortcut Menu and File Menu and ToolBar Menu**************

	-> New button = This button opens a new "Untitled" paint panel. If something is drawn on the paint panel, the application will prompt to save the current file.
	The accelerator for this button is ctrl + N.

	-> Open button = This button will pop up a file chooser. It includes multiple image formats to choose from.
	The accelerator for this button is ctrl + O.
	
	-> Save button = This button saves any changes made to the current file. If your saving the file for the first time using
	this button, it will prompt you to save as options.
	The accelerator for this button is ctrl + S.
	
	-> Save As button = This button will prompt you to save your current file as a new file and it includes multiple image formats to save as.
	The accelerator for this button is ctrl + shift + S.
	
	-> Print button (only available in the fileMenu) = Print the current file. It includes the basic options to print a file.
	
	The accelerator for this button is ctrl + P.
	
	Show button = This button shows the toolbar. This button text then changes to Hide.
	The accelerator for this button is ctrl + T.
	
	Hide button = This button hides the toolbar. This button text then changes to Show.
	The accelerator for this button is ctrl + T.
2)**************Tools**************

	-> Select pointer button = This button let you select individual drawings or shapes. You also move the individual drawings and shapes in the paint panel using this button.

	-> Draw button = This button gives you two options to choose from: line drawing and free hand drawing. 
	The line drawing button let you draw linear lines. As for the free hand drawing button, it let you draw freely.
	
	-> Add Text button = This button let you add texts on the paint panel. A pop up panel will appear on the screen when 
	this button is clicked. You can then format your text in the pop up panel. In order to add the text to the paint panel,
	you will need to click on the proceed button from the pop up panel. You can paste the text multiple times on the paint panel.
	
	-> Add Image button = This button will pop up a file chooser. It includes multiple image formats to choose from.  
	In order to add the image to the paint panel, you will need to click on the paint panel. You can paste the image multiple times on the paint panel.
	
	
	-> Eraser button = To use this button, you first have to select a drawing or shape using the select pointer button. Erasing a shape or drawing means it is permanently deleted.
	
	-> Select button = This button is used to select a drawing or shape from the paint panel.

	-> Shape drawing button = Gives 3 options to draw shapes: 1. Rectangle, 2. Ellipse, and 3. Any polygon shapes. With option 3, you can create a shape by connecting linear lines end to end and finish it off at the starting point.
	
	-> Undo button = The Undo command. This feature only works when you call undo on free hand drawings, line drawings, all shapes drawings, texts and images.
	
	-> Redo button = The redo command. This feature only works when you call redo on free hand drawings, line drawings, all shapes drawings, texts and images.
	
	-> Stroke size slider = This slider changes the stroke size of the line drawings.

3)**************Attributes**************

	-> Background Fill Color button = This button let you choose a color to fill the paint panel background.

	-> Shape Fill Color button = This button let you select individual drawing or shape using the select pointer button and apply the fill color change.
	
	-> Line Color button = This button let you change the line color for new drawings and shapes. You can also select 
	individual drawing or shape using the select pointer button and apply the line color change.
	
	-> Rotate button = This button has 4 options, 1. Rotate right by 90 degree 2. Rotate left by 90 degree 3. Flip horizontal 4. Flip vertical
	All these options apply to all the drawings and shapes on the paint panel.
	You can also choose individual drawing or shape using the select pointer button and apply the rotation.
	
	-> Translate button = This button let you translate all the drawings and shapes from the paint panel given the x and y translating factors. 
	You can also choose individual drawing or shape using the select pointer button and apply the translation.
	
	-> Scale button = This button let you scale all the drawings and shapes from the paint panel given the x and y scaling factors. 
	You can also choose individual drawing or shape using the select pointer button and apply the scaling.

4)**************Paint panel**************

	-> Paint panel = This is where all the drawings are painted. 
			
5)**************Zoom ToolBar**************

	-> Zoom slider = This slider let you zoom in and zoom out the paint panel.
 
-------
Design:
-------

(Describe the motivation behind your software design. Why did you use
the JFC/Swing components that you did, and why did you organise them
that way? Briefly justify your design.)

We looked at different drawing applications and got ideas which inspired us to design our application. We decided to include the common features such as undo, redo, zoom in/out, drawings, etc.
We wanted to make our application user friendly by putting tips so the user knows how the features work.
---------------
Communications:
---------------

October 20, 2015; 4:00pm-6:00pm
Sakib, Ashfaq, Sangeev met to create the group and brainstorm the ideas and made a draft.

October 22, 2015; 11:00am
Ashfaq created whats app group for communication purposes.

October 24, 2015; 10:30am-3:30pm
Sakib, Ashfaq and Sangeev met up and took the ideas from the draft and divided the work between the three of us.

October 29, 2015: 9:00am-5:30pm
Sakib, Ashfaq and Sangeev met up and were working on their own responsibility and helping each other out.

November 1, 2015: 10:00am-1:00pm
Ashfaq and Sangeev met up and worked on their own responsibility.

November 2, 2015: 10:00am-2:30pm
Sakib, Ashfaq and Sangeev met up and started improving the application.

November 5, 2015: 4:00pm-8:00pm
Sakib, Ashfaq and Sangeev met up and discussed new and better features to implement.

November 7, 2015: 2:30pm-10:00pm
Sakib, Ashfaq and Sangeev met up to finish the implementation of the undo and redo buttons.

November 10, 2015: 5:30pm-10:00pm
Sakib, Ashfaq and Sangeev met up to finish the implementation the main toolbar.

November 15, 2015: 9:00am-2:30pm
Sakib, Ashfaq and Sangeev met up to finish the implementation the rotate toolbar.

November 18, 2015: 1:00pm-5:30pm
Sakib, Ashfaq and Sangeev met up to work on the zoom.

November 25, 2015: 10:00am-2:30pm
Sakib, Ashfaq and Sangeev met up and went to random people to get them to test the application and get user feedback.

November 28, 2015: 12:00pm
Sakib, Ashfaq and Sangeev met up to improve the application based on the user feedback.

December 1: 2:30pm
Sakib, Ashfaq and Sangeev met up to test the application and fix errors.

December 2: 11:30pm
Sakib, Ashfaq and Sangeev met up with the TA to get user feedback from her and took her feedback positively and improved the application.

December 4: 2:30pm
Sakib, Ashfaq and Sangeev met up to do final testing before submitting.

-----------------
Responsibilities:
-----------------

Sakib:
- Coded the application
- Debugging and Testing
- GUI Functionality and Design
- Implemented the the toolbars and the paint panel

Ashfaq:
- Coded the application
- Debugging and Testing
- GUI Functionality and Design
- Implemented the the toolbars and the paint panel

Sangeev:
- Coded the application
- Debugging and Testing
- GUI Functionality and Design
- Implemented the the toolbars and the paint panel
