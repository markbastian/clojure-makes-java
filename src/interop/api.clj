(ns interop.api
  (:import (java.util Collection)))

(definterface CInterface
  (^java.util.Collection methodC [^java.util.Collection c]))
