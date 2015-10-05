(ns iclc.core
  (:use [overtone.core])
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [leipzig.live :as live]
            [leipzig.melody :as melody]
            [leipzig.scale :as scale]
            [leipzig.chord :as chord]
            )
  )

;; 0 - setup environment
(def superServerIP "localhost")
(def superServerPort 57110)


;; 1 - connect to supercollider

(Connect-external-server superServerIP  superServerPort)

(defsynth tapper
  []
  (let [source (sound-in 2 1)
        left (select 0 source)
        right (select 1 source)
        _ (tap :left 10 left)
        _ (tap :right 10 right)
        _ (tap :phase 10 (- left right))]
    (out [0 1] [left right])))

(def axotapper (tapper))
