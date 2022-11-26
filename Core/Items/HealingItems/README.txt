The files with healing item names are the base healing items.

Duplicate the file HEALINGITEM_TEMPLATE and change the values.

name (String)
description (String)
healPower (int)

The name of the file is also the name of the healing item.

***********************************************************************

The file HEALINGITEMS is here to add each healing item based on an healing item base.

One line correspond to 1 healing item.

The format is this :
FILE;LOCATION;ISPICKABLE

FILE : The relative path of the base file of your healing item (the name of the file since it's in a the same directory)
LOCATION : The name of the location of your healing item /!\ LOCATION and the name of the location MUST BE THE EXACT SAME
ISPICKABLE : Let you know if the item can be picked from the start. "true" if true, "false" if false
; : is used as a delimitation between the arguments