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
    echo -n "$firstNumber) $firstNumber "
    sum=$((a + b))
    firstNumber=$secondNumber
    b=$sum
done
# End of for loop