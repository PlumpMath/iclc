(ns iclc.core
  (:require [newtonian.kaosnewt :as kn])
  )


(defn draw-particle [{:keys [position velocity accel]} state]
  (let [x (:x position)
        y (:y position)]

                                        ;    (println position)
    (q/with-rotation [(* @(audio-bus-monitor 4) 3 ) 1 0.5 0  ]
      (q/with-translation [ x y]
        (q/fill 255  255 (* 5000 @(audio-bus-monitor 6)) 128)
        (q/box 100 100 (* 1000  @(audio-bus-monitor 4)))
        ;(q/sphere (* @(audio-bus-monitor 0)  1000) ) 
        ))
    )
  )


(defn drawP [state]
  (let [particles @newt/particles]
    (doseq [p particles]
      (draw-particle p state))
    ))



(defn draw [state]
  (q/background 1 8 0)
  (if (= 1  (nth (get-in @live-pats [kickA])  (mod @bbeat (count (get-in @live-pats [kickA])  ))))
    (q/fill (q/random 255) 0 0)
    (q/fill 0 0 255)
    )

  ;
  (dotimes [n (count (get-in @live-pats [kickA]))]
    (q/with-translation [(* n (/ width (count (get-in @live-pats [kickA]))) ) 100 0]
                                        ; (if )
                                        ;


                                        ;(q/fill (q/random 233))
                                        ;    (if (= 1 (nth (get-in @live-pats [kickA] n ))))

      (q/box (* 200 @(audio-bus-monitor 0)) )))



  (dotimes [n (count (map #(or (:amp %) 0 ) (get-in @live-pats [c-hat])))]
    (q/with-translation [(* 100 n) 500 0]
  ;    (q/fill (q/random 234) 35 0)
      (q/sphere (* 50 (nth (map #(or (:amp %) 0 ) (get-in @live-pats [c-hat])) n ))  )
      )

    )
  (if (some #(= 0 %)  (get-in @live-pats [fmtones])) 
    ;(println "no fmtones")
    (drawP state)
    )


                                        ;  (dotimes [n 8] (kn/changespeed n 0 0))
;(kn/changespeed (mod @bbeat 8) 0 10)

  (q/with-translation [ 500 500]
    (q/fill (q/random 255 ) (q/random 255) (q/random 24) 128)
    (q/box (+ 1 (* @(audio-bus-monitor 6) 10000 ))))

  )


(kn/addemitter 130 300 100 0)
(kn/changespeed 0 1 10)
(@newt/emitters 0)
(kn/changeemitspeed 0 1)

;
;(dotimes [n 8] (kn/addemitter (* (/ width 8) (+ 1 n)) 100 0 10 ))


;(kn/addemitter 100 100 10 10)

( dotimes [n 313]
  (swap! newt/emitters pop))
(count @newt/emitters)

(kn/changeemitspread 0 1 )

@newt/particles
