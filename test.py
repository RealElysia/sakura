#! /usr/bin/env python3

def func_1():
	for x in range(1, 10):
		for y in range(x, 10):
			print('%d*%d=%d' % (y, x, x*y),end=' ')
		print(' ')

def func_2():
	a = 0
	sum = 0
	while a <= 100:
		sum = sum + a
		a += 1
	print(sum)

func_1()
print('\n')
func_2()