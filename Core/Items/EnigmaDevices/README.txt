The files with enigmaDevices names are the differents enigmaDevices.

Duplicate the file ENIGMADEVICES_TEMPLATE and change the values.

name (String)
description (String)
descriptionResolved (String) 
descriptionAfterResolved (String)
itemToGive (String)
correctSequence (int[] see more below)
howManyButtons (int) (= N)
[
	buttonsName (String) 
	buttonsDescription (String)
	buttonsDescriptionResolved (String)
] (--> repeat this block times (example below)

itemToGive MUST have the exact same name as your item. It must also be an item which heritate from the class Pick.

correctSequence must be of this format :
A;B;C;D;etc (ex : 0;3;1;2)
A, B, C, etc, are (int). Each number must be between 0 and howManyButtons - 1. The number correspond to the number of the button.
You can have >= 1 numbers.

Example for the "block" :
3
button1
A button.
A button that has been pressed.
button2
A button.
A button that has been pressed.
button3
A button.
A button that has been pressed.

***********************************************************************

The file ENIGMADEVICES is here to add each enigmaDevices.

One line correspond to 1 enigmaDevices.

The format is this :
FILE;LOCATION

FILE : The relative path of the base file of your enigmaDevices
LOCATION : The name of the location of your enigmaDevices /!\ LOCATION and the name of the location MUST BE THE EXACT SAME
; : is used as a delimitation between the arguments