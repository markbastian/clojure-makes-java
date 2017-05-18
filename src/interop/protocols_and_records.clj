(ns interop.protocols-and-records)

(definterface IPretty
  (^String pretty []))

;No typing
(defprotocol AgeProtocol
  (getOlder [_]))

(defprotocol Interact
  (greet [_]))


(defrecord Person [^String name ^int age]
  AgeProtocol
  (getOlder [this] (update this :age inc))
  IPretty
  (pretty [{:keys [name age]}] (str "name: " name ", age: " age)))

;Late binding - Note that it doesn't work with aot.
(extend-type Person
  Interact
  (greet [{:keys [name]}] (str "Hi, my name is " name ".")))