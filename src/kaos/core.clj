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
   :beat @bbeat
   :bus0 @(audio-bus-monitor 0)
   :bus1 @(audio-bus-monitor 1)
   :bus2 @(audio-bus-monitor 2)
   :bus3 @(audio-bus-monitor 3)
   :bus4 @(audio-bus-monitor 4)
   :bus5 @(audio-bus-monitor 5)

   }

  )

(defn drawP [state]
  (let [particles @newt/particles]
    ( doseq [p particles]
      (kn/draw-particle p state))

    )

  )


(defn draw [state]
 ; (kn/drawP state)
  (q/background 255 255 0)
  (q/with-translation [ (+ 100 (* 50 (mod @bbeat 16))) 100 0]
                                        ; (q/box (* (:fm state)  100))

    (q/box (* 550 @(audio-bus-monitor 0)))))



(q/defsketch halic
  :title "halic";;  :size :fullscreen
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




;; taps testen
@(audio-bus-monitor 3 )

;(ctl fmtones :depth 2.0)


(map #(or (:carrier %) (:t %) 0)  (get-in @live-pats [fmchord])
     )

;(count)
(get-in @live-pats [kickA])


(count  (map #(or (:amp %) 0 ) (get-in @live-pats [c-hat])))






;(setEye [x y z]        (q/camera))




(map #(or (:amp %) 0) (get-in @live-pats [kickA]))
(nth (get-in @live-pats [kickA])  (mod @bbeat (count (get-in @live-pats [kickA]))))




(def campos {
             :eye [0 0 0]
             :center [0 0 500]
             :up [ 0 1 0 ]
             })

(def campos { :cam [0 0 0 0 1 1 1 0 ]})

(def live-cam (atom campos))
(swap! live-cam assoc :eye [0 0 100])

(swap! live-cam assoc  :cam [0 0 0  2 2 2 ])

@live-cam

;(q/camera (flatten  (get @live-cam :cam)))



( some #(= 1 %)  [0 0])





;;notes
;; seperate drawfunctions per instrument
;; veel meer via update/state ontsluiten

@(audio-bus-monitor 6)
