# This TM receives as parameter a binary string and copies the 1 on the second tape
# with a blank symbol

q0 q1 q2		
1 0
1 0
q0
q2
2						# Number of tapes for the TM
q0 1 $ q0 1 1 R R
q0 0 $ q0 0 $ R S
q0 $ $ q1 $ $ L L
q1 0 1 q1 0 1 L L
q1 1 1 q1 1 1 L L
q1 0 $ q1 0 $ L S
q1 1 $ q1 1 $ L S
q1 $ $ q2 $ $ R R