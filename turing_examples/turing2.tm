# This TM receives as parameter a binary string and copies the 1 on the second tape
# with a blank symbol

q0						# Initial state
1 0
q0
q0
2						# Number of tapes
q0 1 $ q0 1 1 R R
q0 0 $ q0 0 $ R S