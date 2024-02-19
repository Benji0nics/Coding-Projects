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
