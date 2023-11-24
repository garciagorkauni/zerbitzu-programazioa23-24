print("This program will calculate the correct result for the first exercise of the program.")
print("What is the total sum of the numbers divisible by 7 from 1 to 5000?")
total_sum = 0
for i in range(1, 5000):
    if i%7 == 0:
        total_sum = total_sum + i

print("The result is: " + str(total_sum))