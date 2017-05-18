(ns interop.core
  (:gen-class
    :name interop.Demo
    :prefix "-"
    :main false
    :methods [^:static [bar [] interop.api.JInterface]
              ^:static [foo [] interop.api.CInterface]])
  (:import (interop.api JInterface)
           (interop.api CInterface)))

(defn bar []
  (reify
    JInterface
    (methodJ [this v] (mapv str v))))

(defn foo []
  (reify
    CInterface
    (methodC [this v] (mapv str v))))

(defn -bar ([this] (bar)) ([] (bar)))
(defn -foo ([this] (foo)) ([] (foo)))

;Class w/state
(gen-class
  :name interop.StatefulDemo
  :prefix "stateful-"
  :state state
  :init init
  :main false
  :implements [interop.api.JInterface interop.api.CInterface]
  :methods [[setInc [int] void]
            [getInc [] int]])

;;Methods for stateful class. Type hints are optional.
;;I've fully qualified them to not have to do an import.
(defn stateful-methodJ [^interop.StatefulDemo this v] (mapv (comp str #(+ % (.getInc this))) v))
(defn stateful-methodC [^interop.StatefulDemo this v] (mapv (comp str #(+ % (.getInc this))) v))
(defn stateful-setInc [^interop.StatefulDemo this i] (swap! (.state this) assoc :inc i))
(defn stateful-getInc [^interop.StatefulDemo this] (get @(.state this) :inc))
;initializer returns a vector of super class constructor arguments then the new object's state.
(defn stateful-init [][[] (atom {:inc 0})])
