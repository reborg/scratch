(ns highorder.t-reducers
  (:use midje.sweet)
  (:require [highorder.reducers :as c]))

(facts "high order operations built on top of reducers instead"
       (fact "map"
             (time (c/map #(* % %) (range 10000))) => (contains '(1 4 9))
             (time (doall (map #(* % %) (range 10000)))) => (contains '(1 4 9)))
       (fact "reduce"
             (time (c/reduce + (range 10000))) => 49995000)
       (fact "length"
             (time (c/length (range 100000))) => 100000)
       (fact "filter"
             (time (c/filter odd? (range 10000))) => (contains '(1 3)))
       (fact "reversing"
             (take 4 (time (c/reverse (range 10000)))) => '(9999 9998 9997 9996)))
