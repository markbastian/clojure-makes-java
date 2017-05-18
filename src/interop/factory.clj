(ns interop.factory
  (:gen-class
    :name interop.FactoryDemo
    :prefix "-"
    :main false
    :methods [^:static [getInstance [] interop.api.JInterface]])
  (:import (interop.api JInterface)))

(defn -getInstance []
  (reify
    JInterface
    (methodJ [this v] (mapv str v))))
