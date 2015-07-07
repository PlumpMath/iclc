(ns kaos.core
  (:require [quil.core :as q]
            [quil.middleware :as m]
            [iclc.core :refer :all])
  (:use [clojure.core.match :only [match]])
  (:require [polynome.core :as poly]))




;  _ __ ___   ___  _ __   ___  _ __ ___   ___
; | '_ ` _ \ / _ \| '_ \ / _ \| '_ ` _ \ / _
; | | | | | | (_) | | | | (_) | | | | | |  __/
; |_| |_| |_|\___/|_| |_|\___/|_| |_| |_|\___|


(defonce m (poly/init "/dev/tty.usbserial-m128-121"))


(def width 1440)
(def height 980)


 ;; 4 -  start gfxcore

;;start gfxcore, update state
(defn setup []
  (q/frame-rate 30)
)
(defn update [state]
  {
  :mod16 (mod16)
   }
  ;; (updateM)
  )



(defn draw [state]
  (q/background 255 255 0)
  (q/with-translation [ (+ 100 (* 10 (mod16))) 100 0]
    (q/box 100))
 )

(q/defsketch halic
  :title "halic"
;;  :size :fullscreen
  :size [width height]
  ;;:features [:present]
  :setup setup
  :update update
  :draw draw
  :renderer :p3d
  :middleware [m/fun-mode]

  )
