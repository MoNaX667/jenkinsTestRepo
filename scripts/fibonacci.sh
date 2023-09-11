# Program for Fibonacci
# Series

# Static input for N
maxValue=$1

# First Number of the
# Fibonacci Series
firstNumber=0

# Second Number of the
# Fibonacci Series
secondNumber=1

echo "Generating Fibonacci series for : "

for (( i=0; i<maxValue; i++ ))
do
    echo -ne "$((i + 1))) $firstNumber \n"
    sum=$((firstNumber + secondNumber))
    firstNumber=$secondNumber
    secondNumber=$sum
done
# End of for loop
