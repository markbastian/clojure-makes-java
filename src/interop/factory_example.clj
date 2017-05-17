(ns interop.factory-example
  (:gen-class
    :name interop.FactoryDemo
    :prefix "-"
    :main false
    :methods [^:static [getInstance [] interop.JInterface]])
  (:import (interop JInterface)))

(defn -getInstance []
  (reify
    JInterface
    (methodJ [this v] (mapv str v))))
