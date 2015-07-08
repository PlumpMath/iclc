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


;; 2 - define & start central bpm-counter
(defsynth bpm [bpm 120]
  (let  [a   (/ bpm 120)
          _ (tap:kr :step 60 (lf-saw:kr a))]
     (out 100 (lf-saw:kr a)))) ;; put the control synths on channel 100 & on

(def tempbeat (bpm 60))

(defn mod16 []
  (int (* 16 (/ (+ @(get-in tempbeat [:taps :step]) 1) 2 ))))

(defn mod4 []


  (int (/ (mod16) 4)))
