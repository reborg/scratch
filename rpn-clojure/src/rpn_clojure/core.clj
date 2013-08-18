(ns rpn-clojure.core)

(defstruct current-input-and-stack :input :stack :result)
(defmacro state [current-input current-stack & result] (struct current-input-and-stack current-input current-stack result))

(defn cpush [ops]
  (if (empty? (:input ops))
    (throw (Exception. "missing argument for push"))
    (state
      (rest (:input ops)) 
      (cons (first (:input ops))
            (:stack ops)))))

(defn cpop [ops]
  (let [last-item (last (:stack ops))
        new-stack (first (split-at (- (count (:stack ops)) 1) (:stack ops)))]
    {:input (:input ops) :stack new-stack :result last-item}))

;;(defn add  [stack])
;;(defn sub  [stack])
;;(defn mul  [stack])
;;(defn div  [stack])

(def operations {\0 cpush \1 cpop \2 cpop \3 cpop \4 cpop \5 cpop})

(defn exec [ops]
  (let [operation (operations (first (:input ops)))
        operands (rest (:input ops))
        result (operation {:input operands :stack (:stack ops) :result (:result ops)})
        input-result (:input result)]
    (if (nil? input-result)
      (:result result)
      (recur result))))


(defn clifford [program]
  (if (contains? operations (first program))
    (first (:stack (exec (state program '()))))
    (str "clifford does not understand operation " (first program))))
