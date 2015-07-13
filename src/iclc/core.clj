(ns iclc.core
  (:use [overtone.core])
  (:require [quil.core :as q]
            [quil.middleware :as m]
            )
  )

;; 0 - setup environment
(def superServerIP "localhost")
(def superServerPort 57110)


;; 1 - connect to supercollider

( connect-external-server superServerIP  superServerPort)
