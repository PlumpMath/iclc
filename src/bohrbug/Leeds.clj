(ns iclc.core
  (:require overtone.core
            overtone.inst.drum
            overtone.inst.synth
            overtone.osc.util
            overtone.osc.peer
            overtone.osc.dyn-vars
            ))


(defonce bus1 (audio-bus))
(defonce bus2 (audio-bus))
(defonce bus3 (audio-bus))
(defonce bus4 (audio-bus))
(defonce bus5 (audio-bus))
(defonce bus6 (audio-bus))
(defonce bus7 (audio-bus))
(defonce bus8 (audio-bus))



(definst kickA [freq 105 dur 1.2 width 0.5 amp -20 out-bus 0]
  (let [freq-env (* freq (env-gen (perc 0.02 (* 0.49 dur))))
        env (env-gen (perc 0.019 dur) 1 1 0 1 FREE)
        sqr (* (env-gen (perc 0 0.0800)) (pulse (* 2 freq) width))
        src (sin-osc freq-env)
        src2 (sin-osc-fb freq-env)
        filt (lpf (+ sqr src src2) 100)
        drum (+ sqr (* env filt))
        ]
        (compander drum drum 0.2 1 0.1 0.01 0.01)
        ))

(swap! live-pats assoc kickA [x 0 0 0 0 0 0 y 1 0 1 0 0 0 0 0])
(swap! live-pats assoc kickA [1 0 0 0 0 0 1 0 0 0 0 0 1 0 0 0])
(swap! live-pats assoc kickA [x 0 0 0 0 0 0 0 0 0])
(swap! live-pats assoc kickA [0])

(def kickdisto (inst-fx! kickA fx-distortion2))
    (ctl kickdisto :amount 0.70)


(def kickcompress (inst-fx! kickA fx-compressor))
    (ctl kickcompress :amount 0.10)
(def kickChorus (inst-fx! kickA fx-chorus))
     (ctl kickChorus :rate 0.2 :depth 0.4)
(def kickfilter (inst-fx! kickA fx-rlpf))
    (ctl kickfilter :cutoff (ranged-rand 200 7000) :res 0.8)

(inst-volume! kickA 0.5)
(clear-fx kickA)



(definst snareA [freq 200 dur 0.20 width 0.5 pan 0.5 amp -1.0 out-bus 0]
  (let [freq-env (* freq (env-gen (perc -0.4 (* 0.24 dur))))
        env (env-gen (perc 0.006 dur) 1 1 0 1 FREE)
        noise (pink-noise)
        sqr (* (env-gen (perc 0 0.04)) (pulse (* 2.9 freq) width))
        src (sin-osc freq-env)
        src2 (sin-osc freq-env)
        filt (glitch-rhpf (+ sqr noise src src2) 300 2.6)
        clp (clip2 filt 0.6)
        drum (+ sqr (* env clp))

        ]
        (compander drum drum 0.4 1 0.02 0.01 0.01)
    ))

(swap! live-pats assoc snareA [0 0 0 0 1 0 0 0 0 w 0 w 0 0 0 0 v 1 0 0 0 ])
(swap! live-pats assoc snareA [0])

(inst-volume! snareA 0.5)

(definst c-hat [amp 0.7 t 0.03 out-bus 0]
  (let [env (env-gen (perc 0.001 t) 1 1 0 1 FREE)
             noise (white-noise)
             sqr (* (env-gen (perc 0.07 0.04)) (pulse 880 0.8))
             filt (rhpf  (+ sqr noise) 300 0.5)
       ]
             (* amp env filt)
    ))

(swap! live-pats assoc c-hat [0 0 1 0 0 1 0 0 0 0 0 1 0 0 0 1 0])
(stop)


(defsynth fmchord [carrier 440 divisor 4.0 depth 2.0 out-bus 6]
  (let [modulator (/ carrier divisor)
        mod-env (env-gen (lin 1.9 3.8 -2.8))
        amp-env (env-gen (lin 1 0 4.0 1) :action FREE)
        filt (rlpf (+ carrier modulator ) 100 0.1)]
    (out out-bus  (pan2 (* 0.15 amp-env
                          (sin-osc (+ carrier
                                      (* mod-env (* carrier depth) (sin-osc modulator)))))))
    ))


                              ; 1 - - - 2 - - - 3 - - - 4 - - -
(swap! live-pats assoc fmchord [a c f 0 0 0 0 0 0 0 0 0 0 0 0 0
                                0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
                                +a 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
                                0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0


                                ]
(swap! live-pats assoc fmchord [0])


       )
(swap! live-pats assoc fmchord [0])
(stop)

(defsynth chordreverb [mix 0.5 room 0.6 damp 0.1]
  (out 0 (free-verb (in-feedback bus2 2) mix room damp)))

(def chordreverb-ctrl (chordreverb))


(defsynth fmtones [carrier 440 divisor 8.0 depth 8.0 out-bus 4]
  (let [modulator (/ carrier divisor)
        mod-env (env-gen (lin-rand -0.2 0.4 -2.8))
        amp-env (env-gen (lin 0 -0.2 0.1 1 ) :action FREE)
        filt (glitch-rhpf (+ carrier modulator ) 100 2.6)
             ]
      (out out-bus (pan2 (* 0.60 amp-env
                          (sin-osc (+ carrier
                                     (* mod-env (* carrier depth) (sin-osc  modulator)))))))))

(swap! live-pats assoc fmtones [-a -c -a 0 0 0 0 0 0 0 0 +d 0 0 g +a])
(swap! live-pats assoc fmtones [-a -d 0 a b c e f])

(defsynth contra [carrier 440 divisor 2.0 depth 2.0 out-bus 2]
  (let [modulator (/ carrier divisor)
        mod-env (env-gen (lin-rand -0.2 0.4 -2.8))
        amp-env (env-gen (lin 0 -0.2 0.1 1 ) :action FREE)
        filt (glitch-rhpf (+ carrier modulator ) 100 2.6)
             ]
      (out out-bus (pan2 (* 0.60 amp-env
                          (sin-osc (+ carrier
                                     (* mod-env (* carrier depth) (sin-osc  modulator)))))))))

(swap! live-pats assoc contra [0 0 0 f d e 0 0 k l i j 0 0 0])
(swap! live-pats assoc contra [0])
;  Chaos  ()
;  Line   (amp-comp, amp-comp-a, k2a, line )
;  Random (rand-seed, lonrenz-trig )
;  Noise  (lf-noise, hasher , mantissa-mask)

;
(stop)




;kick control
(def q {:amp 0.5 :dur 1.0 :freq 105})
(def x {:amp 2.0 :dur 4.0 :freq 105})
(def y {:amp -20 :dur 1.2 :freq 75
        })
;; (def z {:amp})

;hh control
(def o {:amp 0.2})
(def p {:amp 0.5})
(def s {:t 0.18})
(def t {:amp 1.0})

 ;snare control
(def v {:amp -3.0
        })
(def w {:amp -4.0})
;fmtones control
 (def -a {:carrier 261.63 :car-freq 261.63})
 (def -b {:carrier 277.18})
 (def -c {:carrier 293.66})
 (def -d {:carrier 311.13})
 (def -e {:carrier 329.63})
 (def -f {:carrier 349.23})
 (def -g {:carrier 369.99})
 (def -h {:carrier 392.00})
 (def -i {:carrier 415.00})
 (def -j {:carrier 440.00})
 (def -k {:carrier 466.16})
 (def -l {:carrier 493.88})
 (def a {:carrier 523.25
         :divisor (ranged-rand 5.0 9.0) :depth (ranged-rand 0.1 9.0)
         })
 (def b {:carrier 554.37})
 (def c {:carrier 587.33 :car-freq 587.33})
 (def d {:carrier 622.25 :car-freq 622.25})
 (def e {:carrier 659.25})
 (def f {:carrier 698.46 :depth (ranged-rand 0.2 0.8)})
 (def g {:carrier 739.99})
 (def h {:carrier 783.99
         :depth 9.0
         :mix 0.5
         })
 (def i {:carrier 830.61})
 (def j {:carrier 880.00})
 (def k {:carrier 923.33})
 (def l {:carrier 987.77})
 (def +a {:carrier 1046.50})
 (def +b {:carrier 1108.73})
 (def +c {:carrier 1174.66})
 (def +d {:carrier 1244.51})
