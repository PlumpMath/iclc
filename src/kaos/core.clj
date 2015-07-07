(ns kaos.core
  (:use [clojure.core.match :only [match]])
  (:require [polynome.core :as poly])
)

(def m (poly/init "/dev/tty.usbserial-m128-121"))
