(ns iclc.core
  (:require [quil.core :as q]
            [quil.middleware :as m]
            )
  (:use [clojure.core.match :only [match]])
  (:require [polynome.core :as poly])
  (:require [newtonian.particle-system :as newt]
            [newtonian.utils :as utils]
            [newtonian.kaosnewt :as kn])
  (:import newtonian.utils.Vector2D)
  )




;  _ __ ___   ___  _ __   ___  _ __ ___   ___
; | '_ ` _ \ / _ \| '_ \ / _ \| '_ ` _ \ / _
; | | | | | | (_) | | | | (_) | | | | | |  __/
; |_| |_| |_|\___/|_| |_|\___/|_| |_| |_|\___|


;;(defonce m (poly/init "/dev/tty.usbserial-m128-121"))


(def width 1440)
(def height 980)


 ;; 4 -  start gfxcore

;;start gfxcore, update state
(defn setup []
  (q/frame-rate 30)
)
(defn update [state]
  (newt/add-new-particles)
  (newt/update-particles width height)
  {
  ;:fm @(:left fmtonestaps)
  :mod16 (mod16)
   }
  ;; (updateM)
  )

(defn drawP [state]
  (let [particles @newt/particles]
    ( doseq [p particles]
      (kn/draw-particle p state)))
  )


(defn draw [state]
 ; (kn/drawP state)
  (q/background 255 255 0)
  (q/with-translation [ (+ 100 (* 10 (mod16))) 100 0]
   ; (q/box (* (:fm state)  100))
    (q/box 50)
    )
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

;;monomestuff
;; long live the source https://github.com/samaaron/polynome/blob/master/src/polynome/core.clj
(range 0 (int  (* 8 0.5)))
