(ns highorder.reducers
  (:require [clojure.core.reducers :as r])
  (:refer-clojure :exclude [map filter reduce reverse]))

(defn map [f coll]
  (r/fold concat (r/map #(list (f %)) coll)))

(defn reduce [g coll]
  (r/fold g coll))

(defn length [coll]
  (r/fold + (r/map (constantly 1) coll)))

(defn filter [p coll]
  (r/fold concat (r/map #(if (p %) (list %) '()) coll)))

(defn reverse [coll]
  (r/fold (fn ([] '()) ([xs ys] (concat ys xs))) (r/map list coll)))
