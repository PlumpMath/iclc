(ns iclc.core
  (:require [newtonian.kaosnewt :as kn])
  )

(defn draw-particle [{:keys [position velocity accel]} state]
  (let [x (:x position)
        y (:y position)]

;    (println position)
    (q/with-translation [ x y]
      (q/box 20))
    )
  )


(defn drawP [state]
  (let [particles @newt/particles]
    (doseq [p particles]
      (draw-particle p state))
    ))



(defn draw [state]
  (q/background 255 128 0)
  (drawP state)


;  (dotimes [n 8] (kn/changespeed n 0 0))
  ;(kn/changespeed (mod @bbeat 8) 0 10)
  (q/box 50)
  )

(kn/addemitter 100 100 100 0)
(kn/changespeed 0 0 0)
(@newt/emitters 0)
(kn/changeemitspeed 0 1)

(dotimes [n 8]
  (kn/addemitter (* (/ width 8) (+ 1 n)) 100 0 10 )
  )



(swap! newt/emitters pop)
