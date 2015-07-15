;;
;;  _____ _____ _      _____ ___   ___  __ _____
;; |_   _/ ____| |    / ____|__ \ / _ \/_ | ____|
;;   | || |    | |   | |       ) | | | || | |__
;;   | || |    | |   | |      / /| | | || |___ \
;;  _| || |____| |___| |____ / /_| |_| || |___) |
;; |_____\_____|______\_____|____|\___/ |_|____/
;;




(ns iclc.core
  (:require [newtonian.kaosnewt :as kn])
  )


(defn draw-particle [{:keys [position velocity accel]} state]
  (let [x (:x position)
        y (:y position)]
    (q/with-translation [ x y]
      (q/fill 255 255 122)
      (q/box 100 100 (* 10 (:fmtonesbus state) ))
    )
  ))


(defn drawP [state]
  (q/fill (q/random 255) 23 45)
  (let [particles @newt/particles]
    (doseq [p particles]
      (draw-particle p state))
    ))



(defn draw [state]

  (println (:drumbus state)))




(q/background (* (:drumbus state) 2000) 95 8)

(if (+ 0.1 (get (:c-hat state) :amp ))
  (q/with-translation [ 500 500]
    (q/fill  25 230 90 20)
    (q/box (* (get  (:c-hat state) :amp) 100) ))
  )

                                        ; (q/background 1 8 0)


(if (= 1  (nth (get-in @live-pats [kickA])  (mod @bbeat (count (get-in @live-pats [kickA])  ))))
  (q/fill (q/random 255) 0 0)
  (q/fill 0 0 255)
  )


(dotimes [n (count (get-in @live-pats [kickA]))]
  (q/with-translation [(* n (/ width (count (get-in @live-pats [kickA]))) ) 500 0]

    (q/with-rotation [(* (:axobus state) (q/random 200)) 0 1 1]
      (q/box (* 100 @(audio-bus-monitor 0)) (* (:fmtones state) 0.2) 0.2 ))



    (dotimes [n (count (map #(or (:amp %) 0 ) (get-in @live-pats [c-hat])))]
      (q/with-translation [(* 100 n) 500 0]
                                        ;    (q/fill (q/random 234) 35 0)



        )
      (if (some #(= 0 %)  (get-in @live-pats [fmtones]))
                                        ;(println "no fmtones")
        (drawP state)
        )))


                                        ;  (dotimes [n 8] (kn/changespeed n 0 0))
                                        ;(kn/changespeed (mod @bbeat 8) 0 10)
  (q/with-rotation [(* 100  (tr)) 0 0 0]
    (q/with-translation [ 500 500]
      (q/fill (q/random 255 ) (q/random 255) (q/random 24) 128)
      (q/box (+ 1 (* (:fmtones state) 10000 ))))))

;(drawP state)







(kn/addemitter 500 300 100 0)
(kn/changespeed 0 1 10)
(@newt/emitters 1)
(kn/changeemitspeed 0 1)

;
;(dotimes [n 8] (kn/addemitter (* (/ width 8) (+ 1 n)) 100 0 10 ))


(kn/addemitter 100 100 10 10)

;( dotimes [n 313])
;(swap! newt/emitters pop)
;(count @newt/emitters)

(kn/changeemitspread 0 0 )

@newt/particles


@(audio-bus-monitor 6)
