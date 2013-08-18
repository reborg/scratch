(ns rpn-clojure.test.core
  (:use [rpn-clojure.core])
  (:use [midje.sweet]))

(facts "validating operations"
       (fact "it only executes known operations"
             (clifford "678") => "clifford does not understand operation 6"
             (clifford "Ze8") => "clifford does not understand operation Z")
       (fact "the number to push is required"
             (clifford "0") => (throws Exception "missing argument for push")))

(facts "stack operations" 
       (fact "the first item in is the first item out"
             (clifford "091") => 9))

(facts "push"
       (fact "there is a new item on the stack"
             (cpush (state [\1] [])) => (state [] [\1]))
       (fact "new items are pushed bofore the others"
             (cpush (state [\2] [\1])) => (state [] [\2 \1])))

(facts "exec"
       (fact "invokes the operation and removes it from the input"
             (exec {:input '(\0 \1) :stack [] :result nil}) => (state '() [\1])))

(facts "pop"
       (fact "it returns nothing if nothing on the stack"
             (cpop (state '() '())) => {:input '() :stack '() :result nil})
       (fact "it returns the last thing on the stack"
             (cpop (state '() '(1 2 3))) => {:input '() :stack '(1 2) :result 3}))
