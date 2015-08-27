% === FACTS ===
fork(o).
knife(o).
spoon(o).
smallKnife(o).

plate(o).
bowl(o).
saucer(o).
pastaPlate(o).
soupPlate(o).

waterGlass(o).
wineGlass(o).
teaCup(o).

there_is_a_fork.
there_is_a_knife.
there_is_a_spoon.
there_is_a_smallKnife.
there_is_a_plate.
there_is_a_bowl.
there_is_a_saucer.
there_is_a_pastaPlate.
there_is_a_soupPlate.
there_is_a_waterGlass.
there_is_a_wineGlass.
there_is_a_teaCup.

% position(i, i, i, o).
left_of(o, o).
above_of(o, o).
on(o, o).
between_of(o, o, o).
top_left_of(o, o).
top_right_of(o, o).

food_utensil(o).
food_container(o).
drink_container(o).

% === MODES ===
fork('+').
knife('+').
spoon('+').
smallKnife('+').
plate('+').
bowl('+').
saucer('+').
pastaPlate('+').
soupPlate('+').
waterGlass('+').
wineGlass('+').
teaCup('+').

there_is_a_fork.
there_is_a_knife.
there_is_a_spoon.
there_is_a_smallKnife.
there_is_a_plate.
there_is_a_bowl.
there_is_a_pastaPlate.
there_is_a_soupPlate.
there_is_a_saucer.
there_is_a_waterGlass.
there_is_a_wineGlass.
there_is_a_teaCup.

left_of('+', '-').
above_of('+', '+').
on('+', '+').


food_utensil('+').
food_container('+').
drink_container('+').

top_left_of('+','+').
top_right_of('+', '+').

% === BACKGROUND ===

left_of(A, B) :-
	position(X1, Y, _, A),
	position(X2, Y, _, B),
	plus(X1, 1, Result),
	Result == X2.

above_of(A, B) :-
	position(X, Y1, _, A),
	position(X, Y2, _, B),
	plus(Y1, 1, Result),
	Result == Y2.

on(A, B) :-
	position(X, Y, Z1, A),
	position(X, Y, Z2, B),
	plus(Z1, 1, Result),
	Result == Z2.

top_left_of(A, B) :-
	position(X1, Y1, _, A),
	position(X2, Y2, _, B),
	plus(X1, 1, XResult),
	plus(Y2, 1, YResult),
	XResult == X2,
	YResult == Y1.

top_right_of(A, B) :-
	position(X1, Y1, _, A),
	position(X2, Y2, _, B),
	plus(X2, 1, XResult),
	plus(Y2, 1, YResult),
	XResult == X1,
	YResult == Y1.


	

food_utensil(X) :- spoon(X).
food_utensil(X) :- fork(X).
food_utensil(X) :- knife(X).
food_utensil(X) :- smallKnife(X).

food_container(X) :- plate(X).
food_container(X) :- bowl(X).
food_container(X) :- pastaPlate(X).
food_container(X) :- soupPlate(X).
food_container(X) :- saucer(X).

drink_container(X) :- wineGlass(X).
drink_container(X) :- waterGlass(X).
drink_container(X) :- teaCup(X).

there_is_a_fork :- fork(X), position(_, _, _, X).
there_is_a_knife :- knife(X), position(_, _, _, X).
there_is_a_spoon :- spoon(X), position(_, _, _, X).
there_is_a_smallKnife :- smallKnife(X), position(_, _, _, X).
there_is_a_plate :- plate(X), position(_, _, _, X).
there_is_a_bowl :- bowl(X), position(_, _, _, X).
there_is_a_pastaPlate :- pastaPlate(X), position(_, _, _, X).
there_is_a_soupPlate :- soupPlate(X), position(_, _, _, X).
there_is_a_saucer :- saucer(X), position(_, _, _, X).
there_is_a_waterGlass :- waterGlass(X), position(_, _, _, X).
there_is_a_wineGlass :- wineGlass(X), position(_, _, _, X).
there_is_a_teaCup :- teaCup(X), position(_, _, _, X).

% ============ 
% Example 1
teaCup(cup).
plate(plate).
knife(knife).

position(0, 0, 0, plate).
position(1, 0, 0, knife).
position(1, 1, 0, cup).

% ============ 
% Example 2

waterGlass(water).
plate(plate).
knife(knife).

position(0, 0, 0, plate).
position(1, 0, 0, knife).
position(1, 1, 0, water).

% ============ 
% Example 3

fork(fork).
knife(knife).
plate(plate).
teaCup(cup).

position(0, 0, 0, fork).
position(1, 0, 0, plate).
position(2, 0, 0, knife).
position(2, 1, 0, cup).

% ============ 
% Example 4

fork(fork).
knife(knife).
plate(plate).
waterGlass(water).

position(0, 0, 0, fork).
position(1, 0, 0, plate).
position(2, 0, 0, knife).
position(2, 1, 0, water).

% ============ 
% Example 5

knife(knife).
spoon(spoon).
plate(plate).
bowl(bowl).
waterGlass(water).

position(0, 0, 0, plate).
position(0, 0, 1, bowl).
position(1, 0, 0, knife).
position(1, 1, 0, water).
position(2, 0, 0, spoon).

% ============ 
% Example 6

knife(knife).
spoon(spoon).
plate(plate).
bowl(bowl).
teaCup(cup).

position(0, 0, 0, plate).
position(0, 0, 1, bowl).
position(1, 0, 0, knife).
position(1, 1, 0, cup).
position(2, 0, 0, spoon).

% ============ 
% Example 7

spoon(spoon).
bowl(bowl).
teaCup(cup).

position(0, 0, 0, bowl).
position(1, 0, 0, spoon).
position(1, 1, 0, cup).

% ============ 
% Example 8

spoon(spoon).
bowl(bowl).
waterGlass(water).

position(0, 0, 0, bowl).
position(1, 0, 0, spoon).
position(1, 1, 0, water).

% ============ 
% Example 9

fork(fork).
knife(knife).
spoon(spoon).
plate(plate).
bowl(bowl).
waterGlass(water).

position(0, 0, 0, fork).
position(1, 0, 0, plate).
position(1, 0, 1, bowl).
position(2, 0, 0, knife).
position(2, 1, 0, water).
position(3, 0, 0, spoon).

% ============ 
% Example 10

fork(fork).
knife(knife).
spoon(spoon).
plate(plate).
bowl(bowl).
teaCup(cup).

position(0, 0, 0, fork).
position(1, 0, 0, plate).
position(1, 0, 1, bowl).
position(2, 0, 0, knife).
position(2, 1, 0, cup).
position(3, 0, 0, spoon).

% ============ 
% Example 11

fork(fork).
knife(knife).
spoon(spoon).
plate(plate).
bowl(bowl).
teaCup(cup).
saucer(saucer).
smallKnife(smallKnife).

position(0, 0, 0, fork).
position(0, 1, 0, saucer).
position(0, 1 ,1, smallKnife).
position(1, 0, 0, plate).
position(1, 0, 1, bowl).
position(2, 0, 0, knife).
position(2, 1, 0, cup).
position(3, 0, 0, spoon).

% ============ 
% Example 12

fork(fork).
knife(knife).
spoon(spoon).
smallKnife(smallKnife).
plate(plate).
bowl(bowl).
saucer(saucer).
teaCup(cup).

position(0, 0, 0, fork).
position(0, 1, 0, saucer).
position(0, 1 ,1, smallKnife).
position(1, 0, 0, plate).
position(1, 0, 1, bowl).
position(2, 0, 0, knife).
position(3, 0, 0, spoon).
position(2, 1, 0, cup).

% ============ 
% Example 13

fork(fork).
knife(knife).
smallKnife(smallKnife).
plate(plate).
saucer(saucer).
waterGlass(water).

position(0, 0, 0, fork).
position(0, 1, 0, saucer).
position(0, 1 ,1, smallKnife).
position(1, 0, 0, plate).
position(2, 0, 0, knife).
position(2, 1, 0, water).

% ============ 
% Example 14

fork(fork).
knife(knife).
smallKnife(smallKnife).
plate(plate).
saucer(saucer).
teaCup(cup).

position(0, 0, 0, fork).
position(0, 1, 0, saucer).
position(0, 1 ,1, smallKnife).
position(1, 0, 0, plate).
position(2, 0, 0, knife).
position(2, 1, 0, cup).

% ============ 
% Example 15

fork(fork).
knife(knife).
smallKnife(smallKnife).
plate(plate).
saucer(saucer).
teaCup(cup).
waterGlass(water).

position(0, 0, 0, fork).
position(0, 1, 0, saucer).
position(0, 1 ,1, smallKnife).
position(1, 0, 0, plate).
position(2, 0, 0, knife).
position(2, 1, 0, cup).
position(3, 1, 0, water).

% ============ 
% Example 16

fork(fork).
knife(knife).
spoon(spoon).
smallKnife(smallKnife).
plate(plate).
bowl(bowl).
saucer(saucer).
teaCup(cup).
waterGlass(water).

position(0, 0, 0, fork).
position(0, 1, 0, saucer).
position(0, 1 ,1, smallKnife).
position(1, 0, 0, plate).
position(1, 0, 1, bowl).
position(2, 0, 0, knife).
position(3, 0, 0, spoon).
position(2, 1, 0, cup).
position(3, 1, 0, water).

% ============ 
% Example 17

bowl(bowl).
spoon(spoon).

position(0, 0, 0, bowl).
spoon(1, 0, 0, spoon).

% ============ 
% Example 18

fork(fork).
knife(knife).
plate(plate).
teaCup(cup).
waterGlass(water).

position(0, 0, 0, fork).
position(1, 0, 0, plate).
position(2, 0, 0, knife).
position(2, 1, 0, cup).
position(3, 1, 0, water).

% ============ 
% Example 19

fork(fork).
knife(knife).
spoon(spoon).
plate(plate).
bowl(bowl).
teaCup(cup).
waterGlass(water).

position(0, 0, 0, fork).
position(1, 0, 0, plate).
position(1, 0, 1, bowl).
position(2, 0, 0, knife).
position(3, 0, 0, spoon).
position(2, 1, 0, cup).
position(3, 1, 0, water).

% ============ 
% Example 20

fork(fork).
knife(knife).
spoon(spoon).
plate(plate).
bowl(bowl).
wineGlass(wine).
waterGlass(water).

position(0, 0, 0, fork).
position(1, 0, 0, plate).
position(1, 0, 1, bowl).
position(2, 0, 0, knife).
position(3, 0, 0, spoon).
position(2, 1, 0, wine).
position(3, 1, 0, water).

% ============ 
% Example 21

fork(fork).
knife(knife).
spoon(spoon).
plate(plate).
soupPlate(soupPlate).
wineGlass(wine).
waterGlass(water).

position(0, 0, 0, fork).
position(1, 0, 0, plate).
position(1, 0, 1, soupPlate).
position(2, 0, 0, knife).
position(2, 1, 0, wine).
position(3, 1, 0, water).
position(3, 0, 0, spoon).

% ============ 
% Example 22

fork(fork).
knife(knife).
spoon(spoon).
smallKnife(smallKnife).
plate(plate).
soupPlate(soupPlate).
saucer(saucer).
wineGlass(wine).
waterGlass(water).

position(0, 0, 0, fork).
position(0, 1, 0, saucer).
position(0, 1, 1, smallKnife).
position(1, 0, 0, plate).
position(1, 0, 1, soupPlate).
position(2, 0, 0, knife).
position(2, 1, 0, wine).
position(3, 1, 0, water).
position(3, 0, 0, spoon).

% ============ 
% Example 23

fork(fork).
knife(knife).
plate(plate).
wineGlass(wine).

position(0, 0, 0, fork).
position(1, 0, 0, plate).
position(2, 0, 0, knife).
position(2, 1, 0, wine).

% ============ 
% Example 24

fork(fork).
spoon(spoon).
pastaPlate(pastaPlate).
waterGlass(water).

position(0, 0, 0, spoon).
position(1, 0, 0, pastaPlate).
position(2, 0, 0, fork).
position(2, 1, 0, water).

% ============ 
% Example 25

fork(fork).
spoon(spoon).
pastaPlate(pastaPlate).
wineGlass(wine).

position(0, 0, 0, spoon).
position(1, 0, 0, pastaPlate).
position(2, 0, 0, fork).
position(2, 1, 0, wine).

% ============ 
% Example 26

fork(fork).
spoon(spoon).
pastaPlate(pastaPlate).
wineGlass(wine).
waterGlass(water).

position(0, 0, 0, spoon).
position(1, 0, 0, pastaPlate).
position(2, 0, 0, fork).
position(2, 1, 0, wine).
position(3, 1, 0, water).

% ============ 
% Example 27

fork(fork).
knife(knife).
plate(plate).
wineGlass(wine).
waterGlass(water).

position(0, 0, 0, plate).
position(1, 0, 0, fork).
position(2, 0, 0, knife).
position(1, 1, 0, wine).
position(2, 1, 0, water).

% ============ 
% Example 28

fork(fork).
knife(knife).
spoon(spoon).
plate(plate).
soupPlate(soupPlate).
wineGlass(wine).
waterGlass(water).

position(0, 0, 0, fork).
position(1, 0, 0, plate).
position(1, 0, 1, soupPlate).
position(2, 0, 0, knife).
position(3, 0, 0, spoon).
position(2, 1, 0, wine).
position(3, 1, 0, water).

% ============ 
% Example 29

spoon(spoon).
soupPlate(soupPlate).
wineGlass(wine).

position(0, 0, 0, soupPlate).
position(1, 0, 0, spoon).
position(1, 1, 0, wine).

% ============ 
% Example 30

spoon(spoon).
soupPlate(soupPlate).
waterGlass(water).

position(0, 0, 0, soupPlate).
position(1, 0, 0, spoon).
position(1, 1, 0, water).

% ============ 
% Example 31

spoon(spoon).
soupPlate(soupPlate).
wineGlass(wine).
waterGlass(water).

position(2, 0, 0, spoon).
position(1, 0, 0, soupPlate).
position(2, 1, 0, wineGlass).
position(3, 1, 0, waterGlass).

% ============ 
% Example 32

spoon(spoon).
soupPlate(soupPlate).
saucer(saucer).
wineGlass(wine).
waterGlass(water).
smallKnife(smallKnife).

position(2, 0, 0, spoon).
position(1, 0, 0, soupPlate).
position(0, 1, 0, saucer).
position(2, 1, 0, wineGlass).
position(3, 1, 0, waterGlass).
position(0, 1, 1, smallKnife).

% ============ 
% Example 33

fork(fork).
knife(knife).
fork(fork2).
knife(knife2).
plate(plate).
wineGlass(wine).
waterGlass(water).

position(0, 0, 0, fork2).
position(1, 0, 0, fork).
position(4, 0, 0, knife2).
position(2, 0, 0, plate).
position(3, 0, 0, knife).
position(3, 1, 0, wineGlass).
position(4, 1, 0, waterGlass).

% ============ 
% Example 34

fork(fork).
knife(knife).
fork(fork2).
knife(knife2).
plate(plate).
wineGlass(wine).
waterGlass(water).
saucer(saucer).
smallKnife(smallKnife).

position(0, 0, 0, fork2).
position(4, 0, 0, knife2).
position(1, 0, 0, fork).
position(2, 0, 0, plate).
position(3, 0, 0, knife).
position(0, 1, 0, saucer).
position(4, 1, 0, waterGlass).
position(3, 1, 0, wineGlass).
position(0, 1, 1, smallKnife).

