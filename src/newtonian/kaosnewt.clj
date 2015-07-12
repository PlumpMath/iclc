(ns newtonian.kaosnewt
  (:require [newtonian.particle-system :as newt])
  (:import newtonian.utils.Vector2D)
)



(defn changespeed [emitterindex  speedX speedY]
  ;;   (swap! newt/emitters update-in [emitterindex :position] assoc :x 200)
  (swap! newt/emitters update-in [emitterindex :velocity] assoc :x speedX)
  (swap! newt/emitters update-in [emitterindex :velocity] assoc :y speedY))

(defn changepos [emitterindex x y]
  (swap! newt/emitters update-in [emitterindex :position] assoc :x x)
  (swap! newt/emitters update-in [emitterindex :position] assoc :y y))

(defn changeemitspeed [emitterindex speed]
  ;; (swap! newt/emitters update-in [emitterindex :emission-rate] assoc :x x )
  (swap! newt/emitters update-in [emitterindex] assoc :emission-rate speed))

(defn changeemitspread [emitterindex spread]
  ;; (swap! newt/emitters update-in [emitterindex :emission-rate] assoc :x x )
  (swap! newt/emitters update-in [emitterindex] assoc :spread spread))

(defn addemitter [locx locy spdx spdy]
  (newt/add-emitter (Vector2D. locx locy) (Vector2D. spdx spdy))
  )

(defn draw-particle [{:keys [position velocity accel]} state]
  (let [x (:x position)
        y (:y position)]
    (println x)))

(defn drawP [state]
  (let [particles @newt/particles]
    (doseq [p particles]
      (draw-particle p state))))
