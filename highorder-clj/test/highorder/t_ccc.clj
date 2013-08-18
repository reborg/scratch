(ns highorder.t-ccc
  (:use midje.sweet)
  (:require [highorder.ccc :as ccc]))

(facts "operations on top of car, cdr, and cons, also know as ccc"
       (fact "map is linear in length of coll"
             (ccc/map #(* % %) '(1 2 3)) => '(1 4 9))
       (fact "reduce is linear in length of coll"
             (ccc/reduce + 0 '(1 4 9)) => 14)
       (fact "mapreduce is linear in length of coll"
             (ccc/mapreduce #(* % %) + 0 '(1 2 3)) => 14)
       (fact "length is linear again"
             (ccc/length (range 10)) => 10)
       (fact "fiter through primitives"
             (ccc/filter-1 odd? '(1 2 3 4)) => '(1 3))
       (fact "fiter through apply append"
             (ccc/filter-2 odd? '(1 2 3 4)) => '(1 3))
       (fact "fiter through mapreduce"
             (ccc/filter-3 odd? '(1 2 3 4)) => '(1 3)))
