(ns highorder.conc-sequential
  (:require [clojure.core.reducers :as r])
  (:refer-clojure :exclude [map filter reduce reverse]))

(defn split [coll f]
    (let [middle (/ (count coll) 2)]
      (f (take middle coll) (drop middle coll))))

(defn mapreduce [f g id coll]
  (cond (empty? coll) id
        (<= (count coll) 512) (clojure.core/reduce g id (r/map f coll))
        :else (split coll (fn [coll1 coll2] (g (mapreduce f g id coll1) (mapreduce f g id coll2))))))

(defn map [f coll]
  (mapreduce #(list (f %)) concat '() coll))

(defn reduce [g id coll]
  (mapreduce identity g id coll))

(defn length [coll]
  (mapreduce (constantly 1) + 0 coll)) 

(defn filter [p coll]
  (mapreduce #(if (p %) (list %) '()) concat '() coll))

(defn reverse [coll]
  (mapreduce list #(concat %2 %1) '() coll))
