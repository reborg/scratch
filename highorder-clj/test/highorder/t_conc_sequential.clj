(ns highorder.t-conc-sequential
  (:use midje.sweet)
  (:require [highorder.conc-sequential :as c]))

(facts "high order operations built on top of conc-semantic with sequential leaf"
       (fact "map"
             (c/map #(* % %) (range 10000)) => (contains '(1 4 9)))
       (fact "reduce"
             (time (c/reduce + 0 (range 100000))) => 4999950000)
       (fact "mapreduce"
             (c/mapreduce #(* % %) + 0 (range 1000)) => 332833500)
       (fact "length"
             (c/length (range 100000)) => 100000)
       (fact "fiter"
             (c/filter odd? (range 10000)) => (contains '(1 3)))
       (fact "reversing"
             (take 4 (c/reverse (range 10000))) => '(9999 9998 9997 9996)))
