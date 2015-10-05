(defproject iclc "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [quil "2.2.6"]
                 [overtone "0.9.1"]
                 [leipzig "0.9.0"]
;;                 [com.rpl/specter "0.5.4"] ;; testing this for filtering
;;                  [overtone "0.10-SNAPSHOT"] ;; trying latest snapshot version
;;                 [polynome "0.3.0-SNAPSHOT"]
                 [polynome "0.2.2"]
                 [org.clojure/core.match "0.3.0-alpha4"]
;;                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 ]
    :repl-options {:host "0.0.0.0" :port 21337}

  )
