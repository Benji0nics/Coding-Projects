###Chess###
This project is a very simple chess board. It does not have an AI opponent, and it does not verify that moves made are legal, as this is just a concise little portfolio piece.

   ---0-------1-------2-------3-------4-------5-------6-------7----
7- |#Rook#||#Knyt#||#Bshp#||#Qeen#||#King#||#Bshp#||#Knyt#||#Rook#| -7
6- |#Pawn#||#Pawn#||#Pawn#||#Pawn#||#Pawn#||#Pawn#||#Pawn#||#Pawn#| -6
5- |      ||::::::||      ||::::::||      ||::::::||      ||::::::| -5
4- |::::::||      ||::::::||      ||::::::||      ||::::::||      | -4
3- |      ||::::::||      ||::::::||      ||::::::||      ||::::::| -3
2- |::::::||      ||::::::||      ||::::::||      ||::::::||      | -2
1- | Pawn || Pawn || Pawn || Pawn || Pawn || Pawn || Pawn || Pawn | -1
0- | Rook || Knyt || Bshp || Qeen || King || Bshp || Knyt || Rook | -0
   ---0-------1-------2-------3-------4-------5-------6-------7----

###How to Play!###
  -When you are prompted to select a piece, input into the command line the coordinates of the desired piece, starting with the horizontal coordinate. 
    -For example, if you wanted to move the left BLACK #ROOK#, you would input 07 to select it. 
  -When prompted for the location to move the selected piece, input the desired coordinates. 
  -Black pieces are denoted with '#' symbols on both sides of their name. White pieces aren't!
  -Pawns who reach the opposite side of the board are promoted to queens automatically.
  -Known Bug: If you input a coordinate that does not correspond to a piece, the game will crash. 
