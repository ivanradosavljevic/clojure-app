(ns project-clojure.routes
  (:use compojure.core
        project-clojure.views        
        project-clojure.controller
        [db.mongodb :as baza]
        [hiccup.middleware :only (wrap-base-url)]
        [noir.util.middleware])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.response :as response]
            [noir.session :as session]
            [noir.response :refer [redirect]]))

(defroutes main-routes
  (GET "/" [] (login-page))
  (GET "/index" [] (index-page))
  (GET "/register" [] (register-page))
  (POST "/register" [username password repeated-password] (register username password repeated-password))
  (POST "/login" [username password] (login username password))
 ; (GET "/logout" [] (logout))
  ;  (POST "/blah" [latitude longitude list] (baza/test-insert latitude longitude))
  (POST "/blah" [lat list lng radius] (let [res (no-name lat list lng radius)] (results-page res)))
  (POST "/favourites" [name address poiid] (favourites name address poiid))
  (GET "/favourites" [] (let [results (get-favourites)] (favourites-page results)))
  (GET "/logout" [] (logout))
  (POST "/remove-from-favourites" [poiid] (remove-from-favourites poiid))

  (route/resources "/"))
;  (route/not-found (not-found)))

(def app
  (-> (handler/site main-routes)
    (session/wrap-noir-session)
    (wrap-base-url)))