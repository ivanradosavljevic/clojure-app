(defproject project-closure "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Apache License, Version 2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.1"]
                 [de.ubercode.clostache/clostache "1.4.0"]
;                 [hiccup "1.0.5"]
                 ;monger je bibiloteka za povezivanje sa monogdb i crud sa njom
                 [com.novemberain/monger "3.0.0-rc2"]
                 ;libnoir je biliteka za http stvari (response, sesije, request itd...
                 [lib-noir "0.9.9"]
                 [ring "1.3.2"]
                  ;json parser
;                 [ring/ring-json "0.4.0"]
                 [org.clojure/data.json "0.2.6"]
                 [clj-http "2.0.0"]
                 [cheshire "5.5.0"]]
  :plugins [[lein-ring "0.9.1"]]
  :ring {:init project-clojure.controller/init-data
         :handler project-clojure.routes/app})
