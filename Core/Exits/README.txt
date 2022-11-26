One line correspond to 1 exit.

The format is this :
EXIT_TYPE;FIRST_LOCATION;SECOND_LOCATION;CAN_A_TO_B;CAN_B_TO_A(;ITEMTOOPEN)

EXIT_TYPE : Write the exit type. The available exit types are written below.
FIRST_LOCATION : The name of your first location (must be IDENTICAL to the name in Locations/)
SECOND_LOCATION : Same but for the second location
CAN_A_TO_B : To indicate if you can go from FIRST_LOCATION to SECOND_LOCATION. "true" if true, "false" if false
CAN_B_TO_A : Same but from SECOND_LOCATION to FIRST_LOCATION
ITEMTOOPEN : (only for exitItem) Indicates an item that needs to be picked before the door opens again.
; : is used as a delimitation between the arguments

exit : Basic exit. If you close a way it can never be reopened.
exitEnemy : If you close 1 or 2 ways it will reopen when all enemies are defeated.
exitItem : If you close 1 or 2 ways it will reopen when ITEMTOOPEN is picked.
exitPillar : Special exit. It will open its direction A->B when all statuette are put on the right pillar in room A, 
			 and in direction B->A when the enemies in B are all defeated.