from random import randint as randomInt
# I was having a hard time figuring when parenthesis should and shouldn't be removed so I whipped up this little program
# It's crappy but it gets the job done
for rightOperator in ["", "-", "+", "/", "*"]:
    for innerOperator in ["-", "+", "/", "*"]:
        for leftOperator in ["", "-", "+", "/", "*"]:
            # This will be the final value of the parenthesis expression
            parenthesisNumber = 0
            # This will be the final value of the expression without parenthesis
            normalNumber = 0
            for i in range(0, 100):
                # While statements are to prevent dividing by zero
                if rightOperator == "":
                    rightNumber == ""
                else:
                    rightNumber = randomInt(-100, 100)
                innerRightNumber = randomInt(-100, 100)
                innerLeftNumber = randomInt(-100, 100)
                while innerLeftNumber == 0 and innerOperator == '/':
                    innerLeftNumber = randomInt(-100, 100)
                if leftOperator == "":
                    leftNumber == ""
                else:
                    leftNumber = randomInt(-100, 100)
                while leftNumber == 0 and leftOperator == '/':
                    leftNumber = randomInt(-100, 100) 
                if rightOperator == "":
                    rightNumber == ""
                if leftOperator == "":
                    leftNumber == ""
                # Why have if & else when you can use dangerous exec statements
                try:
                    exec(f"parenthesisNumber = {rightNumber}{rightOperator}({innerRightNumber}{innerOperator}{innerLeftNumber}){leftOperator}{leftNumber}")
                except ZeroDivisionError:
                    exec(f"parenthesisNumber = 0{leftOperator}{leftNumber}")
                exec(f"normalNumber = {rightNumber}{rightOperator}{innerRightNumber}{innerOperator}{innerLeftNumber}{leftOperator}{leftNumber}")
                # I calculate the percent difference of the numbers instead of comparing with == because of float issues
                try:
                    percentDifference = abs((parenthesisNumber - normalNumber) / normalNumber * 100)
                except ZeroDivisionError:
                    percentDifference = 0
                if percentDifference > .01:
                    print(f"Parenthesis needed for {rightOperator}({innerOperator}){leftOperator}")
                    if i != 0:
                        print(f"{rightNumber} {rightOperator} {innerRightNumber} {innerOperator} {innerLeftNumber} {leftOperator} {leftNumber}")
                    print("")
                    break
            if parenthesisNumber == normalNumber:
                    print(f"Parenthesis not needed for {rightOperator}({innerOperator}){leftOperator}")
                    print("")
