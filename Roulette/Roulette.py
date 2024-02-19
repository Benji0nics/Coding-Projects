#Roulette.py by Benjamin Burnham, written for fun in one day on 1/12/24

import random

wallet = 0
history = []
blacks = [2, 4, 6, 8, 10, 11, 13, 15, 17, 19, 20, 22, 24, 26, 29, 31, 33, 35]
reds = [1, 3, 5, 7, 9, 12, 14, 16, 18, 21, 23, 25, 27, 28, 30, 32, 34, 36]
evens = [x for x in range(1, 37) if x%2 == 0]
odds = [x for x in range(1, 37) if x%2 == 1]
first = [x for x in range(1,37) if x%3 == 0]
second = [x for x in range(1,37) if x%3 == 1]
third = [x for x in range(1,37) if x%3 == 2]
oneThru12 = [x for x in range(1,13)]
thirteenThru24 = [x for x in range(13,25)]
twentyfiveThru36 = [x for x in range(25,37)]
oneThru18 = [x for x in range(1,19)]
nineteenThru36 = [x for x in range(19,37)]
zeroes = [0, 37]

def game():
    global wallet
    print('''
    
    
    
    
    ====== Welcome to Roulette.py, by Benjamin Burnham! ======''')
    wallet = int(input("Enter your starting wallet: "))
    runGameLoop()
    print("Thank you for playing!")

def runGameLoop():
    divider = "========================================"
    global wallet
    while(True):
        winTotal = 0
        showBetTable()
        print("Type 'help' at any time to display the instruction manual. Type 'q' to quit.")
        print("Format your bets like this: [betAmount] [betTarget].   eg: 50 odd  or  40 1-18  or  1 13, 1 14, 1 16, 1 17")
        bets = input("Place your bets: ")

        if(bets.lower() == "q"):
            break 
        elif(bets.lower() == "help"):
            showManual()
        else:
            spinResult = spinWheel()[1:-1] #spinResult is a string
            if spinResult == "-00-":
                spinResultInt = 37          # We represent 00 with the int 37 to distinguish it from 0
            else:
                spinResultInt = int(spinResult)

            # "bets" is list of bets, where each bet is a tuple formatted like this:
            # [betAmount, targets], where targets is always a list of targets.
            bets = bets.split(",")
            for bet in bets:
                bet = bet.strip() # Remove possible whitespace around entry
                bet = bet.split(" ") # Convert each bet into a tuple
                wallet -= int(bet[0])
                targets = interpretTargets(bet[1].split(" ")) # represents a list of targets. User can enter a list of targets by separating with spaces
                if spinResultInt in targets:
                    winTotal += determineMultiplier(targets)*int(bet[0])
            wallet += winTotal
            print('''




            ''')
            print(divider)
            if(winTotal != 0):
                print("You won " + str(winTotal) + "!")
            else:
                print("No winner.")
            print(divider)


# Takes a group of targets and returns a list of ints which
# represents valid targets
def interpretTargets(targets):
    global reds, blacks, evens, odds, first, second, third, oneThru12, thirteenThru24, twentyfiveThru36, oneThru18, nineteenThru36, zeroes

    op = []

    if "00" in targets:
        for i in range(len(targets)):
            if targets[i] == "00":
                targets[i] = "37"


    if targets[0].lower() == "red":
        op = reds
    elif targets[0].lower() == "black":
        op = blacks
    elif targets[0].lower() == "even":
        op = evens
    elif targets[0].lower() == "odd":
        op = odds
    elif targets[0].lower() == "1st":
        op = first
    elif targets[0].lower() == "2nd":
        op = second
    elif targets[0].lower() == "3rd":
        op = third
    elif targets[0].lower() == "1-12":
        op = oneThru12
    elif targets[0].lower() == "13-24":
        op = thirteenThru24
    elif targets[0].lower() == "25-36":
        op = twentyfiveThru36
    else:
        for target in targets:
            op.append(int(target))
    return op

# Takes a list of targets and determines the multiplier value
def determineMultiplier(targets):
    return (36 / len(targets))

# Simulate a single wheel spin, returning a string result
# from 0-37, where 37 is 00. Also adds # flags if black, _ flags if red,
# and - flags if 0/00.
def spinWheel():

    global history, blacks, reds
 
    result = str(random.randint(0,37))
    if result == "37":
        result = "00"
    if (int(result) in blacks):
        result = "#"+result+"#"
    elif (int(result) in reds):
        result = " "+result+" "
    else:
        result = "-"+result+"-"
    history.append(result)

    return result


def showBetTable():
    global wallet
    global history
    print(
        '''
                        Wallet: {credits}

    |-------------------------------------------------------|
    | 00| 3 |#6#| 9 | 12|#15| 18| 21|#24| 27| 30|#33| 36|3rd|
    |---|#2#| 5 |#8#|#11| 14|#17|#20| 23|#26|#29| 32|#35|2nd|
    | 0 | 1 |#4#| 7 |#10|#13| 16| 19|#22| 25|#28|#31| 34|1st|
    |---|---------------|---------------|---------------|---|
        |      1-12     |     13-24     |     25-36     |
        | 1-18  | even  |  red  | black |  odd  | 19-36 |
        -------------------------------------------------

History: {historyList}       
        '''.format(credits=str(wallet), historyList=history))
    
def showManual():
    input('''
============ Roulette.py manual ======================================
Here's how to play! This manual is geared more toward how to run this
specific Python program, not how to play the actual game of roulette. 

The hashtag next to numbers on the roulette table indicate that the number
is black. If there is no hashtag, it's red (or green in the case of 0/00).

When prompted for an input, format your entry like this:
    
Place your bet: [betAmount] [target]
        
where "betAmount" is the amount you want to bet on the target number,
and "target" is the number or collection of numbers you want to bet on.
Separate betAmount and target with a SPACE. 

For "target", you can enter a number, a list of numbers where each number is 
separated by a space, or a keyword on the table, exactly as it appears on the table. 
For instance, if you wanted to bet on Evens, you could type EVEN for your target.
If you wanted to bet on 1-18, you can type 1-18 for your target. 
Your bet will be divided evenly among all of your targets.
          
If you want to get really fancy, you can divide your bet into more
complicated portions by separating each bet by a comma. Think of each entry
separated by commas as a chip you place on the table, where the value of each
chip is betAmount, and the chip's location on the table is determined by target.
          
For instance, if you wanted to bet 5 on evens, and 10 on 13-24, and 8 on [1, #2#, #4#, and 5],
you would enter this at the prompt:
          
Place your bets: 5 even, 10 13-24, 8 1 2 4 5

============== End of Manual, press Enter to exit =================
''')

# Main method to start the program
game()