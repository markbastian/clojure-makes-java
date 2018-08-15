(ns interop.static
  (:gen-class
    ;The name is arbitrary, but often follows the package name (not in this case)
    :name interop.staticfunctions.Math
    :prefix "-"
    :main false
    :methods [^:static [nthFib [long] clojure.lang.BigInt]]))

;;For another good example, see https://stackoverflow.com/questions/2181774/calling-clojure-from-java

(defn nth-fib [n]
  (nth (map second (iterate (fn [[a b]] [b (+ a b)]) [0N 1N])) n))

;Class methods start with the prefix. - is the default.
(defn -nthFib [i] (nth-fib i))