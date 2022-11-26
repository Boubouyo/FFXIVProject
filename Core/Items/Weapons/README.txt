The files with weapons names are the base weapons.

Duplicate the file WEAPONS and change the values.

name (String)
description (String)
attack (int)
durability (int)

The name of the file is also the name of the weapon.

***********************************************************************

The file WEAPONS is here to add each weapon based on a weapon base.

One line correspond to 1 weapon.

The format is this :
FILE;LOCATION;ISPICKABLE

FILE : The relative path of the base file of your weapon (the name of the file since it's in a the same directory)
LOCATION : The name of the location of your weapon /!\ LOCATION and the name of the location MUST BE THE EXACT SAME
ISPICKABLE : Let you know if the weapon can be picked from the start. "true" if true, "false" if false
; : is used as a delimitation between the arguments