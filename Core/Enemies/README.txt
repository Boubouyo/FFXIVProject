The files with enemy names are the base enemies.

Duplicate the file ENEMY_TEMPLATE and change the values.

enemyHealthPoints (int)
enemyAttack (int)
enemyDescription (String)

The name of the file is also the name of the enemy.

***********************************************************************

The file ENEMIES is here to add each enemy based on an enemy base.

One line correspond to 1 enemy.

The format is this :
NAME;FILE;LOCATION

NAME : Put the name of your enemy (ex : golemA)
FILE : The relative path of the base file of your enemy (ex : golem)
LOCATION : The name of the location of your enemy /!\ LOCATION and the name of the location MUST BE THE EXACT SAME
; : is used as a delimitation between the arguments