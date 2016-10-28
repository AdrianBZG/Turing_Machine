# This TM accepts L = { w in Sigma* | w = (a | b | c)* |a| is even }

q0 q1 q2 q3
a b c
a b c
q0
q3
1
q0 a q1 a R
q0 b q0 b R
q0 c q0 c R
q0 $ q2 $ L
q1 a q0 a R
q1 b q1 b R
q1 c q1 c R
q2 a q2 a L
q2 b q2 b L
q2 c q2 c L
q2 $ q3 $ R
