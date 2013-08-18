(ns highorder.t-ilsc
  (:use midje.sweet)
  (:require [highorder.ilsc :as ilsc]))

(facts "new primitives"
       (fact "split applies a function f to a roughly perfect split of a collection"
             (ilsc/split (range 10) (fn [ys zs] (+ (count ys) (count zs)))) => 10))

(facts "operations on top of item, list, split and conc"
       (fact "map is linear in length of coll"
             (ilsc/map #(* % %) '(1 2 3)) => '(1 4 9))
       (fact "reduce is linear in length of coll"
             (ilsc/reduce + 0 '(1 4 9)) => 14)
       (fact "mapreduce is linear in length of coll"
             (ilsc/mapreduce #(* % %) + 0 '(1 2 3)) => 14)
       (fact "length is linear again"
             (ilsc/length (range 10)) => 10)
       (fact "fiter through primitives"
             (ilsc/filter odd? '(1 2 3 4)) => '(1 3))
       (fact "reversing a list in terms of mapreduce"
             (ilsc/reverse '(1 2 3 4)) => '(4 3 2 1)))
